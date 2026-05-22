package service;

import common.RandomConstant;
import model.FootballTeam;
import model.Injurable;
import model.Winnable;

import java.util.Random;

public class MatchService {
    private final Random rd;

    public MatchService() {
        this.rd = new Random();
    }

    public FootballTeam fight(Winnable teamA, Winnable teamB) {
        if (teamA instanceof Injurable injurable) injuryOccur(injurable);
        if (teamB instanceof Injurable injurable) injuryOccur(injurable);

        double teamAWeight = Math.sqrt(
                teamA.getWinningRate()
        );

        double teamBWeight = Math.sqrt(
                teamB.getWinningRate()
        );

        double r = rd.nextDouble() * (teamAWeight + teamBWeight);

        if (teamAWeight >= r) return (FootballTeam) teamA;
        return (FootballTeam) teamB;
    }

    private void injuryOccur(Injurable team) {
        double r = rd.nextDouble();
        if (r < RandomConstant.INJURY_PROBABILITY.getValue()) {
            team.injure();
            decreaseWinningRate((Winnable) team);
        }
    }

    private void decreaseWinningRate(Winnable team) {
        if (!(team instanceof Injurable)) return;

        team.setWinningRate(
                team.getWinningRate() * Math.pow(
                        RandomConstant.DECREASE_RATE.getValue(),
                        ((Injurable) team).getInjuryCount()
                )
        );
    }
}