import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Maze {
    private String fileName;
    private Scanner fileStream;

    private int[][] maze;
    private int xLen;
    private int yLen;

    private Player player;

    public Maze(String fileName) {
        player = new Player(0, 0);

        this.fileName = "../mazes/" + fileName;
        try {
            fileStream = new Scanner(new File(this.fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file " + fileName);
            System.exit(0);
        }

        readFile();
    }

    public void readFile() {
        yLen = fileStream.nextInt();
        xLen = fileStream.nextInt();

        maze = new int[yLen][xLen];
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                maze[i][j] = fileStream.nextInt();
            }
        }
    }

    public void printMaze() {
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                if (player.getY() == i && player.getX() == j) {
                    System.out.print("Y ");
                    continue;
                }
                if (player.getY() - player.getVision() <= i && i <= player.getY() + player.getVision()) {
                    if (player.getX() - player.getVision() <= j && j <= player.getX() + player.getVision()) {
                        System.out.print(maze[i][j] + " ");
                        continue;
                    }
                }
                System.out.print("? ");
            }
            System.out.println();
        }
        System.out.println("\n( " + player.getX() + ", " + player.getY() + " )");
    }

    public boolean movePlayer(String c) {
        if (c.equals("w")) {
            if (player.getY() > 0 && maze[player.getY() - 1][player.getX()] == 1) {
                player.moveUp();
                return true;
            }
        }
        if (c.equals("a")) {
            if (player.getX() > 0 && maze[player.getY()][player.getX() - 1] == 1) {
                player.moveLeft();
                return true;
            }

        }
        if (c.equals("s")) {
            if (player.getY() < yLen && maze[player.getY() + 1][player.getX()] == 1) {
                player.moveDown();
                return true;
            }

        }
        if (c.equals("d")) {
            if (player.getX() < xLen && maze[player.getY()][player.getX() + 1] == 1) {
                player.moveRight();
                return true;
            }
        }
        return false;
    }

    public boolean check_victory() {
        if (player.getY() == yLen - 1 && player.getX() == xLen - 1) {
            return true;
        }
        return false;
    }

// added for playing subgame.
    public void occurSubgame(){

        // Sub-game ouccrs only when the point of player is same with "2" in maze text file. isSubgmae method checks that.
        if(!SubGame.isSubgame(player, maze)){
            return;
        }

        System.out.println("Sub-game event occurs!!!");

        if(SubGame.playSubgame()){
            System.out.println("Your ~~~~ is increased by one");
            System.out.println("Add some codes for increasing characteristic, here");
        }
        else{
            // other cases....
        }
    }
}
