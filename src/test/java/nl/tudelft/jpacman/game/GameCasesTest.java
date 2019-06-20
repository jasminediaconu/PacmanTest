package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Game test for the user stories cases.
 */
public abstract class GameCasesTest {

    private Launcher launcher;
    private Game game;
    private Player player;


    /**
     * Sets the launcher to be used in the tests.
     * @param launcher launcher to be used.
     */
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * Gets the launcher that was set to be used in the tests.
     */
    public Launcher getLauncher() {
        return launcher;
    }

    /**
     * Sets the game to be used in the tests.
     * @param game game to be used.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets the game that was set to be used in the tests.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the player to be used in the tests.
     * @param player player to be used.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Setting up the variables for each test.
     */
    @BeforeEach()
    public abstract void setup();

    /**
     * When the user consumes a pellet that is not the last one.
     */
    @Test
    void testConsumePellet() {
        final int score = 10;

        assertThat(game.isInProgress()).isFalse();

        game.start();

        assertThat(game.isInProgress()).isTrue();

        game.move(player, Direction.EAST);

        assertThat(player.getScore()).isEqualTo(score);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }

    /**
     * When the user collides with a ghost and loses the game.
     */
    @Test
    void testLose() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);

        game.getLevel().addObserver(levelObserver);
        assertThat(game.isInProgress()).isFalse();

        game.start();
        game.move(player, Direction.SOUTH);
        game.move(player, Direction.EAST);

        assertThat(player.isAlive()).isFalse();
        Mockito.verify(levelObserver, Mockito.times(1)).levelLost();
        assertThat(game.isInProgress()).isFalse();

        game.stop();
    }


    /**
     * When the user moves to an empty cell.
     */
    @Test
    void testMoveEmpty() {
        assertThat(game.isInProgress()).isFalse();

        game.start();
        game.move(player, Direction.SOUTH);

        assertThat(player.isAlive()).isTrue();
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }

    /**
     * When the user moves towards a wall.
     */
    @Test
    void testMoveWall() {
        assertThat(game.isInProgress()).isFalse();
        game.start();

        Square square = player.getSquare();

        game.move(player, Direction.WEST);

        assertThat(player.getSquare()).isEqualTo(square);
        assertThat(player.isAlive()).isTrue();
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }


    /**
     * When the user stops and resumes the game.
     */
    @Test
    void testStopStart() {
        assertThat(game.isInProgress()).isFalse();

        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.stop();
        assertThat(game.isInProgress()).isFalse();

        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }
}
