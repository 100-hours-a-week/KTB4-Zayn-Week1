package view;

import common.ErrorMessage;
import common.FootballConstant;
import common.ViewMessage;

import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    public void pressAnyKey() {
        sc.nextLine();
    }

    public void close() {
        sc.close();
    }

    public int userInput() {
        System.out.print(ViewMessage.USER_INPUT_FORMAT.getMessage());
        return validateUserInput(sc.nextLine().trim());
    }

    private int validateUserInput(String input) {
        if (input == null || input.isBlank())
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_OR_IS_EMPTY.getMessage());

        return parseAndValidateDigit(input);
    }

    private int parseAndValidateDigit(String s) {
        try {
            return Integer.parseInt(s) - FootballConstant.ONE.getValue();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
        }
    }
}