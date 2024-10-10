package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import jumper.game.DesktopLauncher;
import jumper.game.MyGame;
import jumper.game.network.GameClient;
import jumper.game.render.FrameBuffer;
import jumper.game.render.ImagePool;
import jumper.game.render.SymbolTable;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import network.FrameState;
import network.KeyboardState;
import network.MouseState;

import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class GameScreen extends MyScreen {
    private SymbolTable symbolTable;
    public FrameBuffer frameBuffer;
    public AtomicReference<FrameState> frameStateAtomicReference = new AtomicReference<>();
    private FrameState frameState;
    private final Group dynamicGroup = new Group();
    private Camera camera;
    @Setter
    private GameClient gameClient;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        //fps
        Gdx.graphics.setForegroundFPS(60);
        //all Image initial in symbolTable constructor
        //emoji
        Texture ghostTexture = new Texture("figures/emoji/ghost.png");
        Texture jokerTexture = new Texture("figures/emoji/joker.png");
        Texture angryTexture = new Texture("figures/emoji/angry.png");
        //bullet
        Texture flowerTexture = new Texture("figures/bullet/flower.png");
        Texture snowTexture = new Texture("figures/bullet/flower.png");

        symbolTable = new SymbolTable();
        symbolTable.put(FrameState.Symbol.PLAYER, jokerTexture);
        symbolTable.put(FrameState.Symbol.ENEMY, ghostTexture);
        symbolTable.put(FrameState.Symbol.BULLET, snowTexture);

        frameBuffer = new FrameBuffer();
        frameState = null;

        camera = new OrthographicCamera(DesktopLauncher.WIDTH, DesktopLauncher.HEIGHT);
        camera.position.set((float) DesktopLauncher.WIDTH / 2, (float) DesktopLauncher.HEIGHT / 2, 0);
        Batch batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }
    @Override
    public void render(float deltaTime) {
        //update
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateFrame();
        camera.update();

        dynamicGroup.clear();
        if (frameState != null) {
            for (var position : frameState.frame.keySet()) {
                var thing = frameState.frame.get(position);
                //get image
                Image image = new Image(symbolTable.getTexture(thing.symbol()));
                //set position
                image.setPosition(position.x(), position.y());
                //set size
                image.setSize(2 * thing.radius(), 2 * thing.radius());
                dynamicGroup.addActor(image);
            }
        }
        stage.addActor(dynamicGroup);
        stage.draw();
        log.info("draw one frame");

        //send MouseState
        Vector3 mousePosition = getMousePosition(camera);
        log.debug("x:{}, y:{}", mousePosition.x, mousePosition.y);
        gameClient.client.sendTCP(new MouseState(
                Gdx.input.isButtonPressed(Input.Buttons.LEFT),
                mousePosition.x,
                mousePosition.y));

        //send KeyboardState
        gameClient.client.sendTCP(new KeyboardState(
                Gdx.input.isButtonPressed(Input.Keys.W),
                Gdx.input.isButtonPressed(Input.Keys.S),
                Gdx.input.isButtonPressed(Input.Keys.A),
                Gdx.input.isButtonPressed(Input.Keys.D)
        ));
    }

    private void updateFrame() {
        FrameState nextFrameState = frameStateAtomicReference.get();
        if (nextFrameState != null) {
            frameState = nextFrameState;
        }
        log.debug("update one frame");
    }

    public Vector3 getMousePosition(Camera camera) {
        // 获取鼠标的屏幕坐标
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();

        // 将屏幕坐标转换为游戏世界坐标
        Vector3 worldCoords = new Vector3(x, y, 0);
        camera.unproject(worldCoords);

        return worldCoords;
    }

}
