
package jumper.game;

import com.esotericsoftware.kryonetty.ServerEndpoint;
import com.esotericsoftware.kryonetty.kryo.KryoNetty;

public class Main {
    KryoNetty kryoNetty = new KryoNetty()
            .useLogging()
            .useExecution()
            .threadSize(16)
            .inputSize(4096)
            .outputSize(4096)
            .maxOutputSize(-1)
            .register(

            );

    ServerEndpoint serverEndpoint = new ServerEndpoint(kryoNetty);
}


