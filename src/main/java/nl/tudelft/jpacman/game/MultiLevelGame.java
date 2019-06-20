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
    private final List<Level> levels;

    /**
     * The current level.
     */
    private int currentLevel;

    /**
     *
     * @param player Player type
     * @param levels Level type
     * @param pointCalculator PointCalculator type
     */
    public MultiLevelGame(Player player, List<Level> levels, PointCalculator pointCalculator) {
        super(pointCalculator);
        this.player = player;
        this.levels = levels;
        this.currentLevel = 0;
        this.levels.get(currentLevel).registerPlayer(player);

    }


    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    @Override
    public Level getLevel() {
        return levels.get(currentLevel);
    }

    @Override
    public void levelWon() {
        currentLevel++;
        if (currentLevel < levels.size()) {
            levels.get(currentLevel).registerPlayer(player);
        }
        stop();
    }

    /**
     * Getter for the current level for testing purposes.
     * @return the number corresponding to the current level starting with 0.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
}
