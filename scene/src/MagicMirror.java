public class MagicMirror extends Substance {
    int status;

    public MagicMirror() {
        status = 1;
    }

    public void broken() {
        System.out.println("MagicMirror is broken.");
        status = 0;
    }
}
