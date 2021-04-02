import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class GameScreen extends JPanel implements KeyListener, Runnable{
    private Random random;
    private Thread thread;
    private String topScore = "";
    private boolean record = false;

    private Player player;
    private BufferedImage bg;
    private Obstacle obstacle;
    private BulletCollectable collectableBullet;
    private static boolean gameOver;
    private static int score;
    private boolean playing;
    private BufferedImage startBg;
    private BulletList bulletList;

    private int bulletCount;
    public GameScreen() {

        setPreferredSize(new Dimension(GameWindow.getWinWidth(),GameWindow.getWinHeight()));
        thread = new Thread(this);
        player = new Player();
        bg = Asset.getAssetsImage("Assets/tlo.jpg");
        obstacle = new Obstacle();
        random = new Random();
        gameOver = false;
        playing = false;
        score = 0;
        startBg = Asset.getAssetsImage("Assets/startBg.png");
        collectableBullet = new BulletCollectable();
        bulletCount = 0;
        bulletList = new BulletList(this);
    }

    public static int getScore() {
        return score;
    }

    @Override
    public void run() {
        while (!gameOver) {

                score++;
                player.update();
                obstacle.obstacleUpdate();
                bulletList.tick();
                for(StrongBullet sb : bulletList.getStrongBullets()) {
                    if(sb.getsBulletHitbox().intersects(obstacle.getObstacleHitbox())) {
                        obstacle.setY(-200);
                        bulletList.removeBullet(sb);
                    }
                }

                if(bulletCount < 5) {
                    collectableBullet.update();
                    if(collectableBullet.getBulletHitbox().intersects(player.getPlayerHitbox())){
                        bulletCount++;
                        collectableBullet.x = -100;
                    }
                }
                repaint();
                if (obstacle.getObstacleHitbox().intersects(player.getPlayerHitbox())) {
                    gameOver = true;
                    CheckScore();
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }

    }

    @Override
    public void paint(Graphics g) {
        if(playing) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(bg, 0, 0, null);
            player.draw(g2);
            obstacle.drawObstacle(g2);
            collectableBullet.drawOBullet(g);
            bulletList.render(g);
            g.setColor(Color.PINK.darker());
            g.setFont(new Font("Helvetica", Font.BOLD, 20));
            g.drawString("SCORE: " + score, 800, 100);
            g.drawString("TOPSCORE: " + topScore, 500, 100);
            g.drawString("x" + bulletCount, 100, 100);
            g.drawImage(collectableBullet.getBulletImg(),50,70,null);

        }else{
            g.drawImage(startBg,0,0,null);
            g.setColor(new Color(40,40,150));
            g.fillRect(400,600,200,90);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Helvetica", Font.BOLD, 20));
            g.drawString("PRESS E TO START", 405, 650);
            g.drawString("SPACE to JUMP",100,625);
            g.drawString("X to SHOOT",100,650);
            g.drawString("TOP SCORE :" + topScore,700,625);
        }
        if(gameOver){
            if(record){
                g.setFont(new Font("Helvetica", Font.BOLD, 50));
                g.drawString("NEW TOP SCORE: " + score, 255, 500);
            }
            g.setFont(new Font("Helvetica", Font.BOLD, 50));
            g.drawString("GAME OVER", 355, 300);
            g.drawImage(Asset.getAssetsImage("Assets/ferdekDead.png"),(int)player.getX(),(int)player.getY(),null);
        }
        if(topScore.equals("")) {
            topScore = this.getHighScore();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!gameOver) {
                if (player.getY() >= 500) {
                    player.jump();
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_X && playing) {
            if(!gameOver) {
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_E && !playing) {
            playing = true;
            thread.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_X) {
            if (bulletCount > 0) {
                bulletList.addBullet(new StrongBullet(player.getX()+75,player.getY()+85,this));
                bulletCount--;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static String getHighScore() {
       FileReader fileReader;
       BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("topScore.dat");
            bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.readLine();
        } catch (Exception e) {
            return "0";
        }
        finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void CheckScore(){
        if(score > Integer.parseInt(topScore)){
            record = true;
            topScore = String.valueOf(score);
            File scoreFile = new File("topScore.dat");
            if(!scoreFile.exists()){
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;
            try {
                fileWriter = new FileWriter(scoreFile);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(topScore);
            } catch (Exception e) {
              e.printStackTrace();
            }
            finally {
                try {
                    if(bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static boolean isGameOver() {
        return gameOver;
    }

}
