package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import jumper.game.playback.ReadPlaybackRecord;
import network.FrameState;

public class PlaybackScreen extends GameScreen{
    private static String filename = "record.txt";
    private ReadPlaybackRecord readPlaybackRecord;
    public PlaybackScreen(Game game) {
        super(game);
        this.readPlaybackRecord = new ReadPlaybackRecord();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        dynamicGroup.clear();
        FrameState frameState = readPlaybackRecord.readNext();
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
    }
}
