package flashcards.command;

import flashcards.util.FileHelper;
import flashcards.util.Logger;

import java.util.Scanner;

public class LogCommand implements Command {
    Scanner scanner;

    public LogCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        Logger.getLogger().log("File name:");
        String fName = scanner.nextLine();
        FileHelper.saveLog(fName);
        Logger.getLogger().log("The log has been saved.");
    }
}
