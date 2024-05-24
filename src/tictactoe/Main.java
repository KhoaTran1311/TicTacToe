package tictactoe;

import tictactoe.model.state.Player;
import tictactoe.model.state.State;
import tictactoe.view.BoardView;
import tictactoe.view.Menu;


public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(Player.Type.X);
        Player player2 = new Player(Player.Type.O);

        State state = new State(player1, player2, Menu.startGame());
        BoardView.getView(state.getBoard());

        while (!state.isFinished()) {
            Menu.inGame(state);
            BoardView.getView(state.getBoard());
        }

        Menu.endGame(state.getWinner());
    }
}
