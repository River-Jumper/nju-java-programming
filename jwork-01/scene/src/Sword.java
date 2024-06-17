public class Sword extends Substance {
    Creature owner;
    int attack;

    public Sword() {
        attack = 10;
    }

    public void setOwner(Creature owner) {
        this.owner = owner;
        System.out.println(owner.name + " acquires a sword!");
        owner.addAttack(attack);
    }
}
