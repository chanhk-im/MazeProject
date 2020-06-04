import java.util.Scanner;
import java.util.Random;
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
        System.out.println();
        for (int i = 0; i < xLen + 2; i++) {
            System.out.print("= ");
        }
        System.out.println();
        for (int i = 0; i < yLen; i++) {
            System.out.print("| ");
            for (int j = 0; j < xLen; j++) {
                if (player.getY() == i && player.getX() == j) {
                    System.out.print("Y ");
                    continue;
                }
                if (player.getY() - player.getVision() <= i && i <= player.getY() + player.getVision()) {
                    
                    if (player.getX() - player.getVision() <= j && j <= player.getX() + player.getVision()) {
                        System.out.print(((maze[i][j] == 0) ? 0 : 1) + " ");
                        continue;
                    }
                }
                System.out.print("? ");
            }
            System.out.println("|");
        }
        for (int i = 0; i < xLen + 2; i++) {
            System.out.print("= ");
        }

        System.out.println();
        System.out.println("\n[Information] Your position: ( " + player.getX() + ", " + player.getY() + " )");
        System.out.println("[Information] Your remained life: " + player.getLife() + " / " + player.getMaxLife());
    }

    public boolean movePlayer(String c) {
        if (c.equals("w")) {
            if (player.getY() > 0 && maze[player.getY() - 1][player.getX()] >= 1) {
                player.moveUp();
                return true;
            }
        }
        if (c.equals("a")) {
            if (player.getX() > 0 && maze[player.getY()][player.getX() - 1] >= 1) {
                player.moveLeft();
                return true;
            }

        }
        if (c.equals("s")) {
            if (player.getY() < yLen && maze[player.getY() + 1][player.getX()] >= 1) {
                player.moveDown();
                return true;
            }

        }
        if (c.equals("d")) {
            if (player.getX() < xLen && maze[player.getY()][player.getX() + 1] >= 1) {
                player.moveRight();
                return true;
            }
        }
        return false;
    }

    public boolean checkVictory() {
        if (player.getY() == yLen - 1 && player.getX() == xLen - 1) {
            return true;
        }
        return false;
    }

    public boolean checkDefeat() {
        if (player.getLife() < 0) {
            return true;
        }
        return false;
    }

    public boolean isSubgame() {
        int pX = player.getX();
        int pY = player.getY();

        if (maze[pX][pY] == 2) {
            return true;
        }
        return false;
    }

// added for playing subgame.
    public void occurSubgame(){

        // Sub-game ouccrs only when the point of player is same with "2" in maze text file. isSubgmae method checks that.
        if(!isSubgame()){
            return;
        }

        System.out.println("Sub-game event occurs!!!");

        if(SubGame.playSubgame()){
            Random generator = new Random();
            int index = generator.nextInt(2) + 1;
            switch (index) {        // [chanhk-im]: case 1->life up, case 2->vision up
                case 1:
                    player.lifeUp(1);
                    System.out.println("Your life is increased by one");
                    break;
                case 2:
                    player.visionUp(1);
                    System.out.println("Your vision is increased by one");
                    break;
                default:
                    break;
            }
            // System.out.println("Your ~~~~ is increased by one");
            System.out.println("Add some codes for increasing characteristic, here");
        }
        else{
            // other cases....
        }
        maze[player.getX()][player.getY()] = 1;
    }
}
