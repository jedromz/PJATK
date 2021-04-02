import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {
        private float x = 1300f;
        private float y = 550f;
        private float speed = 2.8f;
        private BufferedImage obstacleImg;
        private Rectangle obstacleHitbox;

    public Obstacle() {
        obstacleImg = resizeObstacle(Asset.getAssetsImage("Assets/ferdekHalinka.png"), 75, 150);
    }

    public void obstacleUpdate(){
        if(GameScreen.getScore() % 2000 == 0){
            speed += 1;
        }
        obstacleHitbox = new Rectangle((int) x, (int) y, obstacleImg.getWidth(), obstacleImg.getHeight());
        x-=speed;


        if(x + obstacleImg.getHeight() <= 0){
            x = 1300;
            double scale =  0.5 +( Math.random() * 0.7);
            obstacleImg = resizeObstacle(Asset.getAssetsImage("Assets/ferdekHalinka.png"),(int)(75 * scale),(int)( 150* scale));
            y = 700 - obstacleImg.getHeight();
        }
    }


    public BufferedImage resizeObstacle (BufferedImage img, int newW, int newH){
        Image tmp = img.getScaledInstance(newW,newH,Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW,newH,BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
    public void drawObstacle(Graphics g){
        g.setColor(new Color(255, 255, 255, 0));
        g.drawRect(obstacleHitbox.x,obstacleHitbox.y,obstacleHitbox.width,obstacleHitbox.height);
        g.drawImage(obstacleImg,(int)x,(int)y,null);
    }


    public Rectangle getObstacleHitbox() {
        return obstacleHitbox;
    }

    public void setY(float y) {
        this.y = y;
    }
}
