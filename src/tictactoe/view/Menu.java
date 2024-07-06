package tictactoe.view;

import tictactoe.model.state.Player;
import tictactoe.model.state.State;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The type Menu.
 */
public class Menu {
    private Menu() {}


    /**
     * Print the end results
     *
     * @param winner the winner
     */
    public static void endGame(Player winner) {
        // Tie
        if (winner == null) {
            System.out.println("""
                    
                    ╦┌┬┐┌─┐  ┌─┐  ┌┬┐╦┌─┐ ┬
                    ║ │ └─┐  ├─┤   │ ║├┤  │
                    ╩ ┴ └─┘  ┴ ┴   ┴ ╩└─┘ o
                    """);
            return;
        }
        if (winner.getType().equals(Player.Type.X)) {
            System.out.println("""
                                         *             .''.
                           .''.      .        *''*    :_\\/_:     .
                          :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.
                      .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=-
                     :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.'
                     : /\\ : :::::     *_\\/_*     -= o =-  /)\\    '  *
                      '..'  ':::'     * /\\ *     .'/.\\'.   '
                          *    ┏┓┏┓  ╦ ╦┌─┐┌┐╷ ┬       :
                            *   ┃┃   ║║║│ ││││ │     /
                            *  ┗┛┗┛  ╚╩╝└─┘╵└┘ o
                    """);
        } else {
            System.out.println("""
                      *                                .''.
                           .''.      .        *''*    :_\\/_:     .
                          :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.
                      .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=-
                     :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.'
                     : /\\ : :::::     *_\\/_*     -= o =-  /)\\    '  *
                      '..'  ':::'     * /\\ *     .'/.\\'.   '
                          *     ┏┓  ╦ ╦┌─┐┌┐╷ ┬    :
                            *   ┃┃  ║║║│ ││││ │     \\
                            *   ┗┛  ╚╩╝└─┘╵└┘ o
                    """);
        }
    }

    /**
     * Print the game-start panel
     *
     * @return the board size
     */
    public static int startGame() {
        welcomeMessage();
        return boardSizeConfig();
    }

    /**
     * print the welcome message
     */
    private static void welcomeMessage() {
        System.out.println(
                """
                        ╦ ╦┌─┐╦  ┌─┐┌─┐┌┬┐╔═╗ ┬
                        ║║║├┤ ║  │  │ ││││║╣  │
                        ╚╩╝└─┘╚═╝└─┘└─┘┴ ┴╚═╝ o""");
        System.out.println("--- Choose board size (enter a number between 3 and 10): ");
    }

    /**
     * the board size choosing panel for the user
     * @return the user's choice
     */
    private static int boardSizeConfig() {
        Scanner scanner = new Scanner(System.in);
        try {
            int boardSize = scanner.nextInt();
            if (boardSize >= 3 && boardSize <= 10) {
                System.out.println("First one get to " + Math.min(boardSize, 5) + " wins!");
                return boardSize;
            }
        } catch (InputMismatchException e) {
            System.err.println("!!!!!!Invalid board size number. Using default board size 3!!!!!!");
            return 3;
        }
        System.err.println("!!!!!!Invalid board size number. Using default board size 3!!!!!!");
        return 3;
    }

    /**
     * Output in game options
     *
     * @param state the state
     */
    public static void inGame(State state) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(state.getPlayersTurn().getType() + "'s turn");
        System.out.println("--- Choose a position on the board: ");
        try {
            int nextPlay = scanner.nextInt();
            int boardSize = state.getBoard().getBoardSize();
            if (nextPlay < 1 || nextPlay > boardSize || !state.play(nextPlay))
                System.err.println("!!!!!!Enter a valid position!!!!!!");
        } catch (InputMismatchException e) {
            System.err.println("!!!!!!Enter a valid position!!!!!!");
        }
    }
}
