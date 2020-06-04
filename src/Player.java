public class Player {
    private int xPos;
    private int yPos;
    private int vision;
    private int life;
    private static int maxLife;

    public Player(int x, int y) {
        xPos = x;
        yPos = y;
        vision = 1;
        life = 3;
        maxLife = 5;
    }

    public void moveRight() {
        xPos += 1;
    }

    public void moveLeft() {
        xPos -= 1;
    }

    public void moveUp() {
        yPos -= 1;
    }

    public void moveDown() {
        yPos += 1;
    }

    public void visionUp(int v) {
        vision += v;
    }

    public void lifeUp(int l) {
        if (life + l <= maxLife) {
            life += l;
        }
        else {
            life = maxLife;
        }
    }

    public void lifeDown(int l) {
        life -= l;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getVision() {
        return vision;
    }

    public int getLife() {
        return life;
    }
    public int getMaxLife() {
        return maxLife;
    }
}