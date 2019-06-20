package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and launches the JPacMan UI with multiple levels.
 */
public class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;

    private List<String> levelMaps;


    @Override
    public Game makeGame() {
        GameFactory gf = getGameFactory();
        List<Level> levels = new ArrayList<>();

        for (String file : levelMaps) {
            super.withMapFile(file);
            levels.add(makeLevel());
        }

        multiGame = gf.createMultiLevelGame(levels, super.loadPointCalculator());
        return multiGame;
    }

    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    /**
     * Set the names of the files containing the levels' map.
     *
     * @param files
     *            Maps to be used.
     * @return Launcher with levels corresponding to the given maps.
     */
    public MultiLevelLauncher withListOfFiles(List<String> files) {
        this.levelMaps = files;
        return this;
    }


}
