package common;

public enum FootballTeamName {
    ARSENAL("Arsenal", "ARS"),
    BAYERN("Bayern München", "FCB"),
    LIVERPOOL("Liverpool", "LIV"),
    MANCITY("Manchester City", "MCI"),
    BARCELONA("Barcelona", "BAR"),
    CHELSEA("Chelsea", "CHE"),
    NEWCASTLE("Newcastle United", "NEW"),
    PARIS("Paris Saint-Germain", "PSG"),
    REALMADRID("Real Madrid", "RMA"),
    SPORTING("Sporting CP", "SCP"),
    ATLETICO("Atlético de Madrid", "ATM"),
    TOTTENHAM("Tottenham Hotspur", "TOT"),
    ATALANTA("Atalanta", "ATA"),
    LEVERKUSEN("Bayer Leverkusen", "B04"),
    GLIMT("Bodø/Glimt", "BOD"),
    GALATASARAY("Galatasaray", "GAL");

    private final String fullName;
    private final String shortName;

    FootballTeamName(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getShortName() {
        return this.shortName;
    }
}