package view;

import common.TournamentConstant;
import common.RoundName;
import common.ViewMessage;
import model.UefaTournamentParticipant;

import java.util.List;

public class UefaOutputView {
    public void displayInitMessage() {
        System.out.println(ViewMessage.INIT_MESSAGE.getMessage());
    }

    public void displayPressAnyKey() {
        System.out.print(ViewMessage.PRESS_ANY_KEY.getMessage());
    }

    public void displayTeamsMessage(List<UefaTournamentParticipant> teams) {
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
        if (teams.size() == TournamentConstant.ROUND_OF_16.getValue()) {
            System.out.println(ViewMessage.ROUND_OF_16_INIT.getMessage());
        }

        if (teams.size() == TournamentConstant.QUARTER_FINALS.getValue()) {
            System.out.println(ViewMessage.ROUND_OF_8_INIT.getMessage());
        }

        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
        for (int i = 0; i < teams.size(); i++) {
            System.out.print(ViewMessage.TEAM_NAME_FORMAT.get(
                    i + 1,
                    teams.get(i).getTeamName()
            ));

            if ((i + 1) % TournamentConstant.SPLIT_NUMBER.getValue() == 0)
                System.out.println();
        }
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
    }

    public void printWinner(UefaTournamentParticipant winner) {
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
        System.out.println(ViewMessage.WINNER_MESSAGE.get(winner.getTeamName(), winner.getShortName()));
        System.out.println(ViewMessage.MIDDLE_BAR.getMessage());
    }

    public void finalWinnerMessage(UefaTournamentParticipant winner) {
        System.out.println(ViewMessage.FINAL_WINNER.get(winner.getTeamName(), winner.getShortName()));
    }

    public void printMatchInfo(int roundNum, int roundMatchCount, UefaTournamentParticipant teamA, UefaTournamentParticipant teamB) {
        System.out.println(
                ViewMessage.MATCH_INFO.get(
                        RoundName.getRound(roundNum),
                        roundMatchCount,
                        teamA.getTeamName(),
                        teamA.getShortName(),
                        teamB.getTeamName(),
                        teamB.getShortName()
                )
        );
    }
}