package controller;

import common.ErrorMessage;
import common.TournamentConstant;
import common.UefaTeamInfo;
import model.FootballTeam;
import model.UefaTournamentParticipant;
import model.Winnable;
import service.UefaMatchService;
import view.InputView;
import view.UefaOutputView;

import java.util.ArrayList;
import java.util.List;

// FootballTeam의 상속으로 UefaTeam클래스를 사용하지않고
// UefaTournamentParticipant 클래스의 필드 객체로 FootballTeam을 가지는 컴포지션 구조로 변경한 컨트롤러
public class UefaController {
    private final InputView iv;
    private final UefaOutputView ov;
    private final UefaMatchService ms;

    private final List<UefaTournamentParticipant> totalTeams = new ArrayList<>();

    public UefaController() {
        this.iv = new InputView();
        this.ov = new UefaOutputView();
        this.ms = new UefaMatchService();
    }

    public void run() {
        init();

        try { // 입력값 검증 필요 과정 -> IllegalArgumentException
            List<UefaTournamentParticipant> winners = playRoundOf16();
            List<UefaTournamentParticipant> semiFinalsTeams = playQuarterFinals(winners);

            playFinal(
                    playSemiFinals(semiFinalsTeams)
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // UEFA 16강 진출 팀 객체 생성 및 안내 메시지 출력
    private void init() {
        for (UefaTeamInfo teamName : UefaTeamInfo.values())
            totalTeams.add(
                    new UefaTournamentParticipant(
                            new FootballTeam(
                                    teamName.getFullName(),
                                    teamName.getShortName()
                            ),
                            teamName.getWinningRate()
                    )
            );

        ov.displayInitMessage();
        pressAnyKey();
    }

    private List<UefaTournamentParticipant> playRoundOf16() {
        ov.displayTeamsMessage(totalTeams);

        // 대진표 입력
        List<UefaTournamentParticipant> matchTeams = createBracket(TournamentConstant.ROUND_OF_16.getValue(), totalTeams);
        // 경기진행 및 결과 조회
        List<UefaTournamentParticipant> winners = progressMatch(TournamentConstant.ROUND_OF_16.getValue(), matchTeams);

        pressAnyKey();
        return winners;
    }

    private List<UefaTournamentParticipant> playQuarterFinals(List<UefaTournamentParticipant> teams) {
        ov.displayTeamsMessage(teams);

        // 대진표 입력
        List<UefaTournamentParticipant> matchTeams = createBracket(TournamentConstant.QUARTER_FINALS.getValue(), teams);
        // 경기 진행 및 결과 조회
        List<UefaTournamentParticipant> winners = progressMatch(TournamentConstant.QUARTER_FINALS.getValue(), matchTeams);

        pressAnyKey();
        return winners;
    }

    private List<UefaTournamentParticipant> playSemiFinals(List<UefaTournamentParticipant> teams) {
        ov.displayTeamsMessage(teams);
        return progressMatch(TournamentConstant.SEMI_FINALS.getValue(), teams);
    }

    private void playFinal(List<UefaTournamentParticipant> teams) {
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

    private List<UefaTournamentParticipant> createBracket(int teamsCount, List<UefaTournamentParticipant> teams) {
        List<UefaTournamentParticipant> matchTeams = new ArrayList<>();

        boolean[] isSelected = new boolean[teamsCount];
        for (int i = 0; i < teamsCount; i++) {
            int footballTeamIdx = iv.userInput();
            validateIdxScope(teamsCount, footballTeamIdx, isSelected);

            matchTeams.add(teams.get(footballTeamIdx));
        }

        return matchTeams;
    }

    private List<UefaTournamentParticipant> progressMatch(int teamsCount, List<UefaTournamentParticipant> teams) {
        List<UefaTournamentParticipant> winners = new ArrayList<>();

        int roundMatchCount = 0;
        for (int i = 0; i < teamsCount; i += 2) {
            ov.printMatchInfo(teamsCount, ++roundMatchCount, teams.get(i), teams.get(i + 1));
            pressAnyKey();

            UefaTournamentParticipant winner = ms.fight(
                    teams.get(i),
                    teams.get(i + 1)
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