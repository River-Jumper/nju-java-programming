package jumper.game.gamelogic.component.buff;

// shootInterval / shootRate; health + additionHealth; maxX(y)Speed + additionalSpeed
public record BuffComponent(float shootRate, int additionalHealth, float additionalSpeed, int additionalDamage) {
}
