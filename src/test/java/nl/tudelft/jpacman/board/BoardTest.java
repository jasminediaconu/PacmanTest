package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Tests for Board class.
 */
class BoardTest {

    /**
     * Board creation test.
     */
    @Test
    void testBoard() {
        Square[][] grid = new BasicSquare[1][1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }

        Board board = new Board(grid);

        assertThat(board.getHeight()).isEqualTo(1);
        assertThat(board.getWidth()).isEqualTo(1);
    }

    /**
     * Check if the values are within the borders.
     */
    @Test
    void testBoardWithinBordersTrue() {
        Square[][] grid = new BasicSquare[1][1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }

        Board board = new Board(grid);

        assertThat(board.withinBorders(0, 0)).isTrue();
    }

    /**
     * Check if the values are out of the borders.
     */
    @Test
    void testBoardWithinBordersFalse() {
        Square[][] grid = new BasicSquare[1][1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }

        Board board = new Board(grid);

        assertThat(board.withinBorders(1, 1)).isFalse();
    }

    /**
     * Generate the arguments for the test function.
     * Arguments that test the inputs that generate a return that is true.
     * @return List of arguments
     */
    public static Stream<Arguments> withinBordersParametersTrue() {
        return Stream.of(
            Arguments.of(0, 0),
            Arguments.of(0, 1),
            Arguments.of(1, 0),
            Arguments.of(1, 1)
        );
    }

    /**
     * Test for values that make withinBorders return true.
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    @ParameterizedTest
    @MethodSource("withinBordersParametersTrue")
    public void withinBordersTrueTest(int x, int y) {
        final int size = 2;
        Square[][] grid = new BasicSquare[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }

        Board board = new Board(grid);

        assertThat(board.withinBorders(x, y)).isTrue();
    }

    /**
     * Generate the arguments for the test function.
     * Arguments that test the inputs that generate a return that is false.
     * @return List of arguments
     */
    public static Stream<Arguments> withinBordersParametersFalse() {
        return Stream.of(
            Arguments.of(0, 2),
            Arguments.of(2, 0),
            Arguments.of(2, 2)
        );
    }

    /**
     * Test for values that make withinBorders return false.
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    @ParameterizedTest
    @MethodSource("withinBordersParametersFalse")
    public void withinBordersFalseTest(int x, int y) {
        final int size = 2;
        Square[][] grid = new BasicSquare[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }

        Board board = new Board(grid);

        assertThat(board.withinBorders(x, y)).isFalse();
    }
}
