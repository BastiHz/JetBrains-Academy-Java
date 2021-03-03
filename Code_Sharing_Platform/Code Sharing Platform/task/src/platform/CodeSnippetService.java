package platform;

import org.springframework.stereotype.Service;

@Service
public class CodeSnippetService {

    private Code code;

    public CodeSnippetService() {
        code = new Code();
        code.setCode("public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n}"
        );
    }

    public void setCodeSnippet(Code code) {
        this.code = code;
    }

    public Code getCodeSnippet() {
        return code;
    }
}
