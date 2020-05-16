package flashcards.command;

import flashcards.dao.Storage;
import flashcards.domain.Card;
import flashcards.util.Logger;

import java.util.Scanner;

public class AskCommand implements Command {
    Scanner scanner;

    public AskCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        Logger.getLogger().log("How many times to ask?");
        int amount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < amount; i++) {
            Card card = Storage.getInstance().getRandomCard();
            askForAnswer(card);
        }
    }

    private void askForAnswer(Card card) {
        Logger.getLogger().log(String.format("Print the definition of \"%s\"", card.getTerm()));
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(card.getDefinition())) {
            Logger.getLogger().log("Correct answer");
            return;
        }
        String realTerm = Storage.getInstance().findTermByDefinition(answer);
        printErrorMessage(card.getDefinition(), realTerm);
        card.setErrorCounter(card.getErrorCounter()+1);
    }

    private static void printErrorMessage(String realDefinition, String realTerm) {
        System.out.print("Wrong answer. ");
        System.out.print(String.format("(The correct one is \"%s\"", realDefinition));
        if (realTerm != null) {
            System.out.print(", ");
            Logger.getLogger().log(String.format("you've just written the definition of \"%s\")", realTerm));
        } else {
            Logger.getLogger().log(").");
        }
    }
}
