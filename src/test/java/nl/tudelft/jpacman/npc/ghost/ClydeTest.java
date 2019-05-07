package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for the ghost Clyde.
 */
public class ClydeTest {
    private static final PacManSprites SPRITES = new PacManSprites();
    private BoardFactory boardFactory;
    private LevelFactory levelFactory;
    private GhostFactory ghostFactory;
    private PlayerFactory playerFactory;
    private GhostMapParser ghostMapParser;

    /**
     * Setup of the Board, Level and Ghost.
     */
    @BeforeEach void setup() {
        boardFactory = new BoardFactory(SPRITES);
        ghostFactory = new GhostFactory(SPRITES);
        playerFactory = new PlayerFactory(SPRITES);
        levelFactory = new LevelFactory(SPRITES, ghostFactory, new PointCalculatorLoader().load());
        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /**
     * On Point testing.
     */
    @Test void nextAiMoveTestWithin8Squares() {
        List<String> grid = new ArrayList<>();
        grid.add("###########");
        grid.add("#P       C#");
        grid.add("###########");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        Clyde clydeGhost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clydeGhost.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
    }

    /**
     * In point testing.
     */
    @Test void nextAiMoveTestLessThan8Squares() {
        List<String> grid = new ArrayList<>();
        grid.add("#############");
        grid.add("#P    C     #");
        grid.add("#############");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.SOUTH);
        Clyde clydeGhost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clydeGhost.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
    }

    /**
     * Off Point testing.
     */
    @Test void nextAiMoveTestWithin9Squares() {
        List<String> grid = new ArrayList<>();
        grid.add("#############");
        grid.add("#P        C #");
        grid.add("#############");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.SOUTH);
        Clyde clydeGhost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clydeGhost.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
    }

    /**
     * Out point testing.
     */
    @Test void nextAiMoveTestMoreThan9Squares() {
        List<String> grid = new ArrayList<>();
        grid.add("#############");
        grid.add("#P         C#");
        grid.add("#############");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.SOUTH);
        Clyde clydeGhost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clydeGhost.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
    }
}
