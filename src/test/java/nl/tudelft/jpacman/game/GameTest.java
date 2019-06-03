package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private Game game;
    private Level level;

    @BeforeEach
    public void setup() {
        PlayerFactory playerFactory = Mockito.mock(PlayerFactory.class);
        level = Mockito.mock(Level.class);
        PointCalculator pointCalculator = Mockito.mock(PointCalculator.class);
        Player player = Mockito.mock(Player.class);

        Mockito.when(playerFactory.createPacMan()).thenReturn(player);

        GameFactory gameFactory = new GameFactory(playerFactory);

        game = gameFactory.createSinglePlayerGame(level, pointCalculator);
    }

    @Test
    public void startPlayerAliveRemainingPelletsTest() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(true);
        Mockito.when(level.remainingPellets()).thenReturn(2);
        game.start();
        assertThat(game.isInProgress()).isTrue();
    }

    @Test
    public void startPlayerDeadRemainingPelletsTest() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(false);
        Mockito.when(level.remainingPellets()).thenReturn(2);
        game.start();
        assertThat(game.isInProgress()).isFalse();
    }

    @Test
    public void startPlayerAliveNoRemainingPelletsTest() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(true);
        Mockito.when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertThat(game.isInProgress()).isFalse();
    }

    @Test
    public void startPlayerDeadNoRemainingPelletsTest() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(false);
        Mockito.when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertThat(game.isInProgress()).isFalse();
    }

    @Test
    public void startGameAlreadyInProgressTest() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(true);
        Mockito.when(level.remainingPellets()).thenReturn(2);
        game.start();
        game.start();
        assertThat(game.isInProgress()).isTrue();
    }
}
