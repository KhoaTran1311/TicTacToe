package tictactoe.model.state;

import tictactoe.Main;

import java.util.Map;

public class State {
    private int turnNumber;
    private Player playersTurn;
    private Player[] players;
    private Board board;
    private boolean isFinished;
    private Player winner = null;

    // init
    public State(Player player1, Player player2) {
        turnNumber = 0;

        // X's turn
        if (player1.getType().equals(Player.Type.X)) {
            playersTurn = player1;
            player1.setStatus(Player.Status.IN_TURN);
            player2.setStatus(Player.Status.IDLE);
        } else {
            playersTurn = player2;
            player1.setStatus(Player.Status.IDLE);
            player2.setStatus(Player.Status.IN_TURN);
        }

        players = new Player[]{player1, player2};

        isFinished = false;

        board = new Board(10);
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Player getPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(Player playersTurn) {
        this.playersTurn = playersTurn;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean play(int position) {
        Map.Entry<Board, Integer> move = playersTurn.move(getBoard(), position);
        if (move.getValue() == 2) return false;
        setBoard(move.getKey());
        turnNumber++;

        for (Player player : players) {
            switch (player.getStatus()) {
                case IN_TURN -> player.setStatus(Player.Status.IDLE);
                case IDLE -> {
                    setPlayersTurn(player);
                    player.setStatus(Player.Status.IN_TURN);
                }
                case WON -> {
                    setFinished(true);
                    setWinner(player);
                }
            }
        }

        if (turnNumber == board.getBoardSideLength() * board.getBoardSideLength())
            setFinished(true);

        return true;
    }
}
