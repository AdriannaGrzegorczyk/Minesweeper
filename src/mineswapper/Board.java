package mineswapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {

    public int getCounterOfMines() {
        return counterOfMines;
    }

    public int getCounterOfStar() {
        return counterOfStar;
    }

    private int counterOfMines=0;
    private int counterOfStar=0;

    public int getCounterOfReveleadFields() {
        return counterOfReveleadFields;
    }

    private int counterOfReveleadFields=0;
    private int width=9;
    private int height=9;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMines() {
        return mines;
    }

    private int mines;
    private List<List<Cell>> board = new ArrayList<>();


    public Board() {

        System.out.println("How many mines do you want on the field?");
        Scanner scanner= new Scanner(System.in);
        mines = scanner.nextInt();
        for (int i = 0; i < height; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
            board.get(i).add(new Cell());
            }
        }
    }

    public void printBorad (boolean revealMines){

        System.out.print(" |");
        for (int r = 0; r < height; r++) {
            System.out.print(r + 1);
            if (r == height - 1) {
                System.out.println("|");
            }
        }

        System.out.print("-|");
        for (int y = 0; y < height; y++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int i = 0; i < board.size(); i++) {
            System.out.print(i + 1);
            System.out.print("|");
            for (int j = 0; j < board.get(i).size(); j++) {
                char cellValue = board.get(i).get(j).getPrintableValue(revealMines);
                System.out.print(cellValue);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.print("-|");
        for (int h = 0; h < height; h++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    public void fillBoardWithRandomMines () {

        Random random = new Random();
        random.nextInt(1,10);

        for (int i=0; i<this.mines; i++){
            int randomHeight = random.nextInt(height);
            int randomWidth = random.nextInt(width);

            if (board.get(randomHeight).get(randomWidth).isMine){
                i--;
            }else{
                board.get(randomHeight).get(randomWidth).setMine(true);
            }
        }
    }

    public void insertNeighboursCount(){

        for (int i =0;i<height;i++){
            for (int j = 0; j<width; j++){
                int amountOfMines = calculateNeighboursMines(i,j);
                if(!board.get(i).get(j).isMine){
                    board.get(i).get(j).setNumberOfMinesAround(amountOfMines);
                }
            }
        }
    }
    private  int calculateNeighboursMines (int i, int j){

        int amountOfMines=0;

        if (j+1<width && board.get(i).get(j+1).isMine()){
            amountOfMines++;
        }
        if (i+1<height && j+1<width && board.get(i+1).get(j+1).isMine()){
            amountOfMines++;
        }
        if (i+1 < height  &&  board.get(i+1).get(j) .isMine()){
            amountOfMines++;
        }
        if (i+1<height && j-1>=0 && board.get(i+1).get(j-1).isMine()){
            amountOfMines++;
        }
        if ( j-1>=0 && board.get(i).get(j-1).isMine()){
            amountOfMines++;
        }
        if (i-1>=0 && j-1>=0 && board.get(i-1).get(j-1).isMine()){
            amountOfMines++;
        }
        if (i-1>=0  && board.get(i-1).get(j).isMine()){
            amountOfMines++;
        }
        if (i-1>=0 && j+1<width  && board.get(i-1).get(j+1).isMine()){
            amountOfMines++;
        }
        return amountOfMines;
    }

    public void provideCoordinates (){
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt(), x = scanner.nextInt();
        String type = scanner.next();
        Cell selectedField = board.get(x-1).get(y-1);

        if (type.equals("free")){
            if(selectedField.isMine()){
                printBorad(true);
                System.out.println("You stepped on a mine and failed!");
                System.exit(1);
            }else{
                recurrenceBoard(board, x-1, y-1);
            }
        }else if (type.equals("mine")){
            if (selectedField.isVisible() && selectedField.getNumberOfMinesAround()>0){
                System.out.println("There is a number here!");
            }else if (!selectedField.isFlagged()){
                selectedField.setFlagged(true);
                if(selectedField.isMine){
                    counterOfStar++;
                }
            }else if (selectedField.isFlagged()){
                selectedField.setFlagged(false);
                if(selectedField.isMine){
                    counterOfStar--;
                }
            }
        }

        printBorad( false);

    }

    private void recurrenceBoard(List<List<Cell>> board, int x, int y) {

        if (y < 0 || x < 0 || x >= board.size() || y >= board.get(x).size()) {
            return;
        }
        if (board.get(x).get(y).isMine()) {
            return;
        }
        if (board.get(x).get(y).getNumberOfMinesAround() > 0) {
            if(!board.get(x).get(y).isVisible()){
                board.get(x).get(y).setVisible(true);
                counterOfReveleadFields++;
            }
        }
        else {
            if (board.get(x).get(y).isVisible()) {
                return;
            }
            board.get(x).get(y).setVisible(true);
            counterOfReveleadFields++;

            recurrenceBoard(board, x + 1, y);
            recurrenceBoard(board, x - 1, y);
            recurrenceBoard(board, x, y - 1);
            recurrenceBoard(board, x, y + 1);
            recurrenceBoard(board, x + 1, y + 1);
            recurrenceBoard(board, x + 1, y - 1);
            recurrenceBoard(board, x - 1, y + 1);
            recurrenceBoard(board, x - 1, y - 1);
        }
    }
}
