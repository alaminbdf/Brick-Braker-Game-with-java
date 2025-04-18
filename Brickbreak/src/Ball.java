import java.awt.*;
import javax.swing.*;

public class Ball {
    private int x, y;
    private final int diameter = 20;
    private int dx = 2, dy = -2;
    private GamePanel gamePanel; // Reference to GamePanel

    public Ball(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move(int screenWidth, int screenHeight, Paddle paddle, java.util.List<Brick> bricks) {
        x += dx;
        y += dy;

        // Bounce off left/right walls
        if (x <= 0 || x + diameter >= screenWidth) {
            dx = -dx;
        }

        // Bounce off top wall
        if (y <= 0) {
            dy = -dy;
        }

        // Bounce off paddle
        if (getBounds().intersects(paddle.getBounds())) {
            dy = -dy;
        }

        // Check for game over
        if (y + diameter > screenHeight) {
            SoundPlayer.stopBackgroundMusic(); // ⏹ Stop music
            SoundPlayer.playSound("/sounds/game_over.wav"); // 🔊 Play game over sound
            JOptionPane.showMessageDialog(null, "Game Over! Score: " + gamePanel.getScore()+"\n This game made by Alamin. \n");
            System.exit(0);
        }
    }

    public void increaseSpeed() {
        if (dx > 0) dx++;
        else dx--;

        if (dy > 0) dy++;
        else dy--;

        // Prevent excessive speed
        if (Math.abs(dx) > 5) dx = dx > 0 ? 5 : -5;
        if (Math.abs(dy) > 5) dy = dy > 0 ? 5 : -5;
    }

    public void reverseDy() {
        dy = -dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }
}
