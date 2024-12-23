package jumper.game.playback;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

//can only deal with FrameState
@Log4j2
public class WritePlaybackRecord {
    private final Kryo kryo;
    private final String filename = "data/record.txt";
    private FileOutputStream fileOutputStream;
    private Output output;

    public WritePlaybackRecord() {
        this.kryo = new Kryo();
        kryo.register(FrameState.Symbol.class);
        kryo.register(java.util.HashMap.class);
        kryo.register(network.FrameState.Position.class);
        kryo.register(FrameState.Thing.class);
        kryo.register(FrameState.class);
        try {
            this.fileOutputStream = new FileOutputStream(this.filename, true);
            this.output = new Output(this.fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // write FrameState to record
    public void write(FrameState frameState) {
        if (frameState != null) {
            kryo.writeObject(this.output, frameState);
            output.flush();
            log.info("write one frame to record");
        }
    }

    public static void main(String[] args) {
        FrameState frameState0 = new FrameState();
        frameState0.frame.put(new FrameState.Position(1, 2),
                new FrameState.Thing(FrameState.Symbol.PLAYER, 10));

        AtomicReference<FrameState> frameStateAtomicReference = new AtomicReference<>();
        frameStateAtomicReference.set(frameState0);

        FrameState frameState = frameStateAtomicReference.get();

        WritePlaybackRecord writePlaybackRecord = new WritePlaybackRecord();
        writePlaybackRecord.write(frameState);
        for (var position : frameState.frame.keySet()) {
            var thing = frameState.frame.get(position);
            log.info("position: x = {}, y = {}", position.x(), position.y());
        }
    }
}
