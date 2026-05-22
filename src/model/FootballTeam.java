package model;

public class FootballTeam extends Team {
    private final String teamName;
    private final String shortName;

    public FootballTeam(String teamName, String shortName) {
        super("Football", 11); // FootballTeam 클래스만 존재하여 리터럴로 작성, 종목이 다양해질 경우 enum으로 관리

        this.teamName = teamName;
        this.shortName = shortName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getShortName() {
        return shortName;
    }
}