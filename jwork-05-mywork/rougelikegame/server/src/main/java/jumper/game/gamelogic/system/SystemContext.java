package jumper.game.gamelogic.system;


import dev.dominion.ecs.api.Dominion;

import jumper.game.SingletonManager;
import org.example.gamelogic.factory.BulletFactory;

public record SystemContext(Dominion world, SingletonManager singletonManager,
                            BulletFactory bulletFactory) {
}
