
package jumper.game;

import dev.dominion.ecs.api.Dominion;
import dev.dominion.ecs.api.Scheduler;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.health.HealthComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.move.RandomMoveComponent;
import jumper.game.gamelogic.component.shoot.ShootingComponent;
import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.component.symbol.PlayerComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.manager.SingletonManager;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.gamelogic.system.collision.*;
import jumper.game.gamelogic.system.debug.Probe;
import jumper.game.gamelogic.system.destruct.DestructionSystem;
import jumper.game.gamelogic.system.frame.FrameClearSystem;
import jumper.game.gamelogic.system.frame.FrameRecordSystem;
import jumper.game.gamelogic.system.frame.FrameSenderSystem;
import jumper.game.gamelogic.system.health.DamageSystem;
import jumper.game.gamelogic.system.health.HealthSystem;
import jumper.game.gamelogic.system.move.*;
import jumper.game.gamelogic.system.shoot.InputShootSystem;
import jumper.game.network.GameServer;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

import java.io.IOException;

@Log4j2
public class ServerStart {
    public void start() throws IOException {
        //network
        FrameState frameState = new FrameState();

        //manager
        SingletonManager singletonManager = new SingletonManager();
        CollisionEventManager collisionEventManager = new CollisionEventManager();
        singletonManager.addSingletonComponent(SingletonManager.SingletonName.COLLISION_MESSAGE,
                collisionEventManager);

        InputManager inputManager = new InputManager();

        //world
        Dominion world = Dominion.create();
        makeEntity(world);

        //server
        GameServer gameServer = new GameServer();

        //system context
        SystemContext context = new SystemContext(world,
                singletonManager, frameState, inputManager, gameServer);

        gameServer.setListener(context);

        Scheduler scheduler = world.createScheduler();
        addSystemIntoScheduler(scheduler, context);

        //FPS
        scheduler.tickAtFixedRate(60);


    }

    //add system
    private static void addSystemIntoScheduler(Scheduler scheduler, SystemContext context) {
        //level 1
        scheduler.parallelSchedule(new ClearCollisionEventsSystem(context),
                new FrameClearSystem(context));
        //level 2
        scheduler.parallelSchedule(new CollisionDetectionSystem(context),
                new InputShootSystem(context));
        //level 3
        scheduler.parallelSchedule(new BulletCollisionSystem(context),
                new CollisionDamageSystem(context));

        //scheduler.schedule(new Probe(context));
        //level 4
        scheduler.schedule(new RandomMoveSystem(context));
        //level 5
        scheduler.schedule(new TargetMoveSystem(context));
        //level 6
        scheduler.parallelSchedule(new InputMoveSystem(context),
                new DamageSystem(context));
        //scheduler.schedule(new Probe(context));
        //level 7
        scheduler.schedule(new CollisionMoveSystem(context));
        //level 8

        scheduler.schedule(new MovementSystem(context));

        scheduler.schedule(new HealthSystem(context));
        //level 9
        scheduler.schedule(new EdgeDetectSystem(context));
        //level 10
        scheduler.schedule(new BulletEdgeDetectSystem(context));
        //level 11
        scheduler.schedule(new DestructionSystem(context));
        //level 12
        scheduler.schedule(new FrameRecordSystem(context));
        //level 13
        scheduler.schedule(new FrameSenderSystem(context));
    }

    //make entity
    private static void makeEntity(Dominion world) {
        //for example
        world.createEntity(
                new PositionComponent(900, 500),
                new SymbolComponent(FrameState.Symbol.ENEMY),
                new CollisionComponent(20),
                new MovableComponent(10, 10, 100, 100),
                new RandomMoveComponent(2)
        );
    }
}


