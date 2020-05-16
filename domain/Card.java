package flashcards.domain;

public class Card {
    private String term;
    private String definition;
    private int errorCounter;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
        errorCounter = 0;
    }

    public Card(String term, String definition, int errorCounter) {
        this.term = term;
        this.definition = definition;
        this.errorCounter = errorCounter;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(int errorCounter) {
        this.errorCounter = errorCounter;
    }
}
