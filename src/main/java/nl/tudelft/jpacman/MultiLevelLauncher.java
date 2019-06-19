package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;

/**
 * Creates and launches the JPacMan UI with multiple levels.
 */
public class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;


    @Override
    public Game makeGame() {
        GameFactory gf = getGameFactory();
        Level level = makeLevel();
        multiGame = gf.createMultiLevelGame(level, super.loadPointCalculator());
        return multiGame;
    }

    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }
}
