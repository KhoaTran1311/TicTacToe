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
    public static void endGame(Player winner, boolean is2Players) {
        // Tie
        if (winner == null) {
            System.out.println("""
                    
                    ╦┌┬┐┌─┐  ┌─┐  ┌┬┐╦┌─┐ ┬
                    ║ │ └─┐  ├─┤   │ ║├┤  │
                    ╩ ┴ └─┘  ┴ ┴   ┴ ╩└─┘ o
                    """);
            return;
        }
        if (is2Players) {
            twoPlayersEndGame(winner);
        }
        else {
            singlePlayersEndGame(winner);
        }

    }

    private static void twoPlayersEndGame(Player winner) {
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

    private static void singlePlayersEndGame(Player winner) {
        if (winner.isBot()) {
            System.out.println("""
                   /    , // ,,/ `.// ,/ ,//   /, // ,/, /, // ,/,   ,
                    /, // ,/ ,// ,/ ,, ,/, /  ,/ /, //, /,/ //, ,, /,
                     /` / //  ,/` ,/  / //, // ,/`/, //  //, /`,//,/ ,
                      ,,  //,/,  /, `, // /, //, // , ,/, // ,//,  ,//
                    ,/ ,/,, / /,/`  ,/  ' ,`,/, ///, //  / ' // , / ,/
                     //  , /  ╦ ╦, ╦ /┌─┐┌─┐─┬─, , /` ,/' / // ,, / ,/
                   ,/  .  ,// ║ ║ `║, │ │└─┐|│ `/ , '/ / / / ,  //,  ,
                     /,/ / ,/ ╚─╝, ╚═╛└─┘└─┘ ╵ ` ///, / `  /`  ,   //
                    / /,, /      `             ,/ /, . /  // ,//, / //,
                    , / / //,               ,   / ,/,/, // ,/, //, , //
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
                          *    ╦ ╦  ╦ ╦┌─┐┌┐╷ ┬    :
                            *  ║ ║  ║║║│ ││││ │     \\
                            *  ╚─╝  ╚╩╝└─┘╵└┘ o
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
    public static void welcomeMessage() {
        System.out.println(
                """
                        ╦ ╦┌─┐╦  ┌─┐┌─┐┌┬┐╔═╗ ┬
                        ║║║├┤ ║  │  │ ││││║╣  │
                        ╚╩╝└─┘╚═╛└─┘└─┘┴ ┴╚═╝ o""");
    }

    /**
     * the board size choosing panel for the user
     * @return the user's choice
     */
    public static int boardSizeConfig() {
        System.out.println("--- Choose board size (enter a number between 3 and 10): ");
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

    public static int numPlayersConfig() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Choose the number of player (1 or 2):");
        try {
            int numPlayer = scanner.nextInt();
            if (numPlayer == 1 || numPlayer == 2) {
                return numPlayer;
            }
        } catch (InputMismatchException e) {
            System.err.println("!!!!!!Invalid number of players. Using default 1!!!!!!");
            return 1;
        }
        System.err.println("!!!!!!Invalid number of players. Using default 1!!!!!!");
        return 1;
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
