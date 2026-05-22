import controller.FootballController;
import controller.UefaController;

public class SportsSimulatorApplication {
    public static void main(String[] args) {
        // Team <- FootballTeam <- UefaTeam 상속 구조
        // UefaTeam
//        FootballController footballController = new FootballController();
//        footballController.run();

        // Team <- FootballTeam, FootballTeam을 객체 내 필드에 생성한 컴포지션 구조
        // UefaTournamentParticipant
        UefaController uefaController = new UefaController();
        uefaController.run();
    }
}