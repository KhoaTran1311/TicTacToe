package tictactoe.utils;


/**
 * The type Board utils.
 */
public class BoardUtils {
    private BoardUtils() {}

    /**
     * Coordinate to position int.
     *
     * @param x               the x
     * @param y               the y
     * @param boardSideLength the board side length
     * @return the int
     */
    public static int coordinateToPosition(int x, int y, int boardSideLength) {
        return x * boardSideLength + y + 1;
    }

    /**
     * Position to coordinate. <br/>
     * <ul>
     *     <li>[0]: x</li>
     *     <li>[1]: y</li>
     * </ul>
     *
     * @param position        the position
     * @param boardSideLength the board side length
     * @return the int array where first position is x and second is y
     */
    public static int[] positionToCoordinate(int position, int boardSideLength) {
        int[] out = new int[2];
        int truePosition = position - 1;
        out[0] = truePosition / boardSideLength;
        out[1] = truePosition % boardSideLength;
        return out;
    }
}
