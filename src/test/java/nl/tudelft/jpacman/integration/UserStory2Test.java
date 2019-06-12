package nl.tudelft.jpacman.integration;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.Navigation;
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
     * S2.1 Scenario: The player consumes.
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

        // The player actually moves to the pellet's square
        assertThat(player.getSquare()).isEqualTo(destination);
        //The player earns the points
        assertThat(player.getScore()).isEqualTo(10);
        // The pellet disappears
        assertThat(getGame().getLevel().remainingPellets()).isEqualTo(0);
    }

    /**
     * S2.2 Scenario: The player moves on empty square.
     */
    @Test
    public void emptySquare() {
        launcher.withMapFile("/scenario-2.2.txt");
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        Square startingSquare = player.getSquare();
        Square destination = startingSquare.getSquareAt(Direction.WEST);

        assertThat(player.getScore()).isEqualTo(0);

        getGame().move(player, Direction.WEST);

        // The player actually moves to the pellet's square
        assertThat(player.getSquare()).isEqualTo(destination);
        //The score doesn't change
        assertThat(player.getScore()).isEqualTo(0);
    }

    /**
     * S2.3 Scenario: The move fails.
     */
    @Test
    public void moveDenied() {
        launcher.withMapFile("/scenario-2.3.txt");
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        Square startingSquare = player.getSquare();
        Square destination = startingSquare.getSquareAt(Direction.WEST);

        getGame().move(player, Direction.WEST);

        assertThat(player.getSquare()).isNotEqualTo(destination);
    }

    /**
     * S2.4 Scenario: The player dies.
     */
    @Test
    public void playerDies() {
        launcher.withMapFile("/simplemap.txt");
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);
        //Clyde clydeGhost = Navigation.findUnitInBoard(Clyde.class, getGame().getLevel().getBoard());

        getGame().move(player, Direction.WEST);

        //player.setKiller(clydeGhost);

        assertThat(player.isAlive()).isFalse();
        assertThat(getGame().isInProgress()).isFalse();
    }

    /**
     * S2.5 Scenario: Player wins, extends S2.1.
     */
    @Test
    public void playerWins() {
        launcher.withMapFile("/simplemap.txt");
        launcher.launch();
        getGame().start();
        Player player = getGame().getPlayers().get(0);

        // The game ends
        assertThat(getGame().isInProgress()).isFalse();
    }
}
