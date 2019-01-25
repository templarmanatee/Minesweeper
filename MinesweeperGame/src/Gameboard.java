import java.util.Random;
import java.util.Scanner;

public class Gameboard {

    Random rng = new Random();

    private int gridSize = 10;
    private int gridBuffer = 2;
    private Cell[][] grid = new Cell[gridSize][gridSize];

    boolean gameLost = false;

    int rowGuessed;
    int columnGuessed;

    private int bombTotal = 10;

    public void run() {
        placeBombs();
        peek();
        do {
            displayBoard();
            guess();
        }
        while (gameLost == false);
    }

    private void instantiateCells() {
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                grid[i][j] = new Cell();
    }

    private void placeBombs() {
        instantiateCells();

        int randomRow;
        int randomColumn;

        for (int i = bombTotal; i > 0;) {
            randomRow = rng.nextInt(8)+1;
            randomColumn = rng.nextInt(8)+1;

            if(grid[randomColumn][randomRow].getHasBomb() == false) {
                grid[randomColumn][randomRow].setHasBomb(true);
                i--;
            }
        }
    }

    private void guess() {
        Scanner guess = new Scanner(System.in);

        System.out.println("Row: ");
        rowGuessed = guess.nextInt();
        System.out.println("Column: ");
        columnGuessed = guess.nextInt();

        checkGuess(grid[rowGuessed][columnGuessed]);
    }

    private void checkGuess(Cell toCheck) {
        if (toCheck.getHasBomb() == false) {
            checkAdjacent(toCheck);
        }
        else {
            gameLost = true;
            System.out.println("Game lost!"); //Update later
        }
    }

    private void checkAdjacent(Cell centerCell) {
        int[] adjacent = {-1,0,1};
        int bombsAdjacent = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (grid[rowGuessed][columnGuessed].getHasBomb())
                    bombsAdjacent++;
            }
        }
        grid[rowGuessed][columnGuessed].setContents(Integer.toString(bombsAdjacent));
    }

    private void displayBoard() {
        for(int i = 1; i <= gridSize-gridBuffer; i++) {
            for (int j = 1; j <= gridSize-gridBuffer; j++) {
                System.out.print(grid[i][j].getContents());
            }
            System.out.println();
        }
    }

    private void peek() {
        System.out.println("Would you like to peek? Type Y to do so.");
        Scanner peeker = new Scanner (System.in);
        String cheater = peeker.next();

        if (cheater.equals("Y")) {
            for (int i = 1; i <= gridSize - gridBuffer; i++) {
                for (int j = 1; j <= gridSize - gridBuffer; j++) {
                    if (grid[i][j].getHasBomb())
                        grid[i][j].setContents("M");
                }
                System.out.println();
            }
        }
    }
}
