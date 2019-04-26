package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    /**
     * Board creation test
     */
    @Test
    void testBoard() {
        Square[][] grid = new BasicSquare[1][1];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }

        Board board = new Board(grid);

        assertThat(board.getHeight()).isEqualTo(1);
        assertThat(board.getWidth()).isEqualTo(1);
    }

    /**
     * squareAt test
     */
    @Test
    void testSquareAt() {
        Square[][] grid = new BasicSquare[1][0];
        Board board = new Board(grid);

        assertThat(board.squareAt(1,0)).isEqualTo(grid[1][0]);

    }

}
