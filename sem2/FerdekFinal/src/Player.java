import java.awt.*;

public class Player {
    private static float x = 100f;
    private static float y = 500f;
    private float speed = 0;
    private Animation ferdekRun;
    private static Rectangle playerHitbox;

    public Player() {
        ferdekRun = new Animation(100);
        ferdekRun.addFrame(Asset.getAssetsImage("Assets/ferdekLeft.png"));
        ferdekRun.addFrame(Asset.getAssetsImage("Assets/ferdekRight.png"));
        ferdekRun.addFrame(Asset.getAssetsImage("Assets/ferdekJump.png"));

    }

    public void draw(Graphics2D g) {
        if(!GameScreen.isGameOver()) {
            g.setColor(new Color(255, 255, 255, 0));
            g.fillRect(playerHitbox.x, playerHitbox.y, playerHitbox.width, playerHitbox.height);
            g.drawImage(ferdekRun.getFrame(), (int) x, (int) y, null);
        }
    }

    public void jump() {
        speed -= 6;
        y += speed;

    }

    public void update() {
        playerHitbox = new Rectangle((int)x,(int)y,ferdekRun.getFrame().getWidth(),ferdekRun.getFrame().getHeight());
        ferdekRun.update(false);
        if (y >= 500) {
            speed = 0;
            y = 500;
        } else {
            ferdekRun.update(true);
            speed += 0.07f;
            y += speed;
        }
    }

    public static float getX() {
        return x;
    }

    public static float getY() {
        return y;
    }

    public static Rectangle getPlayerHitbox() {
        return playerHitbox;
    }
}