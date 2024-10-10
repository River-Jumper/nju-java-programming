package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class JoinServerScreen extends MyScreen {
    public JoinServerScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        //button
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextField ipText = new TextField("Ip: ", skin);
        TextField portText = new TextField("Port: ", skin);

        TextButton confirmButton = new TextButton("Confirm", skin);
        confirmButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String ip = ipText.getText();
                String port = portText.getText();

                DealWithIpAndPort dealWithIpAndPort = new DealWithIpAndPort(ip, port);

                System.out.println(dealWithIpAndPort.getIp());
                System.out.println(dealWithIpAndPort.getPort());
            }
        });

        TextButton returnButton = new TextButton("Return", skin);
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MultiPlayersGameScreen(game));
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(ipText).width(150).pad(10);
        table.row().center();
        table.add(portText).width(150).pad(10);
        table.row().center();
        table.add(confirmButton).width(100).pad(10);
        table.row().center();
        table.add(returnButton).width(100).pad(10);
        table.row().center();

        stage.addActor(table);
    }

    private static class DealWithIpAndPort {
        private static String ip;
        private static String port;
        public DealWithIpAndPort(String ipText, String portText) {
            ip = ipText.replaceAll("Ip: ", "");
            port = portText.replaceAll("Port: ", "");
        }
        public String getIp() {return ip;}
        public String getPort() {return port;}

    }
}
