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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;



/**
 * MapParser class test.
 */
public class MapParserTest {
    private MapParser mapParser;

    /**
     * Initialize LevelFactory, BoardFactory, MapParser.
     */
    @BeforeEach
    public void setup() {
        LevelFactory levelFactory = mock(LevelFactory.class);
        BoardFactory boardFactory = mock(BoardFactory.class);
        mapParser = new MapParser(levelFactory, boardFactory);
        Square square = mock(Square.class);
        Pellet pellet = mock(Pellet.class);
        Ghost ghost = mock(Ghost.class);
        when(boardFactory.createGround()).thenReturn(square);
        when(levelFactory.createPellet()).thenReturn(pellet);
        when(levelFactory.createGhost()).thenReturn(ghost);
    }

    /**
     * Good weather case. Map parsed as characters.
     */
    @Test
    public void parseMapCharArrayGoodWeatherTest() {
        char[][] map = {{'#', 'P', 'G', '.', ' ', '#'}};

        try {
            mapParser.parseMap(map);
        }
        catch (Exception e) {
            fail("Exception was thrown. " + e.getMessage());
        }
    }

    /**
     * Good weather case. Map as an ArrayList.
     */
    @Test
    public void parseMapStringListGoodWeatherTest() {
        List<String> map = Arrays.asList("#PG. #");

        try {
            mapParser.parseMap(map);
        }
        catch (Exception e) {
            fail("Exception was thrown. " + e.getMessage());
        }
    }

    /**
     * Good weather case.
     * @throws IOException for input.
     */
    @Test
    public void parseMapInputStreamGoodWeatherTest() throws IOException {
        byte[] data = "#PG. #".getBytes(Charset.forName("UTF-8"));

        InputStream input = new ByteArrayInputStream(data);
        try {
            mapParser.parseMap(input);
        }
        catch (Exception e) {
            fail("Exception was thrown. " + e.getMessage());
        }
    }

    /**
     * Bad weather case. Invalid character in the map.
     */
    @Test
    public void parseMapCharArrayWrongCharactersBadWeather() {
        char[][] map = {{'#', '!', 'G', '.', ' ', '#'}};
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    /**
     * Bad weather case. The map is null.
     */
    @Test
    public void parseMapStringListNullObjectBadWeather() {
        List<String> map = null;
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    /**
     * Bad weather case. The map is empty.
     */
    @Test
    public void parseMapStringListEmptyListBadWeather() {
        List<String> map = new ArrayList<>();
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    /**
     * Bad weather case. The map has no character.
     */
    @Test
    public void parseMapStringListNoCharactersBadWeather() {
        List<String> map = Arrays.asList("");
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    /**
     * Bad weather case. The lines of the map have different widths.
     */
    @Test
    public void parseMapStringListDifferentWidthsBadWeather() {
        List<String> map = new ArrayList<>();
        map.add("#P#");
        map.add("G");
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap(map));
    }

    /**
     * Bad weather case. The map file doesn't exist.
     */
    @Test
    public void parseMapFileNotExistentBadWeather() {
        assertThrows(PacmanConfigurationException.class, () -> mapParser.parseMap("test.txt"));
    }
}
