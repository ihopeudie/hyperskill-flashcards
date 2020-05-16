package flashcards.command;

import flashcards.dao.Storage;
import flashcards.domain.Card;
import flashcards.util.Logger;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HardestCardCommand implements Command {
    Scanner scanner;

    public HardestCardCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void process() {
        List<Card> hardestCards = Storage.getInstance().getHardestCards();
        Logger.getLogger().log(printHardestCards(hardestCards));
    }

    private String printHardestCards(List<Card> hardestCards) {
        if (hardestCards.isEmpty()) {
            return "There are no cards with errors.";
        }
        boolean manyCards = hardestCards.size() > 1;
        return "The hardest card" +
                (manyCards ? "s are " : " is ") +
                hardestCards
                        .stream()
                        .map(e -> "\"" + e.getTerm() + "\"")
                        .collect(Collectors.joining(", ")) +
                ". You have "+hardestCards.get(0).getErrorCounter()+" errors answering " +
                (manyCards ? "them." : "it.");
    }
}
