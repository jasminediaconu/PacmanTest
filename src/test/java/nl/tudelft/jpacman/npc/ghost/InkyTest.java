package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for the ghost inky.
 */
public class InkyTest {
    private static final PacManSprites SPRITES = new PacManSprites();
    private BoardFactory boardFactory;
    private LevelFactory levelFactory;
    private GhostFactory ghostFactory;
    private PlayerFactory playerFactory;
    private GhostMapParser ghostMapParser;

    /**
     * Setup of the Board, Level and Ghost.
     */
    @BeforeEach
    void setup() {
        boardFactory = new BoardFactory(SPRITES);
        ghostFactory = new GhostFactory(SPRITES);
        playerFactory = new PlayerFactory(SPRITES);
        levelFactory = new LevelFactory(SPRITES, ghostFactory, new PointCalculatorLoader().load());
        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /**
     * Bad Weather. No blinky ghost.
     */
    @Test
    void nextAiMoveNoBlinkyTest() {
        List<String> grid = new ArrayList<>();
        grid.add("#####################");
        grid.add("#        P         I#");
        grid.add("#####################");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.WEST);
        Inky inkyGhost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inkyGhost.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * Bad Weather. No Player.
     */
    @Test
    void nextAiMoveNoPlayer() {
        List<String> grid = new ArrayList<>();
        grid.add("#####################");
        grid.add("#               B  I#");
        grid.add("#####################");

        Level level = ghostMapParser.parseMap(grid);
        Inky inkyGhost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inkyGhost.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * Bad Weather. No path between player and inky.
     */
    @Test
    void nextAiMoveNoPathPlayerInkyTest() {
        List<String> grid = new ArrayList<>();
        grid.add("#####################");
        grid.add("#        P    B #  I#");
        grid.add("#####################");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.WEST);
        Inky inkyGhost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inkyGhost.nextAiMove()).isEqualTo(Optional.empty());
    }

    /**
     * Good weather. Inky follows blinky and moves towards pacman.
     */
    @Test
    void nextAiMoveInkyMovesTowardsPlayerTest() {
        List<String> grid = new ArrayList<>();
        grid.add("#####################");
        grid.add("#        P    B   I #");
        grid.add("#####################");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        Inky inkyGhost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inkyGhost.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
    }

    /**
     * Good weather. Inky moves towards pacman and does not follow blinky.
     */
    @Test
    void nextAiMoveInkyMovesAwayFromPlayerTest() {
        List<String> grid = new ArrayList<>();
        grid.add("#####################");
        grid.add("#        P    I   B #");
        grid.add("#####################");

        Level level = ghostMapParser.parseMap(grid);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        Inky inkyGhost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inkyGhost.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
    }

}
