package org.example.gamelogic.component;

public class HealthComponent extends Component{
    private int maxHealth;
    private int currentHealth;

    public HealthComponent(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }
    public HealthComponent(int maxHealth, int currentHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {return this.maxHealth; }

    public int getCurrentHealth() {return this.currentHealth; }

    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth; }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }

    public void takeHeal(int heal) {
        this.currentHealth += heal;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }
}
