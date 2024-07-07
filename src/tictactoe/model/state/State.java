package tictactoe.model.state;

import tictactoe.utils.BoardUtils;

import java.util.Map;

/**
 * the state of the game
 */
public class State {

//---------------------------------------------------FIELDS-----------------------------------------------------

    private int turnNumber;
    private Player playersTurn;
    private final Player[] players;
    private Board board;
    private boolean isFinished;
    private Player winner = null;

//------------------------------------------------CONSTRUCTORS-----------------------------------------------------

    /**
     * Instantiates a new State.
     *
     * @param player1   the player 1
     * @param player2   the player 2
     * @param boardSize the board size
     */
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

    /**
     * Instantiates a new State with default board size of 3
     *
     * @param player1 the player 1
     * @param player2 the player 2
     */
    public State(Player player1, Player player2) {
        this(player1, player2, 3);
    }

//-------------------------------------------GETTERS & SETTERS-----------------------------------------------------

    /**
     * Gets turn number.
     *
     * @return the turn number
     */
    public int getTurnNumber() {
        return turnNumber;
    }

    /**
     * Sets turn number.
     *
     * @param turnNumber the turn number
     */
    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    /**
     * Gets players turn.
     *
     * @return the players turn
     */
    public Player getPlayersTurn() {
        return playersTurn;
    }

    /**
     * Sets players turn.
     *
     * @param playersTurn the players turn
     */
    public void setPlayersTurn(Player playersTurn) {
        this.playersTurn = playersTurn;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Sets finished.
     *
     * @param finished the finished
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Get players
     *
     * @return the players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

//-------------------------------------------------FUNCTIONS-----------------------------------------------------
    private boolean isBoardFull() {
        return getTurnNumber() == getBoard().getBoardSize();
    }


    /**
     * Play update the player and the board after a play
     *
     * @param position the position
     * @return the boolean
     */
    public boolean play(int position) {
        int[] coordinates = BoardUtils.positionToCoordinate(position, board.getBoardSideLength());
        Map.Entry<Board, Integer> move = getPlayersTurn().move(getBoard(), coordinates);

        // board move returns an error
        if (move.getValue() == 2) {
            return false;
        }

        setBoard(move.getKey());
        setTurnNumber(getTurnNumber() + 1);

        // updates players
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

        // draw
        if (isBoardFull())
            setFinished(true);

        return true;
    }
}
