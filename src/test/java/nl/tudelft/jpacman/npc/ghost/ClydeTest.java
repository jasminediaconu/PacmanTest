package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClydeTest {
    private static final PacManSprites SPRITES = new PacManSprites();
    private BoardFactory board;
    private LevelFactory level;
    private GhostFactory ghost;

    /**
     * Setup of the Board, Level and Ghost.
     */
    @BeforeEach void setup() {
        board = new BoardFactory(SPRITES);
        ghost = new GhostFactory(SPRITES);
        level = new LevelFactory(SPRITES, ghost, new PointCalculatorLoader().load());
    }

    /**
     *
     */
    @Test void nextAiMoveTest() {

    }

}
