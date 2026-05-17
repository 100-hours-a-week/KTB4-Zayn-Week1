package service;

import common.FootballConstant;
import common.RandomConstant;
import model.FootballTeam;
import model.UefaTeam;

import java.util.Random;

public class MatchService {
    private final Random rd;

    public MatchService() {
        this.rd = new Random();
    }

    public FootballTeam fight(FootballTeam teamA, FootballTeam teamB) {
        injuryOccur(teamA, teamB);

        double teamAWeight = Math.sqrt(
                ((UefaTeam) teamA).getWinningRate()
        );

        double teamBWeight = Math.sqrt(
                ((UefaTeam) teamB).getWinningRate()
        );

        double r = rd.nextDouble() * (teamAWeight + teamBWeight);

        if (teamAWeight >= r) return teamA;
        return teamB;
    }

    private void injuryOccur(FootballTeam teamA, FootballTeam teamB) {
        double rA = rd.nextDouble(), rB = rd.nextDouble();

        if (rA < RandomConstant.INJURY_PROBABILITY.getValue()) {
            teamA.setOccurInjuryCount(
                    teamA.getOccurInjuryCount() + FootballConstant.ONE.getValue()
            );

            ((UefaTeam) teamA).setWinningRate(
                    ((UefaTeam) teamA).getWinningRate() * Math.pow(RandomConstant.DECREASE_RATE.getValue(), teamA.getOccurInjuryCount())
            );
        }

        if (rB < RandomConstant.INJURY_PROBABILITY.getValue()) {
            teamB.setOccurInjuryCount(
                    teamB.getOccurInjuryCount() + FootballConstant.ONE.getValue()
            );

            ((UefaTeam) teamB).setWinningRate(
                    ((UefaTeam) teamB).getWinningRate() * Math.pow(RandomConstant.DECREASE_RATE.getValue(), teamB.getOccurInjuryCount())
            );
        }
    }
}