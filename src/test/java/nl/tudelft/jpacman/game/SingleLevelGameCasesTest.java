package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for single player and single level game.
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


    /**
     * When the user consumes eats the last pellet and wins the game.
     */
    @Test
    void testWin() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);

        getGame().getLevel().addObserver(levelObserver);
        assertThat(getGame().isInProgress()).isFalse();

        getGame().start();

        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.EAST);
        getGame().move(player, Direction.EAST);

        assertThat(player.isAlive()).isTrue();
        Mockito.verify(levelObserver, Mockito.times(1)).levelWon();
        assertThat(getGame().isInProgress()).isFalse();

        getGame().stop();
    }
}
