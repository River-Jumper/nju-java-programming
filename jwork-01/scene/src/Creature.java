public class Creature {
    int health;
    int attack;
    int defense;
    String name;

    public void speakTo(Creature creature, String something) {
        System.out.println(name + " tell " + creature.name + " '" + something + "'");
    }


    public void attack(Creature creature, int ap) {
        System.out.println(name + " attack " + creature.name + " of " + ap + "points");
        creature.receiveAttack(ap);
    }

    public void receiveAttack(int ap) {
        if (!isDead()) {
            if (defense < ap) {
                health -= ap;
                System.out.println(name + " got " + ap + "points attack");
            }
        }
    }

    public boolean isDead() {
        if (health <= 0) {
            System.out.println(name + " is dead");
            return true;
        }

        return false;
    }

    public void move() {

        // not implemented yet

    }

    public void laugh() {
        System.out.println(name + " is laughing.");
    }

    public void weep() {
        System.out.println(name + " is weeping.");
    }

    public void addAttack(int add) {
        attack += add;
    }

}
