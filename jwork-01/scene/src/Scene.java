public class Scene {
    Monster snake;
    Monster scorpion;
    Human thirdChild;
    Sword sword;
    MagicMirror magicMirror;

    public Scene() {
        snake = new Monster("snake");
        scorpion = new Monster("scorpion");
        thirdChild = new Human("thirdChild");
        sword = new Sword();
        magicMirror = new MagicMirror();
    }

    public void play() {
        thirdChild.strike(magicMirror);
        scorpion.weep();
        scorpion.speakTo(thirdChild, "呜呜呜，我的宝贝……");
        snake.throwTo(thirdChild);
        thirdChild.speakTo(snake, "你们这些害人精，还不快投降!");
        snake.rollEyes();
        snake.laugh();
        snake.speakTo(thirdChild, "你凭着一副钢筋铁骨竟就如此夸口,你可知道老娘的厉害。");
        thirdChild.speakTo(snake, "哈哈哈哈，你有什么本事,就拿出来吧!");
        snake.speakTo(thirdChild, "好!");
        sword.setOwner(snake);
        thirdChild.laugh();
        snake.speakTo(thirdChild, "哦？你难道不怕我这宝剑吗？");
        thirdChild.speakTo(snake, "你干脆把所有的兵器都拿出来吧，我还可以陪你玩玩。");
    }

    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.play();
    }

}
