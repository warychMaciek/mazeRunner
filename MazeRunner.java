import java.util.Scanner;

public class MazeRunner {
    public static Maze myMap = new Maze();
    public static boolean wall = false;
    public static int moves = 0;

    public static void main(String[] args) {
        intro();

        while ((wall == true) || myMap.didIWin() == false) {
            userMove();
            moves++;
            movesMessage(moves);
        }

        if (myMap.didIWin() == true) {
            System.out.println("Congratulations, you made it out alive!\nAnd you did it in " + moves + " moves.");
            System.exit(0);
        }

    }

    public static void intro() {
        System.out.println("Welcome to Maze Runner!\nHere is your current position:");
        myMap.printMap();
    }

    public static String userMove() {
        Scanner input = new Scanner(System.in);
        System.out.print("Where would you like to move? (R, L, U, D) ");
        String direction = input.nextLine();
        direction = direction.toUpperCase();
        while (!direction.equals("R") && !direction.equals("L") && !direction.equals("U") && !direction.equals("D")) {
            System.out.print("Where would you like to move? (R, L, U, D) ");
            direction = input.nextLine();
            direction = direction.toUpperCase();
        }

        if (direction.equals("R") && myMap.canIMoveRight() == true) {
            myMap.moveRight();
        }
        else if (direction.equals("L") && myMap.canIMoveLeft() == true) {
            myMap.moveLeft();
        }
        else if (direction.equals("D") && myMap.canIMoveDown() == true) {
            myMap.moveDown();
        }
        else if (direction.equals("U") && myMap.canIMoveUp() == true) {
            myMap.moveUp();
        }
        else {
            if (myMap.isThereAPit(direction) == true) {
                navigatePit(direction);
            }
            else {
                System.out.println("Sorry, you've hit the wall.");
                wall = true;
            }
        }

        myMap.printMap();
        return direction;
    }

    public static void movesMessage(int moves) {
        switch (moves) {
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.");
                break;
            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 move left to escape.");
                break;
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!!");
                break;
            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER>:[\nSorry, but you didn't escape in time - you lose!");
                System.exit(0);
                break;
        }
    }

    public static void navigatePit(String direction) {
        Scanner input = new Scanner(System.in);
        System.out.print("Watch out! There's a pit ahead, jump it? ");
        String jump = input.nextLine();
        jump = jump.toLowerCase();
        if (jump.equals("yes") || jump.equals("y")) {
            myMap.jumpOverPit(direction);
        }
        else {
            userMove();
        }
    }



}
