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
            System.out.println("""
                        
                    ╦┌┬┐┌─┐  ┌─┐  ┌┬┐╦┌─┐┬
                    ║ │ └─┐  ├─┤   │ ║├┤ │
                    ╩ ┴ └─┘  ┴ ┴   ┴ ╩└─┘o
                    """);
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

    public static int startGame() {
        System.out.println(
                """
                        ╦ ╦┌─┐╦  ┌─┐┌─┐┌┬┐╔═╗┬
                        ║║║├┤ ║  │  │ ││││║╣ │
                        ╚╩╝└─┘╩═╝└─┘└─┘┴ ┴╚═╝o""");
        System.out.println("--- Choose board size (enter a number between 3 and 10): ");
        Scanner scanner = new Scanner(System.in);
        try {
            int boardSize = scanner.nextInt();
            if (boardSize >= 3 && boardSize <= 10) return boardSize;
        } catch (InputMismatchException e) {
            System.err.println("!!!!!!Invalid board size number. Using default board size 3!!!!!!");
            return 3;
        }
        System.err.println("!!!!!!Invalid board size number. Using default board size 3!!!!!!");
        return 3;
    }

    public static void inGame(State state) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(state.getPlayersTurn().getType() + "'s turn");
        System.out.println("--- Choose a position on the board: ");
        try {
            int nextPlay = scanner.nextInt();
            int boardSize = state.getBoard().getBoardSideLength() * state.getBoard().getBoardSideLength();
            if (nextPlay < 1 || nextPlay > boardSize || !state.play(nextPlay))
                System.err.println("!!!!!!Enter a valid position!!!!!!");
        } catch (InputMismatchException e) {
            System.err.println("!!!!!!Enter a valid position!!!!!!");
        }
    }
}
