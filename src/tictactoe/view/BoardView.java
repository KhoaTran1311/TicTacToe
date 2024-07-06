package tictactoe.view;

import tictactoe.model.state.Board;
import tictactoe.model.state.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The type Board view.
 */
public class BoardView {
    private BoardView() {
    }

    /**
     * Gets view.
     *
     * @param board the board
     */
    public static void getView(Board board) {
        Player.Type[][] boardState = board.getBoardState();
        int boardSide = board.getBoardSideLength();
        int boardSize = boardSide * boardSide;
        List<String> boardList = new ArrayList<>(boardSize);
        String[] stringRepresentation = new String[boardSize];

        int counter = 1;
        for (int i = 0; i < boardSide; i++) {
            for (int j = 0; j < boardSide; j++) {
                if (boardState[i][j] == null) {
                    boardList.add(String.valueOf(counter));
                } else {
                    boardList.add(boardState[i][j].toString());
                }
                counter++;
            }
        }

        int currRow = 0;
        String[] rowStrings = new String[3];
        Arrays.fill(rowStrings, "");
        for (int i = 0; i < boardSize; i++) {
            BoxView boxView = new BoxView(boardList.get(i), i, boardSide);

            if (i / boardSide == currRow) {
                rowStrings[0] += boxView.getLines()[0];
                rowStrings[1] += boxView.getLines()[1];
                rowStrings[2] += boxView.getLines()[2];
            }
            if (i % boardSide == boardSide - 1) {
                System.arraycopy(rowStrings, 0, stringRepresentation, i / boardSide * 3, 3);
                rowStrings = new String[3];
                Arrays.fill(rowStrings, "");
                currRow++;
            }
        }

        for (int i = 0; i < boardSide * 3; i++) {
            System.out.println(stringRepresentation[i]);
        }
    }
}
