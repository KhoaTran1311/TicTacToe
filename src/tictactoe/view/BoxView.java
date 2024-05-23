package tictactoe.view;

public class BoxView {
    private final boolean isBlank;
    private final String value;
    private final String[] lines = new String[3];
    private final int position;
    private final int boardSide;

    public BoxView(String value, int position, int boardSide) {
        this.value = value;
        isBlank = Character.isDigit(value.charAt(0));
        this.position = position;

        this.boardSide = boardSide;

        StringBuilder[] linesSb = new StringBuilder[]{new StringBuilder(), new StringBuilder(), new StringBuilder()};

        int boardSize = boardSide * boardSide;
        if (isBlank) {
            linesSb[0].append("       ");
            linesSb[1].append("   ").append(value).append("   ");
            if (Integer.parseInt(value) >= 10) linesSb[1].deleteCharAt(6);
            if (Integer.parseInt(value) >= 100) linesSb[1].deleteCharAt(1);


            if (position < boardSize - boardSide) {
                linesSb[2].append("_______");
            } else {
                linesSb[2].append("       ");
            }
        }
        else if (value.equals("x")) {
            linesSb[0].append("  ┏┓┏┓ ");
            linesSb[1].append("   ┃┃  ");
            if (position < boardSize - boardSide) {
                linesSb[2].append("__┗┛┗┛_");
            } else {
                linesSb[2].append("  ┗┛┗┛ ");
            }
        }
        else {
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

    public int getboardSide() {
        return boardSide;
    }

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

    public boolean isBlank() {
        return isBlank;
    }

    public String getValue() {
        return value;
    }

    public String[] getLines() {
        return lines;
    }

    public int getPosition() {
        return position;
    }
}
