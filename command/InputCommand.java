package flashcards.command;

import flashcards.util.Logger;

import java.util.Scanner;

public class InputCommand implements Command {

    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_REMOVE = "remove";
    public static final String COMMAND_IMPORT = "import";
    public static final String COMMAND_EXPORT = "export";
    public static final String COMMAND_ASK = "ask";
    public static final String COMMAND_LOG = "log";
    public static final String COMMAND_HARDEST_CARD = "hardest card";
    public static final String COMMAND_RESET_STATS = "reset stats";
    Scanner scanner;

    public InputCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        while (true) {
            Logger.getLogger().log("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            Command command = null;
            String input = scanner.nextLine();
            switch (input) {
                case COMMAND_ADD: {
                    command = new AddCommand(scanner);
                    break;
                }
                case COMMAND_REMOVE: {
                    command = new RemoveCommand(scanner);
                    break;
                }
                case COMMAND_IMPORT: {
                    command = new ImportCommand(scanner);
                    break;
                }
                case COMMAND_EXPORT: {
                    command = new ExportCommand(scanner);
                    break;
                }
                case COMMAND_ASK: {
                    command = new AskCommand(scanner);
                    break;
                }
                case COMMAND_LOG: {
                    command = new LogCommand(scanner);
                    break;
                }
                case COMMAND_HARDEST_CARD: {
                    command = new HardestCardCommand(scanner);
                    break;
                }
                case COMMAND_RESET_STATS: {
                    command = new ResetStatsCommand();
                    break;
                }
                case COMMAND_EXIT: {
                    return;
                }
                default: {
                    Logger.getLogger().log("Command is not implemented");
                }
            }
            if (command != null) {
                command.process();
            }
        }
    }
}
