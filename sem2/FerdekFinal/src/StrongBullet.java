import java.awt.*;
import java.awt.image.BufferedImage;

public class StrongBullet {
    private double x;
    private double y;
    private BufferedImage sBulletImg;
    private Rectangle sBulletHitbox;

    public StrongBullet(double x, double y, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        sBulletImg = Asset.getAssetsImage("Assets/strongFull.png");
    }
    public void tick(){
        sBulletHitbox = new Rectangle((int) x, (int) y, sBulletImg.getWidth(), sBulletImg.getHeight());
        x += 7;
    }
    public void draw(Graphics g){
    g.drawImage(sBulletImg,(int)x,(int)y,null);
    }

    public double getX() {
        return x;
    }

    public Rectangle getsBulletHitbox() {
        return sBulletHitbox;
    }

}
