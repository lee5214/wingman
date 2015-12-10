package pub;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class LoopMusic implements Runnable {

    private Sequencer midi;
    private String[] names = {"Resources/background.mid"};
    private int i;//num of background music play time
    private Map<String, Sequence> map;

    public LoopMusic() {//constructor
        initMap();
        new Thread(this).start();
    }

    private void initMap() {//initiate the map
        try {
            map = new Hashtable<String, Sequence>();
            midi = MidiSystem.getSequencer();//only one instance of squencer.
            midi.open();//open a device for play.
            for (String s : names) {
                try {
                    Sequence s1 = MidiSystem.getSequence(new File(s));//read sound file.
                    map.put(s, s1);//put  file into the map.
                } catch (InvalidMidiDataException ex) {
                    Logger.getLogger(LoopMusic.class.getName()).log(Level.SEVERE, null, ex);
                    // handle exception
                } catch (IOException ex) {
                    Logger.getLogger(LoopMusic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(LoopMusic.class.getName()).log(Level.SEVERE, null, ex);
            // handle exception
        }
    }

    private void createPlayer(String name) {
        try {
            System.out.println(name);
            System.out.println(map.get(name));
            Sequence se = map.get(name);//read a file from the map.
            midi.setSequence(se);//set current file.
            midi.start();//play the file.
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(LoopMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void run() {
        while (true) {
            try {
                String name = names[(int) (Math.random() * names.length)];//read a random music from array
                createPlayer(name);//play a midi file.
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoopMusic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}