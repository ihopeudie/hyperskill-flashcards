package flashcards.command;

import flashcards.dao.Storage;
import flashcards.domain.Card;
import flashcards.util.Logger;

import java.util.Scanner;

public class AddCommand implements Command {

    private final Scanner scanner;

    public AddCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        String term;
        Logger.getLogger().log("The card:");
        term = readTerm();
        if (term == null) {
            return;
        }
        Logger.getLogger().log("The definition of the card:");
        String definition = readDefinition();
        if (definition == null) {
            return;
        }
        Card card = new Card(term, definition);
        Storage.getInstance().add(card);
        Logger.getLogger().log(String.format("The pair (\"%s\":\"%s\") has been added.\n", card.getTerm(), card.getDefinition()));
    }

    private String readTerm() {
        String term;
        term = scanner.nextLine();
        if (Storage.getInstance().hasCardWithTerm(term)) {
            Logger.getLogger().log(String.format("The card \"%s\" already exists.", term));
            return null;
        }
        return term;
    }

    private String readDefinition() {
        String definition;
        definition = scanner.nextLine();
        if (Storage.getInstance().hasCardWithDefinition(definition)) {
            Logger.getLogger().log(String.format("The definition \"%s\" already exists.", definition));
            return null;
        }
        return definition;
    }


}
