package common;

public enum RoundName {
    ROUND_OF_16("16강"),
    QUATER_FINALS("8강"),
    SEMI_FINALS("4강"),
    FINAL("결승");

    private final String round;

    RoundName(String round) {
        this.round = round;
    }

    public String getRound() {
        return round;
    }
}
