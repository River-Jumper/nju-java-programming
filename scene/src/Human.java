public class Human extends Creature {
    public Human(String name) {
        health = 100;
        attack = 10;
        defense = 10;
        this.name = name;
    }

    public void strike(MagicMirror magicMirror) {
        System.out.println(name + " strike the magicMirror.");
        magicMirror.broken();
    }

    public void parry(){
        System.out.println(name + " parries it.");
    }
}
