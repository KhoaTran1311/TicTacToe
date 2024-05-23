package tictactoe.view;

import tictactoe.model.state.Player;
import tictactoe.model.state.State;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void endGame(Player player1, Player player2) {
        if (player1.getStatus().equals(Player.Status.WON)) {
            System.out.println("X won!");
        } else if (player2.getStatus().equals(Player.Status.WON)) {
            System.out.println("O won!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void endGame(Player winner) {
        if (winner == null) {
            System.out.println("It's a tie!");
            return;
        }

        System.out.println("                                   .''.       \n" +
                "       .''.      .        *''*    :_\\/_:     . \n" +
                "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
                "  .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=-\n" +
                " :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.'\n" +
                " : /\\ : :::::     *_\\/_*     -= o =-  /)\\    '  *\n" +
                "  '..'  ':::'     * /\\ *     .'/.\\'.   '\n" +
                "      *            *..*         :\n" +
                "              " + winner.getType() + " won!\"     *\n" +
                "        *");
    }

    public static void startGame(Player player1, Player player2) {
        System.out.println("Welcome!");
    }

    public static void inGame(State state) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(state.getPlayersTurn().getType() + "'s turn");
        System.out.println("--- Choose a position on the board: ");
        try {
            int nextPlay = scanner.nextInt();
            int boardSize = state.getBoard().getBoardSideLength() * state.getBoard().getBoardSideLength();
            if (nextPlay < 1 || nextPlay > boardSize || !state.play(nextPlay)) System.out.println("!!!!!!Enter a valid position!!!!!!");
        } catch (InputMismatchException e) {
            System.out.println("!!!!!!Enter a valid position!!!!!!");
        }
    }
}
