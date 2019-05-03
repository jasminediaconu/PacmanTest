package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     *
     */
    @Test void nextAiMoveTestWithin8Squares() {
        List<String> grid = new ArrayList<>();
        grid.add(("############"));
        grid.add(("#P        C#"));
        grid.add(("############"));

        Level level = ghostMapParser.parseMap(grid);
    }

}
