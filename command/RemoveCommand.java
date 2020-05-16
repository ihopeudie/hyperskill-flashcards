package flashcards.command;

import flashcards.dao.Storage;
import flashcards.util.Logger;

import java.util.Scanner;

public class RemoveCommand implements Command {

    private final Scanner scanner;

    public RemoveCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        Logger.getLogger().log("The card:");
        String term = scanner.nextLine();
        boolean success = Storage.getInstance().removeByTerm(term);
        Logger.getLogger().log(success ? "The card has been removed." : String.format("Can't remove \"%s\": there is no such card", term));
    }
}
