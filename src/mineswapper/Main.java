package mineswapper;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.fillBoardWithRandomMines();
        board.insertNeighboursCount();
        board.printBorad();

    }
}
