package mineswapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int width=9;
    private int height=9;
    private int mines;
    private List<List<Character>> board = new ArrayList<>();


    public Board() {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner= new Scanner(System.in);
        mines = scanner.nextInt();
        for (int i = 0; i < height; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
            board.get(i).add(Fields.NON_MARKED_FIELD);
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
                char cellValue = board.get(i).get(j);
                if (!revealMines && cellValue == Fields.MINE) {
                    System.out.print(Fields.NON_MARKED_FIELD);

                } else if (!revealMines && cellValue == 'M') {
                    System.out.print(Fields.MARKED_FIELD);
                } else {
                    System.out.print(cellValue);
                }
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

            if (board.get(randomHeight).get(randomWidth).equals(Fields.MINE)){
                i--;
            }else{
                board.get(randomHeight).set(randomWidth,Fields.MINE);
            }
        }
    }

    public void insertNeighboursCount(){

        for (int i =0;i<height;i++){
            for (int j = 0; j<width; j++){
                int amountOfMines = calculateNeighboursMines(i,j);
                char mines =(char)(amountOfMines+'0');
                if(board.get(i).get(j).equals('X')){

                }else if(amountOfMines>0){
                    board.get(i).set(j,mines);
                }else{
                    board.get(i).set(j,'.');
                }

            }
        }
    }
    private  int calculateNeighboursMines (int i, int j){


        int amountOfMines=0;


        if (j+1<width && board.get(i).get(j+1)=='X'){
            amountOfMines++;
        }

        if (i+1<height && j+1<width && board.get(i+1).get(j+1)=='X'){
            amountOfMines++;
        }

        if (i+1 < height  &&  board.get(i+1).get(j) =='X'){
            amountOfMines++;
        }

        if (i+1<height && j-1>=0 && board.get(i+1).get(j-1)=='X'){
            amountOfMines++;
        }

        if ( j-1>=0 && board.get(i).get(j-1)=='X'){
            amountOfMines++;
        }

        if (i-1>=0 && j-1>=0 && board.get(i-1).get(j-1)=='X'){
            amountOfMines++;
        }

        if (i-1>=0  && board.get(i-1).get(j)=='X'){
            amountOfMines++;
        }

        if (i-1>=0 && j+1<width  && board.get(i-1).get(j+1) == 'X' ){

            amountOfMines++;

        }
        return amountOfMines;
    }




}
