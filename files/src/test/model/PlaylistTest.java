package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistTest {
    private Playlist p1;
    private Song s1;
    private Song s2;

    @BeforeEach
    public void runBefore() {
        p1 = new Playlist("Happy Songs");
        s1 = new Song("Hi There");
        s2 = new Song("Hello World");
    }

    @Test
    public void testConstructor() {
        assertEquals("Happy Songs", p1.getName());
    }

    @Test
    public void testAddSong() {
        List<Song> list;
        List<String> names;

        p1.addSong(s1);
        p1.addSong(s2);
        list = p1.getListOfSongs();
        names = p1.getSongNames();

        assertEquals("Hi There", list.get(0).getName());
        assertEquals("Hello World", list.get(1).getName());

        assertEquals(2, names.size());
        assertEquals("Hi There", names.get(0));
        assertEquals("Hello World", names.get(1));
        assertEquals(2, p1.getNumSongs());
    }

    @Test
    public void testFindSong() {
        p1.addSong(s1);
        p1.addSong(s2);

        assertEquals(s1, p1.findSong("Hi There"));
        assertEquals(s2, p1.findSong("Hello World"));
    }

    @Test
    public void testFindSongNotFound() {
        p1.addSong(s1);
        p1.addSong(s2);

        assertEquals(null, p1.findSong("Goodbye"));
    }


}