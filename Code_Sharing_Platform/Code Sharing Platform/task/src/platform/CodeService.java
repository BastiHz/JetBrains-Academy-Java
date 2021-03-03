package platform;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CodeService {

    private List<Code> codes = new ArrayList<>();

    public int addCode(Code code) {
        codes.add(code);
        return codes.size();  // FIXME: return value will be the real id in the next stage
    }

    public Code getCodeById(int id) {
        id -= 1;
        // FIXME: Don't subtract 1 from id when it is taken from a database in the next stage.
        // TODO: Return 404 if invalid id.
        return codes.get(id);
    }

    public List<Code> getLatestCodes() {
        int end = codes.size();
        int start = end < 10 ? 0 : end - 10;
        // Make a new list to prevent changing the order of the whole list.
        List<Code> latestCodes = new ArrayList<>(codes.subList(start, end));
        Collections.reverse(latestCodes);
        return latestCodes;
    }
}
