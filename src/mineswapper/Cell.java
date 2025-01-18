package mineswapper;

public class Cell {

    boolean isMine;
    boolean isNumber;
    boolean isFlagged;

    public int getNumberOfMinesAround() {
        return numberOfMinesAround;
    }

    public void setNumberOfMinesAround(int numberOfMinesAround) {
        this.numberOfMinesAround = numberOfMinesAround;
    }

    int numberOfMinesAround=0;

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

    public char getPrintableValue(boolean shouldReveal) {

        if (!shouldReveal && isFlagged){
            return Fields.MARKED_FIELD;
        }else if (!shouldReveal && isMine){
            return Fields.NON_MARKED_FIELD;
        } else if (shouldReveal && isMine){
            return  Fields.MINE;
        }else if (numberOfMinesAround>0){
            return (char) (numberOfMinesAround+'0');
        }
        else{
            return  Fields.NON_MARKED_FIELD;
        }
    }

}
