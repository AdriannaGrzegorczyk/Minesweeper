package mineswapper;

public class Cell {

    boolean isMine;
    boolean isNumber;
    boolean isFlagged;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(boolean number) {
        isNumber = number;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
