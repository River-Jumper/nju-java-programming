package jumper.game.gamelogic.component;



public class HealthComponent{
    public int maxHealth;
    public int currentHealth;

    public HealthComponent(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }
    public HealthComponent(int maxHealth, int currentHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }
}
