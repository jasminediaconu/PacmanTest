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
public class GameCasesTest {

    private Launcher launcher;
    private Game game;
    private Player player;

    /**
     * Setting up the variables for each test.
     */
    @BeforeEach()
    public void setup() {
        launcher = new Launcher().withMapFile("/simplemap.txt");
        launcher.launch();
        game = launcher.getGame();
        player = game.getPlayers().get(0);
    }

    /**
     * When the user consumes a pellet that is not the last one.
     */
    @Test
    void testConsumePellet() {

        final int score = 10;
        assertThat(game.isInProgress()).isFalse();

        game.start();
        game.move(player, Direction.EAST);

        assertThat(player.getScore()).isEqualTo(score);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }


    /**
     * When the user consumes eats the last pellet and wins the game.
     */
    @Test
    void testWin() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);

        launcher.launch();
        game.getLevel().addObserver(levelObserver);
        assertThat(game.isInProgress()).isFalse();

        game.start();
        game.move(player, Direction.EAST);
        game.move(player, Direction.EAST);

        assertThat(player.isAlive()).isTrue();
        Mockito.verify(levelObserver, Mockito.times(1)).levelWon();
        assertThat(game.isInProgress()).isFalse();

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
