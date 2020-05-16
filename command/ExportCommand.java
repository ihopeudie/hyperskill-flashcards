package flashcards.command;

import flashcards.dao.Storage;
import flashcards.util.FileHelper;
import flashcards.util.Logger;

import java.util.Scanner;

public class ExportCommand implements Command {

    private final Scanner scanner;

    public ExportCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        Logger.getLogger().log("File name:");
        String fileName = scanner.nextLine();
        exportFile(fileName);
    }

    public void exportFile(String fileName) {
        FileHelper.exportCardsToFile(fileName);
        Logger.getLogger().log(String.format("%d cards have been saved.", Storage.getInstance().size()));
    }
}
