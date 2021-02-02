package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.regex.Pattern;


// Class must either be public or have a public constructor to enable serialization.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {

    // No parent Node to avoid circular references.
    // Fields must either be public or have public getters and setters
    // to enable serialization via jackson.
    public TreeNode yes;
    public TreeNode no;
    public String data;  // Can be either the full animal name or the fact statement.

    // The fields below will not be serialized.
    static final Pattern SPACE_AFTER_ARTICLE = Pattern.compile("(?<=^(a|an))\\s");
    static final Pattern ARTICLE_ANIMAL = Pattern.compile("^(a|an)\\s+.+");
    static final Pattern START_VOWEL = Pattern.compile("^[aeiou].*");
    static final Pattern DOT_BANG_END = Pattern.compile("[.!]$");
    static final Pattern SECOND_SPACE = Pattern.compile("(?<=^\\w{1,3}\\s\\w{1,3})\\s");
    static final Map<String, String[]> itCanHasIs = Map.of(
        "It can", new String[] {"can't", "Can it"},
        "It has", new String[] {"doesn't have", "Does it have"},
        "It is", new String[] {"isn't", "Is it"}
    );
    String name;
    String article;
    String question;
    String negatedStatement;

    @JsonIgnore
    boolean isAnimal() {
        return yes == null && no == null;
    }

    @JsonIgnore
    static TreeNode newAnimal() {
        TreeNode animal = new TreeNode();
        String input = Helpers.nextLine();
        if (ARTICLE_ANIMAL.matcher(input).matches()) {
            animal.data = input;
            animal.initOtherAnimalFields();
        } else {
            animal.article = START_VOWEL.matcher(input).matches() ? "an" : "a";
            animal.name = input;
            animal.data = animal.article + " " + animal.name;
        }
        return animal;
    }

    @JsonIgnore
    static TreeNode newFact(String statement) {
        TreeNode fact = new TreeNode();
        statement = DOT_BANG_END.matcher(statement).replaceAll("");
        statement = Helpers.capitalizeFirst(statement);
        fact.data = statement + ".";
        fact.initOtherFactFields();
        return fact;
    }

    @JsonIgnore
    void initOtherFields() {
        if (isAnimal()) {
            initOtherAnimalFields();
        } else {
            initOtherFactFields();
            yes.initOtherFields();
            no.initOtherFields();
        }
    }

    @JsonIgnore
    private void initOtherAnimalFields() {
        String[] parts = SPACE_AFTER_ARTICLE.split(data);
        this.article = parts[0];
        this.name = parts[1];
    }

    @JsonIgnore
    private void initOtherFactFields() {
        String[] parts = SECOND_SPACE.split(data);
        question = String.format(
            "%s %s?",
            itCanHasIs.get(parts[0])[1],
            parts[1].substring(0, parts[1].length() - 1)
        );
        negatedStatement = String.format("It %s %s", itCanHasIs.get(parts[0])[0], parts[1]);
    }
}
