package jumper.game.gamelogic.system.wave;

import jumper.game.GameConfig;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.factory.EnemyFactory;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.gamelogic.system.WaveState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class EnemyWaveSystem implements Runnable {
    private final SystemContext context;
    private WaveState.State curState = WaveState.State.DEFAULT;
    //6*20
    private final RandomPairs randomPairs = new RandomPairs(6, 20);
    @Override
    public void run() {
        if (context.waveState().state != this.curState
                && context.waveState().state == WaveState.State.WAR) {
            int waveNum = context.waveState().waveNum;
            int enemyNum = 4 + waveNum * (waveNum + 1) / 2;
            if (enemyNum > 120) {
                enemyNum = 120;
            }
            int additionalSpeed = 10 * (waveNum - 1);
            int health = waveNum;
            randomPairs.shuffle();
            List<int[]> selectedPairs = randomPairs.pairs.subList(0, enemyNum);
            for (int[] pair : selectedPairs) {
                int x = 60 * pair[1] + 30;
                int y = 720 - (60 * pair[0] + 30);
                EnemyFactory.make(context.world(), x, y,
                        GameConfig.ENEMY_MAX_SPEED + additionalSpeed, GameConfig.ENEMY_MAX_SPEED + additionalSpeed,
                        1, health);
            }
            context.waveState().waveTimeRemain = 10 + 3 * waveNum;
        }
        this.curState = context.waveState().state;
    }

    public static class RandomPairs {
        public List<int[]> pairs;
        public RandomPairs(int row, int column) {
            pairs = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    pairs.add(new int[]{i, j});  // 使用 int[] 存储 (i, j) 对
                }
            }
        }
        public void shuffle() {
            Collections.shuffle(pairs);
        }
    }
}