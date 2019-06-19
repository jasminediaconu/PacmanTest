package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.MultiLevelGame;

/**
 * Creates and launches the JPacMan UI with multiple levels.
 */
public class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;


    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }
}
