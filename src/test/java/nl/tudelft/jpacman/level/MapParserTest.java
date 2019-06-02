package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.PackageElement;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapParserTest {
    private LevelFactory levelFactory;
    private BoardFactory boardFactory;
    private MapParser mapParser;

    @BeforeEach
    public void setup() {
        levelFactory = mock(LevelFactory.class);
        boardFactory = mock(BoardFactory.class);
        mapParser = new MapParser(levelFactory, boardFactory);
    }

    @Test
    public void parseMapGoodWeatherTest() {
        char[][] map = {{'#', 'P', 'G', '.', ' ', '#'}};
        Square square = mock(Square.class);
        Pellet pellet = mock(Pellet.class);
        Ghost ghost = mock(Ghost.class);
        when(boardFactory.createGround()).thenReturn(square);
        when(levelFactory.createPellet()).thenReturn(pellet);
        when(levelFactory.createGhost()).thenReturn(ghost);

        try {
            Level level = mapParser.parseMap(map);
        }
        catch (Exception e) {
            fail("Exception was thrown");
        }

        //TODO ask jasmine about what she thinks.
    }



}
