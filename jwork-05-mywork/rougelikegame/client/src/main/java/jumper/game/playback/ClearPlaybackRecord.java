package jumper.game.playback;

import java.io.IOException;
import java.io.RandomAccessFile;

// Attention!: should not be used in GameScreen, because PlayBack Screen extends GameScreen
public class ClearPlaybackRecord {
    // clear record (initial record because only one record exists)
    public static void clear() {
        try (RandomAccessFile raf = new RandomAccessFile("data/record.txt", "rw")) {
            raf.setLength(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClearPlaybackRecord.clear();
    }
}
