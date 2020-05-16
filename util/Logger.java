package flashcards.util;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private final List<String> log;

    private Logger() {
        this.log = new ArrayList<>();
    }

    public List<String> list() {
        return log;
    }

    public void log(String string) {
        System.out.println(string);
        log.add(string);
    }

    public static Logger getLogger() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
}
