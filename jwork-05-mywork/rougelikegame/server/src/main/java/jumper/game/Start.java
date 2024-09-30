
package jumper.game;
import dev.dominion.ecs.api.Dominion;
import dev.dominion.ecs.api.Scheduler;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.manager.SingletonManager;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.network.GameServer;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

import java.io.IOException;

@Log4j2
public class Start {
    public static void main(String[] args) throws IOException {
        //network
        FrameState frameState = new FrameState();

        //manager
        SingletonManager singletonManager = new SingletonManager();
        InputManager inputManager = new InputManager();

        //world
        Dominion world = Dominion.create();
        makeEntity(world);

        Scheduler scheduler = world.createScheduler();
        addSystemIntoScheduler(scheduler);

        //FPS
        scheduler.tickAtFixedRate(60);

        //system context
        SystemContext systemContext = new SystemContext(world,
                singletonManager, frameState, inputManager);

        //server
        GameServer gameServer = new GameServer(inputManager);
    }
    //add system
    private static void addSystemIntoScheduler(Scheduler scheduler) {

    }
    //make entity
    private static void makeEntity(Dominion world) {
        //for example
        world.createEntity(
                "test",
                new PositionComponent(10, 10),
                new SymbolComponent()
        );
    }
}


