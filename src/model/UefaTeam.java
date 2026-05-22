package model;

public class UefaTeam extends FootballTeam implements Winnable, Injurable {
    private double uefaWinningRate; // UEFA 우승 확률
    private int occurInjuryCount = 0;

    public UefaTeam(String teamName, String shortName, double winningRate) {
        super(teamName, shortName);
        this.uefaWinningRate = winningRate;
    }

    public void setWinningRate(double rate) {
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