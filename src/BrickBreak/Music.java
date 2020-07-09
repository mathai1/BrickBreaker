package BrickBreak;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    Clip clip;

    public Music(String file){
        try{
            File files =new File(file);
            if(files.exists()){
                AudioInputStream sound= AudioSystem.getAudioInputStream(files);
                clip =AudioSystem.getClip();
                clip.open(sound);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage() + "No sound documents are found");
        }
    }

    public void play() {
        clip.setFramePosition(0); // Must always rewind!
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
