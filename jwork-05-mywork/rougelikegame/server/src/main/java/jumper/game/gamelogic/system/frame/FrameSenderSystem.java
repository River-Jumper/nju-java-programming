package jumper.game.gamelogic.system.frame;

import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

@Log4j2
@RequiredArgsConstructor
public class FrameSenderSystem implements Runnable{
    private final SystemContext context;

    @Override
    public void run() {
        FrameState frameState = context.frameState();
        log.info("send frame" + frameState.frame.keySet());
        context.gameServer().server.sendToAllTCP(frameState);
    }
}
