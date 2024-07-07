package tictactoe.model.state;

import java.util.*;


/**
 * The state representation of the Board
 */
public class Board {

//---------------------------------------FIELDS-------------------------------------------------

    private Player.Type[][] boardState; // current board boardState(this represents the players' positions and empty cells)
    private final int boardSideLength; // length of the board
    private final int boardSize; // the total number of boxes in the board

//---------------------------------------CONSTRUCTORS-------------------------------------------------

    /**
     * Instantiates a new Board with the default length of 3
     */
    public Board() {
        boardSideLength = 3;
        boardState = new Player.Type[boardSideLength][boardSideLength];
        boardSize = boardSideLength * boardSideLength;
    }

    /**
     * Instantiates a new Board.
     *
     * @param boardSideLength the board side length
     */
    public Board(int boardSideLength) {
        this.boardSideLength = boardSideLength;
        boardState = new Player.Type[boardSideLength][boardSideLength];
        boardSize = boardSideLength * boardSideLength;
    }

    /**
     * Create a Deep copy of a board
     *
     * @param board the board
     */
    public Board(Board board) {
        this.boardSideLength = board.boardSideLength;
        Player.Type[][] copiedState = new Player.Type[boardSideLength][boardSideLength];

        this.boardSize = board.boardSize;

        for (int i = 0; i < boardSideLength; i++) {
            System.arraycopy(board.boardState[i], 0, copiedState[i], 0, boardSideLength);
        }

        this.boardState = copiedState;
    }

//--------------------------------GETTERS & SETTERS--------------------------------------------------------

    /**
     * Gets board size – which is the area of the board.
     *
     * @return the board size
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Get the current boardState of the board.
     *
     * @return the board state
     */
    public Player.Type[][] getBoardState() {
        return boardState;
    }

    /**
     * Sets boardState.
     *
     * @param boardState the boardState
     */
    public void setBoardState(Player.Type[][] boardState) {
        this.boardState = boardState;
    }

    /**
     * Gets board side length.
     *
     * @return the board side length
     */
    public int getBoardSideLength() {
        return boardSideLength;
    }

//------------------------------------------FUNCTIONS----------------------------------------------

    /**
     * Move int.
     * <ul>
     *     <li><b>0</b>: not won</li>
     *     <li><b>1</b>: won</li>
     *     <li><b>2</b>: error</li>
     * </ul>
     *
     * @param x      the x
     * @param y      the y
     * @param playerType the type of the player
     * @return the int
     */
    public int move(int x, int y, Player.Type playerType) {
        if (getBoardState()[x][y] != null) {
            return 2;
        }

        setBoardState(updatedBoardStateOnMove(x, y, playerType));

        // column check
        if (columnCheck(x, playerType, x, y) == Math.min(5, getBoardSideLength())) return 1; // won

        // row check
        if (rowCheck(y, playerType, x, y) == Math.min(5, getBoardSideLength())) return 1; // won

        // diagonal check
        if (diagonalCheck(x, y, playerType, x, y) == Math.min(5, getBoardSideLength())) return 1; // won

        return 0;
    }

    /**
     * Helper – Update the board state when a play move
     * @param x x-position
     * @param y y-position
     * @param playerType the Type of the player
     * @return the new board state
     */
    private Player.Type[][] updatedBoardStateOnMove(int x, int y, Player.Type playerType) {
        Player.Type[][] copiedBoardState = new Player.Type[getBoardSideLength()][getBoardSideLength()];

        // deep copy array
        for (int i = 0; i < getBoardSideLength(); i++) {
            System.arraycopy(getBoardState()[i], 0, copiedBoardState[i], 0, getBoardSideLength());
        }

        // add new move
        copiedBoardState[x][y] = playerType;

        return copiedBoardState;
    }

    /**
     * Helper – Check column for maximum plays a player has from a coordinate. Uses DFS.
     * @param x the current x position
     * @param playerType the type of the player
     * @param originalX the starting x-position
     * @param originalY the starting y-position
     * @return the maximum plays a number have in the given column
     */
    private int columnCheck(int x, Player.Type playerType, int originalX, int originalY) {
        if (x < 0 || x > getBoardSideLength() - 1 || getBoardState()[x][originalY] != playerType) {
            return 0;
        }

        if (originalX < x) {
            return 1 + columnCheck(x + 1, playerType, originalX, originalY);
        } else if (originalX > x) {
            return 1 + columnCheck(x - 1, playerType, originalX, originalY);
        }

        return 1 +
                columnCheck(x + 1, playerType, originalX, originalY) +
                columnCheck(x - 1, playerType, originalX, originalY);
    }

    /**
     * Helper – Check row for maximum plays a player has from a coordinate. Uses DFS.
     * @param y the current y position
     * @param playerType the type of the player
     * @param originalX the starting x-position
     * @param originalY the starting y-position
     * @return the maximum plays a number have in the given row
     */
    private int rowCheck(int y, Player.Type playerType, int originalX, int originalY) {
        if (y < 0 || y > getBoardSideLength() - 1 || getBoardState()[originalX][y] != playerType) {
            return 0;
        }

        if (originalY < y) {
            return 1 + rowCheck(y + 1, playerType, originalX, originalY);
        } else if (originalY > y) {
            return 1 + rowCheck(y - 1, playerType, originalX, originalY);
        }

        return 1 +
                rowCheck(y + 1, playerType, originalX, originalY) +
                rowCheck(y - 1, playerType, originalX, originalY);
    }

    /**
     * Helper – Check the diagonals for maximum plays a player has from a coordinate. Uses DFS.
     * @param x the current x position
     * @param y the current y position
     * @param playerType the type of the player
     * @param originalX the starting x-position
     * @param originalY the starting y-position
     * @return the maximum plays a number have in the given column
     */
    private int diagonalCheck(int x, int y, Player.Type playerType, int originalX, int originalY) {
        if (x < 0 || x > getBoardSideLength() - 1 || y < 0 || y > getBoardSideLength() - 1 || getBoardState()[x][y] != playerType) {
            return 0;
        }

        if (x != originalX || y != originalY) {
            if (originalX < x && originalY < y) {
                return 1 + diagonalCheck(x + 1, y + 1, playerType, originalX, originalY); // SE
            } else if (originalX > x && originalY > y) {
                return 1 + diagonalCheck(x - 1, y - 1, playerType, originalX, originalY); // NW
            } else if (originalX < x && originalY > y) {
                return 1 + diagonalCheck(x + 1, y - 1, playerType, originalX, originalY); // SW
            }
            return 1 + diagonalCheck(x - 1, y + 1, playerType, originalX, originalY); // NE
        }

        return 1 + Math.max(
                (diagonalCheck(x + 1, y + 1, playerType, originalX, originalY) +
                        diagonalCheck(x - 1, y - 1, playerType, originalX, originalY)), // NW-SE diagonal
                (diagonalCheck(x + 1, y - 1, playerType, originalX, originalY) +
                        diagonalCheck(x - 1, y + 1, playerType, originalX, originalY))); // NE-SW diagonal
    }

//-------------------------------------------TO STRING----------------------------------------------------

    @Override
    public String toString() {
        return "BoardUtils{\n" +
                "  " + Arrays.toString(boardState[0]) + "\n" +
                "  " + Arrays.toString(boardState[1]) + "\n" +
                '}';
    }
}
