package jumper.game.gamelogic.system.frame;

import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;
import network.Frame;


@RequiredArgsConstructor
public class FrameRecordSystem implements Runnable{
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(SymbolComponent.class, PositionComponent.class, CollisionComponent.class)
                .forEach(result -> {
                    Frame.Symbol symbol = result.comp1().symbol;
                    PositionComponent position = result.comp2();
                    context.frame().frame.put(new Frame.Position(position.x, position.y),
                            new Frame.Thing(symbol, result.comp3().radius));
                });
    }
}
