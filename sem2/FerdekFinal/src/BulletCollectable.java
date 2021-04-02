import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletCollectable extends Rectangle {
    private float startX,y,w,h;
    private Rectangle bulletHitbox;
    private BufferedImage bulletImg;

    public BulletCollectable(){
        bulletImg = Asset.getAssetsImage("Assets/strongFull.png");
        startX = 1600f;
        y = (float)(300 + Math.random()*500);
        w = 20f;
        h = 10f;

    }
    public void update() {
        bulletHitbox = new Rectangle((int) x, (int) y, bulletImg.getWidth(), bulletImg.getHeight());
        x -= 2.5;
        if (x + 10 <= 0) {
            x = 1600;
            y = (float)(300 + Math.random()*400);
        }
    }

    public void drawOBullet(Graphics g){
        g.setColor(null);
        g.drawImage(bulletImg,x,(int)y,null);

    }

    public double getY() {
        return y;
    }

    public Rectangle getBulletHitbox() {
        return bulletHitbox;
    }

    public BufferedImage getBulletImg() {
        return bulletImg;
    }
}
