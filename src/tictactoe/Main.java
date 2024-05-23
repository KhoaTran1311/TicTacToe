package tictactoe;

import tictactoe.model.state.Player;
import tictactoe.model.state.Board;
import tictactoe.model.state.State;
import tictactoe.view.BoardView;
import tictactoe.view.Menu;

public class Main {
    public static void main(String[] args) {
//        BoardView boardView = new BoardView(new int[][]{{0, 1, 2}, {1, 2, 0}, {2, 0, 1}});
//        BoardView boardView = new BoardView();

        Player player1 = new Player(1, Player.Type.X);
        Player player2 = new Player(2, Player.Type.O);
        State state = new State(player1, player2);
        BoardView.getView(state.getBoard());

        while (!state.isFinished()) {
            Menu.inGame(state);
            BoardView.getView(state.getBoard());
        }

        Menu.endGame(state.getWinner());
    }
}
