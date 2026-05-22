package controller;

import common.ErrorMessage;
import common.TournamentConstant;
import common.UefaTeamInfo;
import model.FootballTeam;
import model.UefaTeam;
import model.Winnable;
import service.MatchService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class FootballController {
    private final InputView iv;
    private final OutputView ov;
    private final MatchService ms;

    private final List<FootballTeam> totalTeams = new ArrayList<>();

    public FootballController() {
        this.iv = new InputView();
        this.ov = new OutputView();
        this.ms = new MatchService();
    }

    public void run() {
        init();
        List<FootballTeam> semiFinalsTeams;

        try { // 입력값 검증 필요 과정 -> IllegalArgumentException
            List<FootballTeam> winners = playRoundOf16();
            semiFinalsTeams = playQuarterFinals(winners);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        playFinal(
                playSemiFinals(semiFinalsTeams)
        );
    }

    // UEFA 16강 진출 팀 객체 생성 및 안내 메시지 출력
    private void init() {
        for (UefaTeamInfo teamName : UefaTeamInfo.values())
            totalTeams.add(
                    new UefaTeam(
                            teamName.getFullName(),
                            teamName.getShortName(),
                            teamName.getWinningRate()
                    )
            );

        ov.displayInitMessage();
        pressAnyKey();
    }

    private List<FootballTeam> playRoundOf16() {
        ov.displayTeamsMessage(totalTeams);

        // 대진표 입력
        List<FootballTeam> matchTeams = createBracket(TournamentConstant.ROUND_OF_16.getValue(), totalTeams);
        // 경기진행 및 결과 조회
        List<FootballTeam> winners = progressMatch(TournamentConstant.ROUND_OF_16.getValue(), matchTeams);

        pressAnyKey();
        return winners;
    }

    private List<FootballTeam> playQuarterFinals(List<FootballTeam> teams) {
        ov.displayTeamsMessage(teams);

        // 대진표 입력
        List<FootballTeam> matchTeams = createBracket(TournamentConstant.QUARTER_FINALS.getValue(), teams);
        // 경기 진행 및 결과 조회
        List<FootballTeam> winners = progressMatch(TournamentConstant.QUARTER_FINALS.getValue(), matchTeams);

        pressAnyKey();
        return winners;
    }

    private List<FootballTeam> playSemiFinals(List<FootballTeam> teams) {
        ov.displayTeamsMessage(teams);
        return progressMatch(TournamentConstant.SEMI_FINALS.getValue(), teams);
    }

    private void playFinal(List<FootballTeam> teams) {
        ov.printMatchInfo(
                teams.size(),
                TournamentConstant.ONLY_ONE_ROUND.getValue(),
                teams.get(0),
                teams.get(1)
        );
        pressAnyKey();

        ov.finalWinnerMessage(
                ms.fight(
                        (Winnable) teams.get(0),
                        (Winnable) teams.get(1)
                )
        );

        iv.close();
    }

    private List<FootballTeam> createBracket(int teamsCount, List<FootballTeam> teams) {
        List<FootballTeam> matchTeams = new ArrayList<>();

        boolean[] isSelected = new boolean[teamsCount];
        for (int i = 0; i < teamsCount; i++) {
            int footballTeamIdx = iv.userInput();
            validateIdxScope(teamsCount, footballTeamIdx, isSelected);

            matchTeams.add(teams.get(footballTeamIdx));
        }

        return matchTeams;
    }

    private List<FootballTeam> progressMatch(int teamsCount, List<FootballTeam> teams) {
        List<FootballTeam> winners = new ArrayList<>();

        int roundMatchCount = 0;
        for (int i = 0; i < teamsCount; i += 2) {
            ov.printMatchInfo(teamsCount, ++roundMatchCount, teams.get(i), teams.get(i + 1));
            pressAnyKey();

            FootballTeam winner = ms.fight(
                    (Winnable) teams.get(i),
                    (Winnable) teams.get(i + 1)
            );
            winners.add(winner);
            ov.printWinner(winner);
        }

        return winners;
    }

    private void validateIdxScope(int round, int idx, boolean[] isSelected) {
        if (idx < 0 || idx > (round - 1))
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_SCOPE.getMessage());
        if (isSelected[idx])
            throw new IllegalArgumentException(ErrorMessage.ALREADY_SELECTED_TEAM.getMessage());

        isSelected[idx] = true;
    }

    private void pressAnyKey() {
        ov.displayPressAnyKey();
        iv.pressAnyKey();
    }
}