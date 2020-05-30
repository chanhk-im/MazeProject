public class Player {
    private int xPos;
    private int yPos;
    private int vision;

    public Player(int x, int y) {
        xPos = x;
        yPos = y;
        vision = 1;
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

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getVision() {
        return vision;
    }
}