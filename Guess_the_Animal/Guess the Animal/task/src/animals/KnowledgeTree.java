package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;


@JsonInclude(JsonInclude.Include.NON_NULL)
class TreeNode {

    // No parent Node to avoid circular references.
    // Fields must either be public or have public getters and setters
    // to enable serialization via jackson.
    public TreeNode yes;
    public TreeNode no;
    public String data;

    public TreeNode() {}  // Required for json serialization.

    @JsonIgnore
    TreeNode(String data) {
        this.data = data;
    }

    @JsonIgnore
    TreeNode(String article, String name) {
        this.data = article + " " + name;
    }

    @JsonIgnore
    boolean isAnimal() {
        return yes == null && no == null;
    }
}


class KnowledgeTree {

    TreeNode root;
    private final String FILENAME;
    private final ObjectMapper objectMapper;

    KnowledgeTree(String mapperType) {
        switch (mapperType) {
            case "json":
                objectMapper = new JsonMapper();
                FILENAME = "animals.json";
                break;
            case "xml":
                objectMapper = new XmlMapper();
                FILENAME = "animals.xml";
                break;
            case "yaml":
                objectMapper = new YAMLMapper();
                FILENAME = "animals.yaml";
                break;
            default:
                throw new IllegalArgumentException("Invalid type specified.");
        }
    }

    void insert(
            TreeNode fact,
            TreeNode parent,
            TreeNode oldAnimal,
            TreeNode newAnimal,
            boolean factIsTrueForNewAnimal) {
        if (parent == null) {
            root = fact;
        } else if (oldAnimal == parent.yes) {
            parent.yes = fact;
        } else {
            parent.no = fact;
        }

        if (factIsTrueForNewAnimal) {
            fact.yes = newAnimal;
            fact.no = oldAnimal;
        } else {
            fact.yes = oldAnimal;
            fact.no = newAnimal;
        }
    }

    void load() {
        try {
            root = objectMapper.readValue(new File(FILENAME), TreeNode.class);
        } catch (IOException e) {
            root = null;
        }
    }

    void save() {
        try {
            objectMapper
                 .writerWithDefaultPrettyPrinter()
                 .writeValue(new File(FILENAME), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
