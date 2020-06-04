import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeGame {
    private static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        int menu;


        while (true) {
            System.out.print("[Question] Choose menu(1. Start Game, 2. Load Game, 0. Exit) > ");
            menu = kb.nextInt();

            switch (menu) {
            case 1:
                Quiz.setQuiz(); // to set the list of quiz game (sub game)
                startMaze();
                break;
            case 2:
                Quiz.setQuiz();
                loadGame();
                break;
            default:
                break;
            }

            if (menu == 0) {
                break;
            }
        }

        kb.close();
    }

    public static void startMaze() {
        System.out.println("[Information] If you want to save, press v");
        Maze maze = new Maze("maze2.txt");
        String c;

        kb.nextLine();
        while (true) {
            if (maze.checkVictory()) {
                System.out.println("[Information] You win!!");
                break;
            }
            if (maze.checkDefeat()) {
                System.out.println("[Information] You lose!!");
                break;
            }
            maze.printMaze();
            System.out.print("[Question] Where to go?(w a s d) > ");
            c = kb.nextLine();
            if (c.equals("v")) {
                maze.saveFile();
                System.out.println("[System] Succeed to save!");
                continue;
            }
            if (!maze.movePlayer(c)) {
                System.out.println("[Information] You cannot go there!");
                continue;
            }
            maze.occurSubgame(); // added for playing sub game.
        }
    }

    public static void loadGame() {
        String filename = "../save/save.txt";
        Scanner inputStream = null;
        try {
            int x, y, v, l;
            int yLen, xLen;
            int mazeArr[][];
            inputStream = new Scanner(new File(filename));
            x = inputStream.nextInt();
            y = inputStream.nextInt();
            v = inputStream.nextInt();
            l = inputStream.nextInt();
            yLen = inputStream.nextInt();
            xLen = inputStream.nextInt();

            mazeArr = new int[yLen][xLen];
            for (int i = 0; i < yLen; i++) {
                for (int j = 0; j < xLen; j++) {
                    mazeArr[i][j] = inputStream.nextInt();
                }
            }

            System.out.println("[Information] If you want to save, press v");
            Maze maze = new Maze(mazeArr, x, y, v, l);
            String c;
    
            kb.nextLine();
            while (true) {
                if (maze.checkVictory()) {
                    System.out.println("[Information] You win!!");
                    break;
                }
                if (maze.checkDefeat()) {
                    System.out.println("[Information] You lose!!");
                    break;
                }
                maze.printMaze();
                System.out.print("[Question] Where to go?(w a s d) > ");
                c = kb.nextLine();
                if (c.equals("v")) {
                    maze.saveFile();
                    System.out.println("[System] Succeed to save!");
                    continue;
                }
                if (!maze.movePlayer(c)) {
                    System.out.println("[Information] You cannot go there!");
                    continue;
                }
                maze.occurSubgame(); // added for playing sub game.
            }
        } catch (FileNotFoundException e) {
            System.out.println("[Error] Error opening file ");
            System.exit(0);
        }
    }
}
