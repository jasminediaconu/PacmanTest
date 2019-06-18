package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.MultiLevelGame;

/**
 *
 */
public class MultiLevelLauncher extends  Launcher {

    private MultiLevelGame multiGame;


    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }
}
