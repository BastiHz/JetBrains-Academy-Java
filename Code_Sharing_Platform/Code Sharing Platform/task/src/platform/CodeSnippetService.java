package platform;

import org.springframework.stereotype.Service;

@Service
public class CodeSnippetService {

    private CodeSnippet codeSnippet;

    public CodeSnippetService() {
        codeSnippet = new CodeSnippet();
        codeSnippet.setCode("public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n}"
        );
    }

    public void setCodeSnippet(CodeSnippet codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public CodeSnippet getCodeSnippet() {
        return codeSnippet;
    }
}
