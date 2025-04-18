import javax.swing.*;

public class BrickBreakerGame extends JFrame {

    public BrickBreakerGame() {
        setTitle("Brick Breaker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BrickBreakerGame::new);
    }
}
