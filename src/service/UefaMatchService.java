package service;

import common.RandomConstant;
import model.FootballTeam;
import model.Injurable;
import model.UefaTournamentParticipant;
import model.Winnable;

import java.util.Random;
import java.util.stream.Stream;

public class UefaMatchService {
    private final Random rd;

    public UefaMatchService() {
        this.rd = new Random();
    }

    public UefaTournamentParticipant fight(Winnable teamA, Winnable teamB) {
        applyInjuries(teamA, teamB);
        return decideWinner(teamA, teamB);
    }

    private void applyInjuries(Winnable teamA, Winnable teamB) {
        Stream.of(teamA, teamB)
                .filter(Injurable.class::isInstance)
                .map(Injurable.class::cast)
                .forEach(this::injuryOccur);
    }

    private UefaTournamentParticipant decideWinner(Winnable teamA, Winnable teamB) {
        double teamAWeight = Math.sqrt(
                teamA.getWinningRate()
        );

        double teamBWeight = Math.sqrt(
                teamB.getWinningRate()
        );

        double r = rd.nextDouble() * (teamAWeight + teamBWeight);

        if (teamAWeight >= r) return (UefaTournamentParticipant) teamA;
        return (UefaTournamentParticipant) teamB;
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