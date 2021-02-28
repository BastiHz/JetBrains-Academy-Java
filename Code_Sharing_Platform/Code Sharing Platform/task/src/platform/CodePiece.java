package platform;

public class CodePiece {
    private final String code;
    private final String htmlCode;
    private static final String HTML_TEMPLATE = "<html>" +
        "<head>" +
        "<title>Code</title>" +
        "</head>" +
        "<body>" +
        "<pre>%s</pre>" +
        "</body>" +
        "</html>";

    CodePiece() {
        code = "public static void main(String[] args) {\n" +
        "    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
        htmlCode = String.format(HTML_TEMPLATE, code);
    }

    public String getCode() {
        return code;
    }

    String getHtmlCode() {
        return htmlCode;
    }
}
