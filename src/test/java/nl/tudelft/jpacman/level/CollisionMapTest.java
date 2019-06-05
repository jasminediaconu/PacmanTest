package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.eq;

/**
 * CollisionMap test.
 * Tests all the classes that use the CollisionMap interface.
 */
public abstract class CollisionMapTest {
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private PointCalculator pointCalculator;
    private CollisionMap collisionMap;

    /**
     * Set player.
     *
     * @param player Player type
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Set pellet.
     *
     * @param pellet Pellet type
     */
    public void setPellet(Pellet pellet) {
        this.pellet = pellet;
    }

    /**
     * Set ghost.
     *
     * @param ghost Ghost type
     */
    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    /**
     * Set point calculator.
     *
     * @param pointCalculator PointCalculator type
     */
    public void setPointCalculator(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
    }

    /**
     * Set player collisions.
     *
     * @param collisionMap CollisionMap type
     */
    public void setPlayerCollisions(CollisionMap collisionMap) {
        this.collisionMap = collisionMap;
    }

    /**
     * Get point calculator.
     *
     * @return point calculator
     */
    public PointCalculator getPointCalculator() {
        return pointCalculator;
    }

    /**
     * Initialize required parameters for the test cases.
     */
    @BeforeEach
    abstract void init();

    /**
     * Test collisions between two players.
     */
    @Test
    void testCollisionBetweenPlayers() {
        Player pl = mock(Player.class);
        collisionMap.collide(player, pl);
        verifyZeroInteractions(player, pl);
    }

    /**
     * Test collisions between two ghosts.
     */
    @Test
    void testCollisionGhostGhost() {
        Ghost g1 = mock(Ghost.class);
        collisionMap.collide(ghost, g1);
        verifyZeroInteractions(ghost, g1);
    }

    /**
     * Test collisions between two pellets.
     */
    @Test
    void testCollisionPelletPellet() {
        Pellet p1 = mock(Pellet.class);
        collisionMap.collide(pellet, p1);
        verifyZeroInteractions(pellet, p1);
    }

    /**
     * Test collisions between a player and a pellet.
     */
    @Test
    void testCollisionPlayerPellet() {
        collisionMap.collide(player, pellet);

        verify(pointCalculator, times(1)).consumedAPellet(
            eq(player), eq(pellet));

        verify(pellet, times(1)).leaveSquare();
        verifyNoMoreInteractions(player, pellet);
    }

    /**
     * Test collisions between a pellet and a player.
     */
    @Test
    void testCollisionPelletPlayer() {
        collisionMap.collide(pellet, player);

        verify(pointCalculator, times(1)).consumedAPellet(
            eq(player), eq(pellet));

        verify(pellet, times(1)).leaveSquare();
        verifyNoMoreInteractions(pellet, player);
    }

    /**
     * Test collisions between a player and a ghost (the player bumps in the ghost).
     */
    @Test
    void testCollisionPlayerGhost() {
        collisionMap.collide(player, ghost);

        verify(pointCalculator, times(1)).collidedWithAGhost(
            eq(player), eq(ghost));

        verify(player, times(1)).setAlive(false);
        verify(player, times(1)).setKiller(eq(ghost));
        verifyNoMoreInteractions(player, ghost);
    }

    /**
     * Test collisions between a ghost and a player (the ghost kills the player).
     */
    @Test
    void testCollisionGhostPlayer() {
        collisionMap.collide(ghost, player);

        verify(pointCalculator, times(1)).collidedWithAGhost(
            eq(player), eq(ghost));

        verify(player, times(1)).setAlive(false);
        verify(player, times(1)).setKiller(eq(ghost));
        verifyNoMoreInteractions(ghost, player);
    }

    /**
     * Test collisions between a ghost and a pellet.
     */
    @Test
    void testCollisionGhostPellet() {
        collisionMap.collide(ghost, pellet);
        verifyZeroInteractions(ghost, pellet);
    }

    /**
     * Test collisions between a pellet and a ghost.
     */
    @Test
    void testCollisionPelletGhost() {
        collisionMap.collide(pellet, ghost);
        verifyZeroInteractions(pellet, ghost);
    }
}
