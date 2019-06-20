package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.MultiLevelLauncher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiLevelGameCasesTest extends GameCasesTest {

    @BeforeEach
    @Override
    public void setup() {
        List<String> files = Arrays.asList("/simplemap.txt", "Level 2.txt");
        setLauncher(new MultiLevelLauncher().withListOfFiles(files));
        getLauncher().launch();
        setGame(getLauncher().getGame());
        setPlayer(getGame().getPlayers().get(0));

    }

    @Test
    public void nextLevelAfterWin() {
        assertThat(getGame().isInProgress()).isFalse();

        getGame().start();

        assertThat(getGame().isInProgress()).isTrue();

        Level initialLevel = getGame().getLevel();
        Player player = getGame().getPlayers().get(0);

        getGame().move(player, Direction.WEST);
        getGame().move(player, Direction.WEST);

        assertThat(getGame().getLevel()).isNotEqualTo(initialLevel);
        assertThat(getGame().isInProgress()).isTrue();

    }

    @Test
    public void endAfterLastLevelTest() {
        assertThat(getGame().isInProgress()).isFalse();

        getGame().start();

        assertThat(getGame().isInProgress()).isTrue();

        Player player = getGame().getPlayers().get(0);
        Level firstLevel = getGame().getLevel();

        getGame().move(player, Direction.WEST);
        getGame().move(player, Direction.WEST);

        assertThat(getGame().getLevel()).isNotEqualTo(firstLevel);

        Level lastLevel = getGame().getLevel();

        getGame().move(player, Direction.EAST);

        assertThat(getGame().getLevel()).isEqualTo(lastLevel);
        assertThat(getGame().isInProgress()).isFalse();
    }
}
