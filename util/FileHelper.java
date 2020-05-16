package flashcards.util;

import flashcards.dao.Storage;
import flashcards.domain.Card;

import java.io.*;
import java.util.*;

public class FileHelper {

    public static final String SEPARATOR = ";;";

    public static List<Card> importCardsFromFile(String fileName) throws FileNotFoundException {
        List<Card> list = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] split = line.split(SEPARATOR);
            if (split.length > 1) {
                String term = split[0];
                String definition = split[1];
                int counter = (split.length > 2) ? Integer.parseInt(split[2]) : 0;
                if (term != null && !"".equals(term)) {
                    Card card = new Card(term, definition, counter);
                    list.add(card);
                }
            }
        }
        return list;
    }

    public static void exportCardsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            List<Card> listToSave = Storage.getInstance().list();
            listToSave.forEach(card -> {
                try {
                    writer.write(String.format(
                            "%s%s%s%s%s\r\n",
                            card.getTerm(), SEPARATOR, card.getDefinition(), SEPARATOR, card.getErrorCounter())
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLog(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            List<String> log = Logger.getLogger().list();
            log.forEach(entry -> {
                try {
                    writer.write(entry + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
