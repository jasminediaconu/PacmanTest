package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * User story 2 test.
 */
public class UserStory2Test {

    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    public void before() {
        launcher = new Launcher();
    }

    private Game getGame() {
        return launcher.getGame();
    }

    /**
     * 2.1 Scenario, pacman eats pellet.
     */
    @Test
    public void consumePellet() {
        launcher.withMapFile("/scenario-2.1.txt");
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);
        Square startingSquare = player.getSquare();
        Square destination = startingSquare.getSquareAt(Direction.WEST);

        assertThat(player.getScore()).isEqualTo(0);
        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(1);

        getGame().move(player, Direction.WEST);

        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(0);
        assertThat(player.getScore()).isEqualTo(10);
        assertThat(player.getSquare()).isEqualTo(destination);
    }


}
