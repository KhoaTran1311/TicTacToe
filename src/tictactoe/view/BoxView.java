package tictactoe.view;

/**
 * The type Box view.
 */
public class BoxView {
    private final String[] lines = new String[3];
    private final int position;
    private final int boardSide;

    /**
     * Instantiates a new Box view.
     *
     * @param value     the value
     * @param position  the position
     * @param boardSide the board side
     */
    public BoxView(String value, int position, int boardSide) {
        boolean isBlank = Character.isDigit(value.charAt(0));

        this.position = position;

        this.boardSide = boardSide;

        StringBuilder[] linesSb = new StringBuilder[]{new StringBuilder(), new StringBuilder(), new StringBuilder()};

        int boardSize = boardSide * boardSide;
        if (isBlank) {
            setBlankFirstLines(value, linesSb);

            if (position < boardSize - boardSide) {
                linesSb[2].append("_______");
            } else {
                linesSb[2].append("       ");
            }
        } else if (value.equals("X")) {
            linesSb[0].append("  ┏┓┏┓ ");
            linesSb[1].append("   ┃┃  ");
            if (position < boardSize - boardSide) {
                linesSb[2].append("__┗┛┗┛_");
            } else {
                linesSb[2].append("  ┗┛┗┛ ");
            }
        } else {
            linesSb[0].append("   ┏┓  ");
            linesSb[1].append("   ┃┃  ");
            if (position < boardSize - boardSide) {
                linesSb[2].append("___┗┛__");
            } else {
                linesSb[2].append("   ┗┛  ");
            }
        }

        setVerticalBorder(linesSb);

        lines[0] = linesSb[0].toString();
        lines[1] = linesSb[1].toString();
        lines[2] = linesSb[2].toString();
    }

    /**
     * set the empty space to be rendered
     * @param value numbers
     * @param linesSb the lines to be renders
     */
    private void setBlankFirstLines(String value, StringBuilder[] linesSb) {
        linesSb[0].append("       ");
        linesSb[1].append("   ").append(value).append("   ");
        if (Integer.parseInt(value) >= 10) linesSb[1].deleteCharAt(6);
        if (Integer.parseInt(value) >= 100) linesSb[1].deleteCharAt(1);
    }

    /**
     * set the vertical border to be rendered
     * @param linesSb the lines to be rendered
     */
    private void setVerticalBorder(StringBuilder[] linesSb) {
        if (position % boardSide == 0) {
            linesSb[0].append("|");
            linesSb[1].append("|");
            linesSb[2].append("|");
        } else if (position % boardSide != 1) {
            linesSb[0].insert(0, "|");
            linesSb[1].insert(0, "|");
            linesSb[2].insert(0, "|");
        }
    }

    /**
     * Get lines.
     *
     * @return the lines to be rendered
     */
    public String[] getLines() {
        return lines;
    }
}
