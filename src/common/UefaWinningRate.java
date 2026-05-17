package common;

public enum UefaWinningRate {
    ARSENAL(27.4),
    BAYERN(14.3),
    LIVERPOOL(12.8),
    MANCITY(10.8),
    BARCELONA(7.7),
    CHELSEA(6.9),
    NEWCASTLE(4.7),
    PARIS(4.6),
    REALMADRID(2.8),
    SPORTING(2.7),
    ATLETICO(2),
    TOTTENHAM(1.2),
    ATALANTA(1.1),
    LEVERKUSEN(0.5),
    GLIMT(0.4),
    GALATASARAY(0.2);

    private final double value;

    UefaWinningRate(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
