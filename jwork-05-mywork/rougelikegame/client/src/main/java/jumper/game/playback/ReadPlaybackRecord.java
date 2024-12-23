package jumper.game.playback;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

//can only deal with FrameState
@Log4j2
public class ReadPlaybackRecord {
    private final Kryo kryo;
    private final String filename = "data/record.txt";
    private InputStream inputStream;
    private Input input;

    public ReadPlaybackRecord() {
        this.kryo = new Kryo();
        kryo.register(FrameState.Symbol.class);
        kryo.register(java.util.HashMap.class);
        kryo.register(network.FrameState.Position.class);
        kryo.register(FrameState.Thing.class);
        kryo.register(FrameState.class);

        try {
            this.inputStream = new FileInputStream(this.filename);
            this.input = new Input(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // read next FrameState from record
    public FrameState readNext() {
        try {
            if (this.input.available() > 0) {
                log.info("read one frame from record");
                return kryo.readObject(input, FrameState.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //initial
        ClearPlaybackRecord.clear();
        //write
        WritePlaybackRecord writePlaybackRecord = new WritePlaybackRecord();
        Random random = new Random();
        int frameNum = 2 + random.nextInt(9);
        while (frameNum > 0) {
            FrameState frameState = new FrameState();
            frameState.frame.put(new FrameState.Position(frameNum, frameNum),
                    new FrameState.Thing(FrameState.Symbol.PLAYER, frameNum));
            log.info("Write: position: x = {}, y = {}, radius = {}", frameNum, frameNum, frameNum);
            writePlaybackRecord.write(frameState);
            frameNum--;
        }

        //read
        ReadPlaybackRecord readPlaybackRecord = new ReadPlaybackRecord();
        FrameState frameState = readPlaybackRecord.readNext();
        while (frameState != null) {
            for (var position : frameState.frame.keySet()) {
                var thing = frameState.frame.get(position);
                log.info("Read: position: x = {}, y = {}, radius = {}",
                        position.x(), position.y(), thing.radius());
            }
            frameState = readPlaybackRecord.readNext();
        }
    }
}
