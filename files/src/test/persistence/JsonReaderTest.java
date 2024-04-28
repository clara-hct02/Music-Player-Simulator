package persistence;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Code influenced by JsonSerializationDemo at
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Playlist> playlists = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyApp() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyApp.json");
        try {
            List<Playlist> playlists = reader.read();
            assertEquals(0, playlists.size());
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testReaderGeneralApp() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralApp.json");
        try {
            List<Playlist> playlists = reader.read();
            assertEquals(2, playlists.size());
            assertEquals("A", playlists.get(0).getName());
            assertEquals("B", playlists.get(1).getName());

            List<Song> songsA = playlists.get(0).getListOfSongs();
            assertEquals(2, songsA.size());
            List<Song> songsB = playlists.get(1).getListOfSongs();
            assertEquals(1, songsB.size());

            checkSong("AAA", false, songsA.get(0));
            checkSong("AAAA", true, songsA.get(1));
            checkSong("BBB", false, songsB.get(0));
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }
}
