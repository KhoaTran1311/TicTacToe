package tictactoe.view;

import tictactoe.model.state.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardView {
    public static void getView(Board board) {
        int[][] boardState = board.getState();
        int boardSide = board.getBoardSideLength();
        int boardSize = boardSide * boardSide;
        List<String> boardList = new ArrayList<>(boardSize);
        String[] stringRepresentation = new String[boardSize];

        int counter = 1;
        for (int i = 0; i < boardSide; i++) {
            for (int j = 0; j < boardSide; j++) {
                if (boardState[i][j] == 1) {
                    boardList.add("x");
                } else if (boardState[i][j] == 2) {
                    boardList.add("o");
                } else {
                    boardList.add(String.valueOf(counter));
                }
                counter++;
            }
        }

        int currRow = 0;
        String[] rowStrings = new String[3];
        Arrays.fill(rowStrings, "");
        for (int i = 0; i < boardSize; i++) {
            BoxView boxView = new BoxView(boardList.get(i), i);

            if (i / boardSide == currRow) {
                rowStrings[0] += boxView.getLines()[0];
                rowStrings[1] += boxView.getLines()[1];
                rowStrings[2] += boxView.getLines()[2];
            }
            if (i % boardSide == boardSide - 1) {
                for (int j = 0; j < 3; j++) {
                    stringRepresentation[i - (boardSide - 1 - j)] = rowStrings[j];
                }
                rowStrings = new String[3];
                Arrays.fill(rowStrings, "");
                currRow ++;
            }
        }
        for (int i = 0; i < boardSize; i++) {
            System.out.println(stringRepresentation[i]);
        }
    }
}
