package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;

/**
 * Player collision mockito test.
 * It checks collisions for the PlayerCollisions class.
 */
public class PlayerCollisionsTest extends CollisionMapTest {

    /**
     * Mock Player, Pellet, Ghost classes and sets the PlayerCollisions object.
     */
    @BeforeEach
    @Override
    void init() {
        this.setPlayer(mock(Player.class));
        this.setPellet(mock(Pellet.class));
        this.setGhost(mock(Ghost.class));
        this.setPointCalculator(mock(PointCalculator.class));
        this.setPlayerCollisions(new PlayerCollisions(this.getPointCalculator()));
    }
}
