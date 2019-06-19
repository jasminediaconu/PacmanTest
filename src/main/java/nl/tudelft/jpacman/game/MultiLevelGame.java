package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

/**
 * A game with one player and multiple levels.
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
     * @param player Player type
     * @param level Level type
     * @param pointCalculator PointCalculator type
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
