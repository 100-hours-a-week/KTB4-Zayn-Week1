package common;

public enum FootballConstant {
    ROUND_OF_16(16),
    QUARTER_FINALS(8),
    SEMI_FINALS(4),
    FINAL(2),
    SPLIT_NUMBER(4);

    private final int value;

    FootballConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}