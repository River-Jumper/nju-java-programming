package org.example.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.example.GameConfig;
import org.example.gamelogic.factory.BulletFactory;
import org.example.gamelogic.factory.EnemyFactory;
import org.example.gamelogic.factory.PlayerFactory;
import org.example.gamelogic.system.*;

public class GameScreen implements Screen {
    private final Game game;
    private Stage stage;
    private Engine engine;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.stage = new Stage();
        this.engine = new Engine();
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();

        camera.setToOrtho(false, GameConfig.WIDTH, GameConfig.HEIGHT);

        Gdx.input.setInputProcessor(stage);

        batch.setProjectionMatrix(camera.combined);

        //background

        Texture backgroundTexture = new Texture(Gdx.files.internal("background/background.jpg"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(backgroundImage);

        //make entities
        PlayerFactory playerFactory = new PlayerFactory();
        EnemyFactory enemyFactory = new EnemyFactory();
        BulletFactory bulletFactory = new BulletFactory();

        playerFactory.setEngine(this.engine);
        enemyFactory.setEngine(this.engine);
        bulletFactory.setEngine(this.engine);

        playerFactory.make(0, GameConfig.HEIGHT - 2 * GameConfig.PlayerRADIUS, 0, 0);
        enemyFactory.make(0, 0, GameConfig.EnemyMAXSPEED, GameConfig.EnemyMAXSPEED);
        enemyFactory.make(GameConfig.WIDTH - 2 * GameConfig.EnemyRADIUS, 0, 0, 0);

        //system in engine
        engine.addSystem(new CollisionDetectionSystem());
        engine.addSystem(new InputMoveSystem());
        engine.addSystem(new RandomMoveSystem());
        engine.addSystem(new TargetMoveSystem());
        engine.addSystem(new InputShootSystem(bulletFactory, this.camera));
        engine.addSystem(new CollisionRespondSystem());
        engine.addSystem(new BulletCollisionSystem());

        engine.addSystem(new MovementSystem());
        engine.addSystem(new DestructionSystem());

        engine.addSystem(new RenderSystem(this.batch));


    }

    @Override
    public void render(float v) {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        engine.update(v);

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
