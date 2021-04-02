import javax.swing.*;

import java.io.IOException;

public class GameWindow extends JFrame {
    private static int winWidth = 1024, winHeight = 768;
    public GameScreen gameScreen;


    public GameWindow() {
        setSize(1024,768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public static int getWinWidth() { return winWidth; }

    public static int getWinHeight() {
        return winHeight;
    }

}
