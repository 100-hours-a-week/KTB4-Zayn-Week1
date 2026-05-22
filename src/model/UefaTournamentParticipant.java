package model;

import common.ErrorMessage;

public class UefaTournamentParticipant implements Winnable, Injurable {
    private final FootballTeam team;
    private double uefaWinningRate;
    private int occurInjuryCount = 0;

    public UefaTournamentParticipant(FootballTeam team, double winningRate) {
        this.team = team;
        this.uefaWinningRate = winningRate;
    }

    public String getTeamName() {
        return team.getTeamName();
    }

    public String getShortName() {
        return team.getShortName();
    }

    public void setWinningRate(double rate) {
        if (rate <= 0)
            throw new IllegalArgumentException(ErrorMessage.INVALID_PARAMETER.getMessage());

        this.uefaWinningRate = rate;
    }

    public double getWinningRate() {
        return this.uefaWinningRate;
    }

    public void injure() {
        this.occurInjuryCount++;
    }

    public int getInjuryCount() {
        return this.occurInjuryCount;
    }
}
