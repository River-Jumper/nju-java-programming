package jumper.game.render;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Log4j2
public class ImagePool {
    private final Map<FrameState.Symbol, Queue<Image>> pool;
    private final Map<FrameState.Symbol, Queue<Image>> used;
    private final SymbolTable symbolTable;
    public ImagePool(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.pool = new HashMap<>();
        this.used = new HashMap<>();
        Queue<Image> palyerImageQueue = new ArrayDeque<>();
        Queue<Image> enemyImageQueue = new ArrayDeque<>();
        Queue<Image> bulletImageQueue = new ArrayDeque<>();

        pool.put(FrameState.Symbol.PLAYER, palyerImageQueue);
        pool.put(FrameState.Symbol.ENEMY, enemyImageQueue);
        pool.put(FrameState.Symbol.BULLET, bulletImageQueue);



        used.put(FrameState.Symbol.PLAYER, palyerImageQueue);
        used.put(FrameState.Symbol.ENEMY, enemyImageQueue);
        used.put(FrameState.Symbol.BULLET, bulletImageQueue);

    }
    //如果池子里找不到image，新建一个，否则拿池子里的
    //将拿到的image添加到used里面，并将其设置为可见，返回image
    public Image getImage(FrameState.Symbol symbol) {
        Queue<Image> imageQueue = pool.get(symbol);
        log.debug("{}queue size: {}, get {}", symbol, imageQueue.size(), symbol);
        if (imageQueue.isEmpty()) {
            Image newImage = new Image(symbolTable.getTexture(symbol));
            newImage.setVisible(true);
            used.get(symbol).add(newImage);
            return newImage;
        }
        else {
            Image newImage = imageQueue.poll();
            newImage.setVisible(true);
            used.get(symbol).add(newImage);
            return newImage;
        }


    }
    //put images of used into pool, and set visible false
    //used after render one frame
    public void recyclePool() {
        for (FrameState.Symbol symbol : used.keySet()) {
            Queue<Image> poolQueue = pool.get(symbol);
            Queue<Image> usedQueue = used.get(symbol);
            while (!usedQueue.isEmpty()) {
                Image image = usedQueue.poll();
                image.setVisible(false);
                poolQueue.add(image);
                log.debug("{} used queue size: {}, recycle {}", symbol, usedQueue.size(), symbol);
            }
        }
    }
}
