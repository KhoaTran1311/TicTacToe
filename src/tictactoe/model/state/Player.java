package tictactoe.model.state;

import java.util.*;

public class Player {
    public enum Status {
        WON,
        LOST,
        DRAW,
        IN_TURN,
        IDLE
    }

    public enum Type {
        X,
        O
    }

    private int id;
    private Status status;
    private final Type type;

    public Player(int id, Type type, Status status) {
        this.id = id;
        this.status = status;
        this.type = type;
    }

    public Player(int id, Type type) {
        this.id = id;
        this.type = type;
        this.status = Status.IDLE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Map.Entry<Board, Integer> move(Board board, int position) {
        Board boardCopy = new Board(board);

        int x = (position - 1) / board.getBoardSideLength();
        int y = (position - 1) - x * board.getBoardSideLength();

        int playerTypeInt = getType().equals(Type.X) ? 1 : 2;
        int result = boardCopy.move(x, y, playerTypeInt);

        if (board.isFull()) status = Status.DRAW;
        if (result == 1) status = Status.WON;

        return new AbstractMap.SimpleEntry<>(boardCopy, result);
    }
}
