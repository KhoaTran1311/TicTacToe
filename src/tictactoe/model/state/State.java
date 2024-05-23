package tictactoe.model.state;

import java.util.Map;

public class State {
    private int turnNumber;
    private Player playersTurn;
    private final Player[] players;
    private Board board;
    private boolean isFinished;
    private Player winner = null;

    // init
    public State(Player player1, Player player2, int boardSize) {
        turnNumber = 0;

        // X's turn to start
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

        board = new Board(boardSize);
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
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
        setTurnNumber(getTurnNumber() + 1);

        for (Player player : getPlayers()) {
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
                default -> {
                    return false;
                }
            }
        }

        if (getTurnNumber() == getBoard().getBoardSideLength() * getBoard().getBoardSideLength())
            setFinished(true);

        return true;
    }
}
