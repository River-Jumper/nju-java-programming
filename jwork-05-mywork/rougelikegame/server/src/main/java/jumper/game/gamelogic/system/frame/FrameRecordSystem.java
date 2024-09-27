package jumper.game.gamelogic.system.frame;

import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;
import network.FrameState;


@RequiredArgsConstructor
public class FrameRecordSystem implements Runnable{
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(SymbolComponent.class, PositionComponent.class, CollisionComponent.class)
                .forEach(result -> {
                    FrameState.Symbol symbol = result.comp1().symbol;
                    PositionComponent position = result.comp2();
                    context.frameState().frame.put(new FrameState.Position(position.x, position.y),
                            new FrameState.Thing(symbol, result.comp3().radius));
                });
    }
}
