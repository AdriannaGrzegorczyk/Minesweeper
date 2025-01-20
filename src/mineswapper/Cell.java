package mineswapper;

public class Cell {

    boolean isMine;
    boolean isNumber;
    boolean isFlagged;
    int numberOfMinesAround=0;
    private boolean isVisible = false;


    boolean isFreeField(){
        return !isMine && numberOfMinesAround==0;
    }

    public int getNumberOfMinesAround() {
        return numberOfMinesAround;
    }

    public void setNumberOfMinesAround(int numberOfMinesAround) {
        this.numberOfMinesAround = numberOfMinesAround;
    }

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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public char getPrintableValue(boolean shouldReveal) {

        if (!shouldReveal && !isVisible && isFlagged){
            return Fields.MARKED_FIELD;
        }else if ((!shouldReveal|| isVisible) && isMine){
            return Fields.NON_MARKED_FIELD;
        } else if ((shouldReveal|| isVisible) && isMine){
            return  Fields.MINE;
        }else if (isVisible && numberOfMinesAround >0){
            return (char) (numberOfMinesAround+'0');
        }else if (isVisible){
            return Fields.FREE_FIELD;
        }
        else{
            return  Fields.NON_MARKED_FIELD;
        }
    }

}
