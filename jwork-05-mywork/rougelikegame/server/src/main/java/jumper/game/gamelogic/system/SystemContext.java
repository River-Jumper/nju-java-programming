package jumper.game.gamelogic.system;


import dev.dominion.ecs.api.Dominion;

import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.manager.SingletonManager;
import network.Frame;


public record SystemContext(Dominion world, SingletonManager singletonManager,
                            Frame frame, InputManager inputManager) {
}
