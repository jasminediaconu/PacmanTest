package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

/**
 * DefaultPlayerInteractionMap test.
 * It checks collisions for the DefaultPlayerInteractionMap class.
 */
public class DefaultPlayerInteractionMapTest extends CollisionMapTest {

    /**
     * Mock Player, Pellet, Ghost classes and sets the PlayerCollisions object.
     */
    @BeforeEach
    @Override
    void init() {
        this.setPlayer(Mockito.mock(Player.class));
        this.setPellet(Mockito.mock(Pellet.class));
        this.setGhost(Mockito.mock(Ghost.class));
        this.setPointCalculator(Mockito.mock(PointCalculator.class));
        this.setPlayerCollisions(new DefaultPlayerInteractionMap(this.getPointCalculator()));
    }
}
