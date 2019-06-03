package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;



public class MapParserTest {
    private LevelFactory levelFactory;
    private BoardFactory boardFactory;
    private MapParser mapParser;

    @BeforeEach
    public void setup() {
        levelFactory = mock(LevelFactory.class);
        boardFactory = mock(BoardFactory.class);
        mapParser = new MapParser(levelFactory, boardFactory);
        Square square = mock(Square.class);
        Pellet pellet = mock(Pellet.class);
        Ghost ghost = mock(Ghost.class);
        when(boardFactory.createGround()).thenReturn(square);
        when(levelFactory.createPellet()).thenReturn(pellet);
        when(levelFactory.createGhost()).thenReturn(ghost);
    }

    @Test
    public void parseMapCharArrayGoodWeatherTest() {
        char[][] map = {{'#', 'P', 'G', '.', ' ', '#'}};

        try {
            Level level = mapParser.parseMap(map);
        }
        catch (Exception e) {
            fail("Exception was thrown. " + e.getMessage());
        }

        //TODO ask jasmine about what she thinks.
    }

    @Test
    public void parseMapStringListGoodWeatherTest() {
        List<String> map = Arrays.asList("#PG. #");

        try {
            Level level = mapParser.parseMap(map);
        }
        catch (Exception e) {
            fail("Exception was thrown. " + e.getMessage());
        }
    }

    @Test
    public void parseMapInputStreamGoodWeatherTest() throws IOException {
        byte[] data = "#PG. #".getBytes();

        InputStream input = new ByteArrayInputStream(data);
        try {
            Level level = mapParser.parseMap(input);
        }
        catch (Exception e) {
            fail("Exception was thrown. " + e.getMessage());
        }
    }

    @Test
    public void parseMapCharArrayWrongCharactersBadWeather() {
        char[][] map = {{'#', '!', 'G', '.', ' ', '#'}};
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    @Test
    public void parseMapStringListNullObjectBadWeather() {
        List<String> map = null;
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    @Test
    public void parseMapStringListEmptyListBadWeather() {
        List<String> map = new ArrayList<>();
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    @Test
    public void parseMapStringListNoCharactersBadWeather() {
        List<String> map = Arrays.asList("");
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    @Test
    public void parseMapStringListDifferentWidthsBadWeather() {
        List<String> map = new ArrayList<>();
        map.add("#P#");
        map.add("G");
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    @Test
    public void parseMapFileNotExistentBadWeather() {
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap("test.txt"));
    }


}
