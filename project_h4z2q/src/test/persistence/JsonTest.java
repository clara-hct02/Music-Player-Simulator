package persistence;

import model.Playlist;
import model.Song;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code influenced by JsonSerializationDemo at
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkSong(String name, Boolean favourite, Song song) {
        assertEquals(name, song.getName());
        assertEquals(favourite, song.getLike());
    }
}

