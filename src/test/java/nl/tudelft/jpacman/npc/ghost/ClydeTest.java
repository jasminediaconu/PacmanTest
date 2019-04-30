package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClydeTest {
    private static final PacManSprites SPRITES = new PacManSprites();
    private BoardFactory boardFactory;
    private LevelFactory levelFactory;
    private GhostFactory ghostFactory;
    private GhostMapParser ghostMapParser;

    /**
     * Setup of the Board, Level and Ghost.
     */
    @BeforeEach void setup() {
        boardFactory = new BoardFactory(SPRITES);
        ghostFactory = new GhostFactory(SPRITES);
        levelFactory = new LevelFactory(SPRITES, ghostFactory, new PointCalculatorLoader().load());
        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /**
     *
     */
    @Test void nextAiMoveTestWithin8Squares() {
        char[][] grid = { ("############").toCharArray(),
            ("#P        C#").toCharArray(), ("############").toCharArray()};
        Level level = ghostMapParser.parseMap(grid);
    }

}
