package jumper.game.gamelogic.system;


import dev.dominion.ecs.api.Dominion;

import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.manager.SingletonManager;
import jumper.game.network.GameServer;
import network.FrameState;


public record SystemContext(Dominion world, SingletonManager singletonManager,
                            FrameState frameState, InputManager inputManager,
                            GameServer gameServer) {
}
