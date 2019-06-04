package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Game mockito test.
 */
public class GameTest {
    private Game game;
    private Level level;

    /**
     * Initialize variables for the test cases.
     */
    @BeforeEach
    public void setup() {
        PlayerFactory playerFactory = mock(PlayerFactory.class);
        level = mock(Level.class);
        PointCalculator pointCalculator = mock(PointCalculator.class);
        Player player = mock(Player.class);

        when(playerFactory.createPacMan()).thenReturn(player);

        GameFactory gameFactory = new GameFactory(playerFactory);

        game = gameFactory.createSinglePlayerGame(level, pointCalculator);
    }

    /**
     * If the player is still alive continue the game.
     */
    @Test
    public void startPlayerAliveRemainingPelletsTest() {
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(2);
        game.start();
        assertThat(game.isInProgress()).isTrue();
    }

    /**
     * If the player is dead the game ends.
     */
    @Test
    public void startPlayerDeadRemainingPelletsTest() {
        when(level.isAnyPlayerAlive()).thenReturn(false);
        when(level.remainingPellets()).thenReturn(2);
        game.start();
        assertThat(game.isInProgress()).isFalse();
    }

    /**
     * If the player is alive but there are no more pellets, the game ends.
     */
    @Test
    public void startPlayerAliveNoRemainingPelletsTest() {
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertThat(game.isInProgress()).isFalse();
    }

    /**
     * If the player is dead and there are no more pellets, the game ends.
     */
    @Test
    public void startPlayerDeadNoRemainingPelletsTest() {
        when(level.isAnyPlayerAlive()).thenReturn(false);
        when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertThat(game.isInProgress()).isFalse();
    }

    /**
     * If the game is already in progress when you start it, then continue the game.
     */
    @Test
    public void startGameAlreadyInProgressTest() {
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(2);
        game.start();
        game.start();
        assertThat(game.isInProgress()).isTrue();
    }
}
