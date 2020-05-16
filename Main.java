package flashcards;

import flashcards.command.ExportCommand;
import flashcards.command.ImportCommand;
import flashcards.command.InputCommand;
import flashcards.util.Logger;

import java.util.Scanner;

public class Main {

    public static final String IMPORT_ARG = "-import";
    public static final String EXPORT_ARG = "-export";

    public static void main(String[] args) {
        String fileToExport = "";
        try (Scanner scanner = new Scanner(System.in)) {
            int commandsCount = args.length / 2;
            for (int i = 0; i < commandsCount; i ++) {
                if (args[i*2].equalsIgnoreCase(IMPORT_ARG)) {
                    new ImportCommand(scanner).importFile(args[i*2 + 1]);
                }
                if (args[i*2].equalsIgnoreCase(EXPORT_ARG)) {
                    fileToExport = args[i*2 + 1];
                }
            }
            new InputCommand(scanner).process();
            Logger.getLogger().log("Bye bye!");
            if (!fileToExport.isBlank()) {
                new ExportCommand(scanner).exportFile(fileToExport);
            }
        }
    }
}
