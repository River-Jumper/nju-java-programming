package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.DesktopLauncher;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.network.GameClient;
import jumper.game.playback.WritePlaybackRecord;
import jumper.game.render.FrameBuffer;
import jumper.game.render.SymbolTable;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import network.FrameState;
import network.KeyboardState;
import network.MouseState;
import network.PauseControl;

import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class GameScreen extends MyScreen {
    protected SymbolTable symbolTable;
    public FrameBuffer frameBuffer;
    public AtomicReference<FrameState> frameStateAtomicReference = new AtomicReference<>();
    private FrameState frameState;
    protected final Group dynamicGroup = new Group();
    private Camera camera;
    private WritePlaybackRecord writePlaybackRecord;
    private boolean lastPressEsc = false;
    private boolean isPause = false;
    private boolean sendStart = false;
    private Image loadingImage;
    @Setter
    private GameClient gameClient;


    public GameScreen(Game game) {
        super(game);
        this.writePlaybackRecord = new WritePlaybackRecord();
    }

    @Override
    public void show() {
        //fps
        Gdx.graphics.setForegroundFPS(60);
        //all Image initial in symbolTable constructor
        //background
        Texture pamuTexture = new Texture("figures/background/horizontal/pamu.jpg");
        Texture loadTexture = new Texture("figures/background/horizontal/loading.jpg");
        Texture purpleTexture = new Texture("figures/background/horizontal/purple.jpg");
        //emoji
        Texture ghostTexture = new Texture("figures/emoji/ghost.png");
        Texture jokerTexture = new Texture("figures/emoji/joker.png");
        Texture angryTexture = new Texture("figures/emoji/angry.png");
        Texture candyTexture = new Texture("figures/emoji/candy.png");
        //bullet
        Texture flowerTexture = new Texture("figures/bullet/flower.png");
        Texture snowTexture = new Texture("figures/bullet/flower.png");

        /*
        //button
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));
        TextButton settingsButton = new TextButton("Settings", skin);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsButton.remove();
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(settingsButton).width(80).pad(10);
        table.row().center();

        stage.addActor(table);
        */
        symbolTable = new SymbolTable();
        symbolTable.put(FrameState.Symbol.PLAYER, jokerTexture);
        symbolTable.put(FrameState.Symbol.ENEMY, ghostTexture);
        symbolTable.put(FrameState.Symbol.BULLET, snowTexture);
        symbolTable.put(FrameState.Symbol.GIFT, candyTexture);

        frameBuffer = new FrameBuffer();
        frameState = null;

        camera = new OrthographicCamera(DesktopLauncher.WIDTH, DesktopLauncher.HEIGHT);
        camera.position.set((float) DesktopLauncher.WIDTH / 2, (float) DesktopLauncher.HEIGHT / 2, 0);
        Batch batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        Image gameScreenBackground = new Image(purpleTexture);
        gameScreenBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.setBackgroundImage(gameScreenBackground);
        this.loadingImage = new Image(loadTexture);
        this.loadingImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float deltaTime) {
        //update
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        updateFrame();

        // write frame to playback record
        this.writePlaybackRecord.write(this.frameState);

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
                //image.setSize(thing.radius(), thing.radius());
                dynamicGroup.addActor(image);
            }
        }
        else {
            dynamicGroup.addActor(this.loadingImage);
        }
        stage.addActor(dynamicGroup);
        stage.draw();
        log.info("draw one frame");

        //send MouseState
        Vector3 mousePosition = getMousePosition(camera);
        log.debug("x:{}, y:{}", mousePosition.x, mousePosition.y);
        if (this.gameClient != null) {
            gameClient.client.sendTCP(new MouseState(
                    Gdx.input.isButtonPressed(Input.Buttons.LEFT),
                    mousePosition.x,
                    mousePosition.y));
        }


        //send KeyboardState
        boolean pressW = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean pressS = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean pressA = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean pressD = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean pressP = Gdx.input.isKeyPressed(Input.Keys.P);
        this.changeServerPauseState(pressP);
        log.debug("W S A D Esc: {} {} {} {} {}", pressW, pressS, pressA, pressD, pressP);
        if (this.gameClient != null) {
            gameClient.client.sendTCP(new KeyboardState(
                    pressW, pressS, pressA, pressD, pressP
            ));
        }
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

    private void changeServerPauseState(boolean curPressEsc) {
        if (!curPressEsc && this.lastPressEsc) {
            this.isPause = !this.isPause;
            gameClient.client.sendTCP(new PauseControl(this.isPause));
        }
        // lastPressP need to be stored
        this.lastPressEsc = curPressEsc;
    }

}
