package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 */
public class SingleLevelGameCasesTest extends GameCasesTest {
    @BeforeEach
    @Override
    public void setup() {
        setLauncher(new Launcher().withMapFile("/simplemap.txt"));
        getLauncher().launch();
        setGame(getLauncher().getGame());
        setPlayer(getGame().getPlayers().get(0));
    }
}
