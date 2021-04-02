import java.awt.*;
import java.util.LinkedList;

public class BulletList {
    private LinkedList<StrongBullet> strongBullets;
    private StrongBullet temp;
    private GameScreen gameScreen;
    public BulletList(GameScreen gameScreen){
        strongBullets = new LinkedList<StrongBullet>();
        this.gameScreen = gameScreen;
    }

    public void tick(){
        for(StrongBullet strongBullet : strongBullets){
            temp = strongBullet;
            temp.tick();
            if(temp.getX() >= 1200){
                removeBullet(temp);
            }
        }
    }
    public void render(Graphics g){
        for(StrongBullet strongBullet : strongBullets){
            temp = strongBullet;
            temp.draw(g);
        }
    }
    public void addBullet(StrongBullet sb){
        strongBullets.add(sb);
    }
    public void removeBullet(StrongBullet sb){
        strongBullets.remove(sb);
    }

    public LinkedList<StrongBullet> getStrongBullets() {
        return strongBullets;
    }

}
