package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.MultiLevelLauncher;
import org.junit.jupiter.api.BeforeEach;

public class MultiLevelGameCasesTest extends GameCasesTest {

    @BeforeEach
    @Override
    public void setup() {
        setLauncher(new MultiLevelLauncher().withMapFile("/simplemap.txt"));
        getLauncher().launch();
        setGame(getLauncher().getGame());
        setPlayer(getGame().getPlayers().get(0));

    }
}
