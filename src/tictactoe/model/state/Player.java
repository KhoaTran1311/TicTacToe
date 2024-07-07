package tictactoe.model.state;

import java.util.*;


/**
 * The state of the Player.
 */
public class Player {

//---------------------------------------------------ENUMS-----------------------------------------------------

    /**
     * The enum Status.
     */
    public enum Status {
        /**
         * Won status.
         */
        WON,
        /**
         * In turn status.
         */
        IN_TURN,
        /**
         * Idle status.
         */
        IDLE
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * X type.
         */
        X,
        /**
         * O type.
         */
        O
    }

//---------------------------------------------------FIELDS-----------------------------------------------------

    private Status status;
    private final Type type;
    private final boolean isBot;

//---------------------------------------------------CONSTRUCTORS-------------------------------------------------

    /**
     * Instantiates a new Player.
     *
     * @param type the type
     */
    public Player(Type type) {
        this.type = type;
        this.status = Status.IDLE;
        this.isBot = false;
    }

    public Player(Type type, boolean isBot) {
        this.type = type;
        this.status = Status.IDLE;
        this.isBot = isBot;
    }

//-----------------------------------------------GETTERS & SETTERS-----------------------------------------------

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    public boolean isBot() {
        return isBot;
    }

    //---------------------------------------------------FUNCTIONS-----------------------------------------------------

    /**
     * Move the player to a new position
     *
     * @param board    the board
     * @param coordinates the coordinates
     * @return the Entry Map of the new board and result of the move
     */
    public Map.Entry<Board, Integer> move(Board board, int[] coordinates) {
        Board boardCopy = new Board(board);

        int result = boardCopy.move(coordinates[0], coordinates[1], getType());

        if (result == 1) setStatus(Status.WON);

        return new AbstractMap.SimpleEntry<>(boardCopy, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return getType() == player.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType());
    }
}
