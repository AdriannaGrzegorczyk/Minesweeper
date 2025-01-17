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

    public void printBorad (){

        for (int i =0; i< board.size();i++){
            for (int j=0; j<board.get(i).size();j++){
                System.out.print(board.get(i).get(j));
            }
            System.out.println();
        }
    }

    public void fillBoardWithRandomMines () {

        Random random = new Random();
        random.nextInt(1,10);

        for (int i=0; i<this.mines; i++){
            int randomHeight = random.nextInt(height);
            int randomWidth = random.nextInt(width);

            if (board.get(randomHeight).get(randomWidth).equals('X')){
                i--;
            }else{
                board.get(randomHeight).set(randomWidth,'X');
            }
        }
    }

}
