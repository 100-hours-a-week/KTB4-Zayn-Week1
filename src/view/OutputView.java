package view;

import common.FootballConstant;
import common.RoundName;
import common.ViewMessage;
import model.FootballTeam;

import java.util.List;

public class OutputView {
    public void displayInitMessage() {
        System.out.println(ViewMessage.INIT_MESSAGE.getMessage());
    }

    public void displayPressAnyKey() {
        System.out.print(ViewMessage.PRESS_ANY_KEY.getMessage());
    }

    public void displayTeamsMessage(List<FootballTeam> teams) {
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
        if (teams.size() == FootballConstant.ROUND_OF_16.getValue()) {
            System.out.println(ViewMessage.ROUND_OF_16_INIT.getMessage());
        }

        if (teams.size() == FootballConstant.QUARTER_FINALS.getValue()) {
            System.out.println(ViewMessage.ROUND_OF_8_INIT.getMessage());
        }

        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
        for (int i = FootballConstant.ZERO.getValue(); i < teams.size(); i++) {
            System.out.print(ViewMessage.TEAM_NAME_FORMAT.get(
                    i + FootballConstant.ONE.getValue(),
                    teams.get(i).getTeamName()
            ));

            if ((i + FootballConstant.ONE.getValue()) % FootballConstant.FOUR.getValue() == FootballConstant.ZERO.getValue())
                System.out.println();
        }
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
    }

    public void printWinner(FootballTeam winner) {
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
        System.out.println(ViewMessage.WINNER_MESSAGE.get(winner.getTeamName(), winner.getShortName()));
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
    }

    public void finalWinnerMessage(FootballTeam winner) {
        System.out.println(ViewMessage.FINAL_WINNER.get(winner.getTeamName(), winner.getShortName()));
    }

    public void printMatchInfo(int roundNum, int idx, FootballTeam teamA, FootballTeam teamB) {
        String roundName = setRoundName(roundNum);

        System.out.println(
                ViewMessage.MATCH_INFO.get(
                        roundName,
                        idx,
                        teamA.getTeamName(),
                        teamA.getShortName(),
                        teamB.getTeamName(),
                        teamB.getShortName()
                )
        );
    }

    private String setRoundName(int roundNum) {
        if (roundNum == FootballConstant.ROUND_OF_16.getValue())
            return RoundName.ROUND_OF_16.getRound();

        if (roundNum == FootballConstant.QUARTER_FINALS.getValue())
            return RoundName.QUATER_FINALS.getRound();

        if (roundNum == FootballConstant.SEMI_FINALS.getValue())
            return RoundName.SEMI_FINALS.getRound();

        return RoundName.FINAL.getRound();
    }
}