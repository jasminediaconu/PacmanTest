package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

/**
 *
 */
public class MultiLevelGame extends Game {
    /**
     * The player of this game.
     */
    private final Player player;

    /**
     * The level of this game.
     */
    private final Level level;

    /**
     *
     * @param player
     * @param level
     * @param pointCalculator
     */
    public MultiLevelGame(Player player, Level level, PointCalculator pointCalculator) {
        super(pointCalculator);
        this.player = player;
        this.level = level;
        this.level.registerPlayer(player);

    }


    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
