package flashcards.command;

import flashcards.dao.Storage;
import flashcards.util.Logger;

public class ResetStatsCommand implements Command {

    @Override
    public void process() {
        Storage.getInstance().resetStats();
        Logger.getLogger().log("Card statistics has been reset");
    }
}
