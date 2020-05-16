package flashcards.command;

import flashcards.dao.Storage;
import flashcards.domain.Card;
import flashcards.util.FileHelper;
import flashcards.util.Logger;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ImportCommand implements Command {

    private final Scanner scanner;

    public ImportCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        Logger.getLogger().log("File name:");
        String fileName = scanner.nextLine();
        importFile(fileName);
    }

    public void importFile(String fileName) {
        int amount = 0;
        List<Card> list;
        try {
            list = FileHelper.importCardsFromFile(fileName);
        } catch (FileNotFoundException e) {
            Logger.getLogger().log("File not found.");
            return;
        }
        if (list.size() > 0) {
            amount = Storage.getInstance().addAll(list);
        }
        Logger.getLogger().log(String.format("%d cards have been loaded.", amount));
    }
}
