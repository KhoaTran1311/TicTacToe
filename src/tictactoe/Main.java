package tictactoe;

import java.util.Random;

import tictactoe.model.minimax.MinimaxAlphaBeta;
import tictactoe.model.state.Player;
import tictactoe.model.state.State;
import tictactoe.model.state.StateChild;
import tictactoe.utils.BoardUtils;
import tictactoe.view.BoardView;
import tictactoe.view.Menu;


public class Main {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        Menu.welcomeMessage();
        int numPlayers = Menu.numPlayersConfig();
        int boardConfig = Menu.boardSizeConfig();
        if (numPlayers == 1) {
            singlePlayerGame(boardConfig);
        } else if (numPlayers == 2) {
            twoPlayersGame(boardConfig);
        }
    }

    private static void twoPlayersGame(int boardConfig) {
        Player player1 = new Player(Player.Type.X);
        Player player2 = new Player(Player.Type.O);

        State state = new State(player1, player2, boardConfig);
        BoardView.getView(state.getBoard());

        while (!state.isFinished()) {
                Menu.inGame(state);
                BoardView.getView(state.getBoard());
        }

        Menu.endGame(state.getWinner(), true);
    }

    private static void singlePlayerGame(int boardConfig) {
        Player bot;
        Player player;
        if (rand.nextBoolean()) {
            bot = new Player(Player.Type.X, true);
            player = new Player(Player.Type.O, false);
        } else {
            bot = new Player(Player.Type.O, true);
            player = new Player(Player.Type.X, false);
        }

        State state = new State(player, bot, boardConfig);
        BoardView.getView(state.getBoard());
        MinimaxAlphaBeta minimaxAlphaBeta = new MinimaxAlphaBeta(3);

        while (!state.isFinished()) {
            if (state.getPlayersTurn().equals(bot)) {
                System.out.println("Bot's played");
                int[] botNextMove = minimaxAlphaBeta.alphaBetaSearch(
                        new StateChild(state),
                        0,
                        Double.NEGATIVE_INFINITY,
                        Double.POSITIVE_INFINITY
                ).getAction();

                state.play(BoardUtils.coordinateToPosition(botNextMove[0], botNextMove[1], boardConfig));

                BoardView.getView(state.getBoard());
            }
            else {
                Menu.inGame(state);
                BoardView.getView(state.getBoard());
            }
        }

        Menu.endGame(state.getWinner(), false);
    }
}
