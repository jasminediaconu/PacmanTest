package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.MultiLevelLauncher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class that contains all the tests related to the MultiLevel games.
 */
public class MultiLevelGameCasesTest extends GameCasesTest {

    @BeforeEach
    @Override
    public void setup() {
        List<String> files = Arrays.asList("/simplemap.txt", "/Level 2.txt");
        setLauncher(new MultiLevelLauncher().withListOfFiles(files));
        getLauncher().launch();
        setGame(getLauncher().getGame());
        setPlayer(getGame().getPlayers().get(0));
    }

    /**
     * Test that checks the level is changed when there are more levels to be played.
     */
    @Test
    public void nextLevelAfterWin() {
        getGame().start();

        assertThat(getGame().isInProgress()).isTrue();
        assertThat(((MultiLevelGame) getGame()).getCurrentLevel()).isEqualTo(0);

        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.EAST);
        getGame().move(player, Direction.EAST);

        assertThat(((MultiLevelGame) getGame()).getCurrentLevel()).isEqualTo(1);

        getGame().start();

        assertThat(getGame().isInProgress()).isTrue();
        assertThat(player.isAlive()).isTrue();
    }

    /**
     * Test to check the game ends once the last level is completed.
     */
    @Test
    public void endAfterLastLevelTest() {
        getGame().start();

        assertThat(getGame().isInProgress()).isTrue();
        assertThat(((MultiLevelGame) getGame()).getCurrentLevel()).isEqualTo(0);

        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.EAST);
        getGame().move(player, Direction.EAST);

        assertThat(((MultiLevelGame) getGame()).getCurrentLevel()).isEqualTo(1);

        Level lastLevel = getGame().getLevel();

        getGame().move(player, Direction.WEST);

        assertThat(getGame().getLevel()).isEqualTo(lastLevel);
        assertThat(getGame().isInProgress()).isFalse();
    }
}
