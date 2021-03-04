package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    public int addCode(Code code) {
        codeRepository.save(code);
        return code.getId();
    }

    public Code getCodeById(int id) {
        return codeRepository.findById(id).orElseThrow();
    }

    public List<Code> getLatestCodes() {
        return codeRepository.findTop10ByOrderByIdDesc();
    }
}
