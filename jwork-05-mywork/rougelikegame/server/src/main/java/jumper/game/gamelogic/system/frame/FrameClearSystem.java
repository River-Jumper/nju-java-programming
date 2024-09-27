package jumper.game.gamelogic.system.frame;

import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FrameClearSystem implements Runnable{
    private final SystemContext context;
    @Override
    public void run() {
        context.frameState().frame.clear();
    }
}
