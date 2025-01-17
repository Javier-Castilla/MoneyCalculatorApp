package software.ulpgc.moneycalculator.architecture.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandFactory {
    private final Map<String, Builder> builderMap;

    public CommandFactory() {
        builderMap = new HashMap<>();
    }

    public CommandFactory register(String name, Builder builder) {
        builderMap.put(name, builder);
        return this;
    }

    public Set<String> keySet() {
        return builderMap.keySet();
    }

    public Builder get(String name) {
        return builderMap.get(name);
    }

    public interface Builder {
        Command build();
    }
}
