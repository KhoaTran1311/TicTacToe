package tictactoe.view;

import tictactoe.model.state.Player;
import tictactoe.model.state.State;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Menu() {}

    public static void endGame(Player winner) {
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

    public static int startGame() {
        System.out.println(
                """
                        ╦ ╦┌─┐╦  ┌─┐┌─┐┌┬┐╔═╗ ┬
                        ║║║├┤ ║  │  │ ││││║╣  │
                        ╚╩╝└─┘╚═╝└─┘└─┘┴ ┴╚═╝ o""");
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
