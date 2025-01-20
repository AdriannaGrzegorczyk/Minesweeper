package mineswapper;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.fillBoardWithRandomMines();
        board.insertNeighboursCount();
        board.printBorad(false);

        while (1 > 0) {
            board.provideCoordinates();
             if ((board.getMines() == board.getCounterOfStar())
                     || board.getCounterOfMines() == board.getHeight() * board.getWidth()
                     - board.getCounterOfReveleadFields()) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }

    }
}
