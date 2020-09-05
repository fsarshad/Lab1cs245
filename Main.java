package main;

// this method is what executes the program
// it calls the methods from the solver.java class.

public class Main {
    public static void main(String[] args)
    {
        Solver solver = new Solver();
        solver.playPuzzle();

    }
    public static void partialInput(int[][] req){
        Solver solver = new Solver();
        solver.playPuzzle(req);
    }
}
