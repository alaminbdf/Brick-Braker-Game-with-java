import java.awt.*;

public class Paddle {
    private int x, y;
    private final int width = 100;
    private final int height = 10;
    private final int speed = 20;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
        }
    }

    public void moveRight(int screenWidth) {
        if (x + width < screenWidth) {
            x += speed;
        }
    }
}
