import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<BufferedImage> frames;
    public int currentFrameindex = 0;
    int deltaTime;
    long previousTime;

    public Animation(int deltaTime){
        this.deltaTime = deltaTime;
        frames = new ArrayList<BufferedImage>();

    }
    public void update(boolean jumped){

        if(!jumped) {
            if (System.currentTimeMillis() - previousTime > deltaTime) {
                currentFrameindex++;
                if (currentFrameindex == 2 || currentFrameindex == 3) {
                    currentFrameindex = 0;
                }
                previousTime = System.currentTimeMillis();
            }
        }else{
            currentFrameindex = 2;
            }
        }



    public void addFrame(BufferedImage frame){
        frames.add(frame);
    }

    public BufferedImage getFrame(){
        if(frames.size() > 0){
            return frames.get(currentFrameindex);
        }
        return null;
    }


}
