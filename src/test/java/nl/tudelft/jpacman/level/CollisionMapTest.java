package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * CollisionInteractionMap, DefaultPlayerInteractionMap, PlayerCollisions test.
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
     * @param player Player type
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Set pellet.
     * @param pellet Pellet type
     */
    public void setPellet(Pellet pellet) {
        this.pellet = pellet;
    }

    /**
     * Set ghost.
     * @param ghost Ghost type
     */
    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    /**
     * Set point calculator.
     * @param pointCalculator PointCalculator type
     */
    public void setPointCalculator(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
    }

    /**
     * Set player collisions.
     * @param collisionMap CollisionMap type
     */
    public void setPlayerCollisions(CollisionMap collisionMap) {
        this.collisionMap = collisionMap;
    }

    /**
     * Get point calculator.
     * @return point calculator
     */
    public PointCalculator getPointCalculator() {
        return pointCalculator;
    }

    /**
     * Test collisions between two players.
     */
    @Test
    void testCollisionBetweenPlayers() {
        Player pl = Mockito.mock(Player.class);
        collisionMap.collide(player, pl);
        Mockito.verifyZeroInteractions(player, pl);
    }

    /**
     * Test collisions between two ghosts.
     */
    @Test
    void testCollisionGhostGhost() {
        Ghost g1 = Mockito.mock(Ghost.class);
        collisionMap.collide(ghost, g1);
        Mockito.verifyZeroInteractions(ghost, g1);
    }

    /**
     * Test collisions between two pellets.
     */
    @Test
    void testCollisionPelletPellet() {
        Pellet p1 = Mockito.mock(Pellet.class);
        collisionMap.collide(pellet, p1);
        Mockito.verifyZeroInteractions(pellet, p1);
    }

    /**
     * Test collisions between a player and a pellet.
     */
    @Test
    void testCollisionPlayerPellet() {
        collisionMap.collide(player, pellet);

        Mockito.verify(pointCalculator, Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet));

        Mockito.verify(pellet, Mockito.times(1)).leaveSquare();
        Mockito.verifyNoMoreInteractions(player, pellet);
    }

    /**
     * Test collisions between a pellet and a player.
     */
    @Test
    void testCollisionPelletPlayer() {
        collisionMap.collide(pellet, player);

        Mockito.verify(pointCalculator, Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet));

        Mockito.verify(pellet, Mockito.times(1)).leaveSquare();
        Mockito.verifyNoMoreInteractions(pellet, player);
    }

    /**
     * Test collisions between a player and a ghost (the player bumps in the ghost).
     */
    @Test
    void testCollisionPlayerGhost() {
        collisionMap.collide(player, ghost);

        Mockito.verify(pointCalculator, Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost));

        Mockito.verify(player, Mockito.times(1)).setAlive(false);
        Mockito.verify(player, Mockito.times(1)).setKiller(Mockito.eq(ghost));
        Mockito.verifyNoMoreInteractions(player, ghost);
    }

    /**
     * Test collisions between a ghost and a player (the ghost kills the player).
     */
    @Test
    void testCollisionGhostPlayer() {
        collisionMap.collide(ghost, player);

        Mockito.verify(pointCalculator, Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost));

        Mockito.verify(player, Mockito.times(1)).setAlive(false);
        Mockito.verify(player, Mockito.times(1)).setKiller(Mockito.eq(ghost));
        Mockito.verifyNoMoreInteractions(player, ghost);
    }

    /**
     * Test collisions between a ghost and a pellet.
     */
    @Test
    void testCollisionGhostPellet() {
        collisionMap.collide(ghost, pellet);
        Mockito.verifyZeroInteractions(ghost, pellet);
    }

    /**
     * Test collisions between a pellet and a ghost.
     */
    @Test
    void testCollisionPelletGhost() {
        collisionMap.collide(pellet, ghost);
        Mockito.verifyZeroInteractions(pellet, ghost);
    }
}
