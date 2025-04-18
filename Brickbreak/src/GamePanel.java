import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private int score = 0;
    private int level = 1;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        paddle = new Paddle(350, 550);
       // ball = new Ball(390, 525);
        ball = new Ball(390, 525, this);
        bricks = new ArrayList<>();
        generateBricks();
        SoundPlayer.playBackgroundMusic("sounds/background.wav");
        //sounds/background.wav

        timer = new Timer(10, this);
        timer.start();
    }

    private void generateBricks() {
        bricks.clear();
        int brickWidth = 60;
        int brickHeight = 20;
        int rows = level + 2; // More rows as levels increase

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < rows; j++) {
                bricks.add(new Brick(100 + i * (brickWidth + 10), 50 + j * (brickHeight + 10)));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paddle.draw(g2d);
        ball.draw(g2d);
        for (Brick brick : bricks) {
            brick.draw(g2d);
        }

        // Draw Score & Level
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 10, 20);
        g2d.drawString("Level: " + level, 700, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move(getWidth(), getHeight(), paddle, bricks);

        boolean allBricksDestroyed = true;
        for (Brick brick : bricks) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {
                brick.setVisible(false);
                ball.reverseDy();
                score += 10;
                SoundPlayer.playSound("/sounds/brick_hit.wav");
            }
            if (brick.isVisible()) {
                allBricksDestroyed = false;
            }
        }

        // Load next level
        if (allBricksDestroyed) {
            level++;
           // ball = new Ball(390, 525);
            ball = new Ball(390, 525, this); // ✅ Corrected

            ball.increaseSpeed();
            generateBricks();

            // Win condition (after 3 levels)
            if (level > 3) {
                JOptionPane.showMessageDialog(this, "You Win! Final Score: " + score);
                System.exit(0);
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight(getWidth());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    public int getScore() {
        return score;
    }

}
