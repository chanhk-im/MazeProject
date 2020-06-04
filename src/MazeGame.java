import java.util.Scanner;

public class MazeGame {
    private static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        int menu;


        while (true) {
            System.out.print("Choose menu(1. Start Game, 0. Exit) > ");
            menu = kb.nextInt();

            switch (menu) {
            case 1:
                Quiz.setQuiz(); // to set the list of quiz game (sub game)
                startMaze();
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

            if (!maze.movePlayer(c)) {
                System.out.println("[Information] You cannot go there!");
                continue;
            }
            maze.occurSubgame(); // added for playing sub game.
        }
    }

}
