package persistence;

import model.Playlist;
import model.Song;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code influenced by JsonSerializationDemo at
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Playlist playlist = new Playlist("My playlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyApp() {
        JSONArray playlists = new JSONArray();
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyApp.json");
        JsonReader reader = new JsonReader("./data/testWriterEmptyApp.json");

        try {
            writer.open();
            writer.write(playlists);
            writer.close();
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }

        try {
            List<Playlist> readPlaylists = reader.read();
            assertEquals(0, readPlaylists.size());
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testWriterGeneralApp() {
        JSONArray playlists = new JSONArray();
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralApp.json");
        JsonReader reader = new JsonReader("./data/testWriterGeneralApp.json");

        Song s1 = new Song("Over the Rainbow");
        Song s2 = new Song("Speechless");
        Song s3 = new Song("Hello");
        Playlist playlist = new Playlist("My playlist");
        Playlist playlist1 = new Playlist("Playlist1");

        s2.like();
        playlist.addSong(s1);
        playlist.addSong(s2);
        playlist1.addSong(s3);

        playlists.put(playlist.toJson());
        playlists.put(playlist1.toJson());

        try {
            writer.open();
            writer.write(playlists);
            writer.close();

        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }

        try {
            List<Playlist> readPlaylist = reader.read();
            assertEquals(2, readPlaylist.size());
            List<Song> songs = readPlaylist.get(0).getListOfSongs();
            List<Song> songs1 = readPlaylist.get(1).getListOfSongs();

            assertEquals("My playlist", readPlaylist.get(0).getName());
            assertEquals(2, songs.size());
            checkSong("Over the Rainbow", false, songs.get(0));
            checkSong("Speechless", true, songs.get(1));

            assertEquals("Playlist1", readPlaylist.get(1).getName());
            assertEquals(1, songs1.size());
            checkSong("Hello", false, songs1.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}