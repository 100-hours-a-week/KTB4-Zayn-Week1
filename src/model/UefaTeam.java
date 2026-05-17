package model;

public class UefaTeam extends FootballTeam {
    private double winningRate; // UEFA 우승 확률

    public UefaTeam(String teamName, String shortName) {
        super(teamName, shortName);
    }

    public void setWinningRate(double rate) {
        this.winningRate = rate;
    }

    public double getWinningRate() {
        return this.winningRate;
    }
}