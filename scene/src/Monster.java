public class Monster extends Creature {
    public Monster(String name) {
        health = 100;
        attack = 10;
        defense = 10;
        this.name = name;
    }

    public void rollEyes() {
        System.out.println(name + " roll his/her eyes");
    }

    public void throwTo(Creature creature) {
        System.out.println(name + " throw magicMirror to " + creature.name);
        ((Human) creature).parry();
    }
}
