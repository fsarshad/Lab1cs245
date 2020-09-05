package main;

import java.util.Random;
import java.util.Scanner;


//created different methods to create the program

//created a class to solve the sudoku project.
//

public class Solver {
    int[][] puzz = new int[9][9];
    int[][] gen = new int[9][9];
    Random rand;

    //this method lets you input the number and it checks if it is right
    //or wrong. by scanning the row and column for repeated num.

    public void playPuzzle() {
        int intInput = 1;
        boolean hold = false;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Scanner scanner = new Scanner(System.in);
                while (hold == false) {
                    while (intInput > 0 && intInput <= 9) {
                        System.out.print("input a number: ");
                        intInput = scanner.nextInt();
                        if (intInput > 0 && intInput <= 9) {
                            break;
                        }
                    }

                    hold = isAllowed(intInput, row, col, puzz);
                    if (hold == true) {
                        System.out.println(intInput + " is valid");
                    } else {
                        System.out.println(intInput + " not valid");
                    }

                }
                puzz[row][col] = intInput;
                hold = false;
                printPuzzle(puzz);
            }

        }

    }

    //lets you input a number
    //checks whether its valid or not.

    public void playPuzzle(int[][] req) {
        int intInput = 1;
        boolean hold = false;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Scanner scanner = new Scanner(System.in);
                while (hold == false) {
                    if (req[row][col] == 0) {
                        while (intInput > 0 && intInput <= 9) {
                            System.out.print("input a number: ");
                            intInput = scanner.nextInt();
                            if (intInput > 0 && intInput <= 9) {
                                break;
                            }
                        }

                        hold = isAllowed(intInput, row, col, req);
                        if (hold == true) {
                            System.out.println(intInput + " is valid");
                        } else {
                            System.out.println(intInput + " not valid");
                        }
                    }
                    req[row][col] = intInput;
                    hold = false;
                    printPuzzle(req);
                }

            }

        }
    }

    //constructor and getter

    public Solver() {
    }

    public int[][] getGen() {
        return gen;
    }

    // this method solves the sudoku problem pretty much

    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (gen[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isAllowed(num, row, col, gen)) {
                            gen[row][col] = num;
                            if (solve()) {
                                return true;
                            } else {
                                gen[row][col] = -1;
                            }
                        }
                        System.out.println("\n\n");
                        printPuzzle(gen);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //this method checks whether the input numbers meet the requirements
    //regarding numbers from 1-9 for rows, col, etc.

    public boolean isAllowed(int value, int row, int col, int[][] arr) {
        return !(containedInRow(value, row, col, arr) || containedInCol(value, row, col, arr) || containedInBox(value, row, col, arr));
    }

    //this method checks the numbers from 1-9 to see if they satisfy the
    //the row requirements.

    public boolean containedInRow(int value, int row, int col, int[][] arr) {
        for (int i = 0; i < 9; i++) {
            if (arr[i][col] == value) {
                return true;
            }
        }
        return false;
    }

    //this method checks the numbers from 1-9 to see if they satisfy the
    //the column requirements.

    public boolean containedInCol(int value, int row, int col, int[][] arr) {
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == value) {
                return true;
            }
        }
        return false;
    }

    //this method checks the numbers from 1-9 to see if they satisfy the
    //the column requirements

    public boolean containedInBox(int value, int row, int col, int[][] arr) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (arr[i][j] == value) {
                    return true;
                }
            }
        }

        return false;
    }

    // this method generates,use any number as long as it is in
    //between 1-9

    public int[] generateRandRow() {
        int[] randRow = new int[10];
        for (int i = 1; i < 10; i++) {
            randRow[i] = rand.nextInt(10);
        }

        return randRow;
    }

    //this method is what is printing the puzzle

    public void printPuzzle(int[][] was) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + was[i][j] + " ");
            }

            System.out.println("");
        }
    }
}