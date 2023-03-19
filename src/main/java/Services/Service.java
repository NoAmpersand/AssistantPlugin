package Services;

import java.util.List;

public class Service {
    public String convertListToString(List<String> list) {
        return String.join(" ", list);
    }
}
