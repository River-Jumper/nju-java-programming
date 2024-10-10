package jumper.game.render;

import lombok.extern.log4j.Log4j2;
import network.FrameState;

import java.util.ArrayDeque;
import java.util.Queue;

@Log4j2
public class FrameBuffer {
    private final Queue<FrameState> frameQueue;
    public FrameBuffer() {
        frameQueue = new ArrayDeque<>();
    }
    public void addFrame(FrameState frameState) {
        log.debug("framebuffer size: {}, " + "add into framebuffer:" + frameState.frame.keySet(), frameQueue.size());
        frameQueue.add(frameState);
    }
    public FrameState getFrame() {
        if (frameQueue.peek() != null) {
            log.debug("framebuffer size: {}, " + "get from framebuffer:" + frameQueue.peek().frame.keySet(), frameQueue.size());
        }
        return frameQueue.poll();
    }
}
