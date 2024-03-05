package persistence;

import model.Playlist;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Code influenced by JsonSerializationDemo at
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads each playlist from file and returns them in a list
    // throws IOException if an error occurs reading data from file
    public List<Playlist> read() throws IOException {
        List<Playlist> playlists = new ArrayList<>();
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        List<JSONObject> objects = extractObjects(jsonArray);

        for (JSONObject object : objects) {
            playlists.add(parsePlaylist(object));
        }

        return playlists;
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: extracts each JSONObject from the JSONArray and puts it into a list
    //          of JSONObjects
    private List<JSONObject> extractObjects(JSONArray jsonArray) {
        List<JSONObject> objects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject playlist = jsonArray.getJSONObject(i);
            objects.add(playlist);
        }
        return objects;
    }

    // EFFECTS: parses playlist from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Playlist p = new Playlist(name);
        addSongs(p, jsonObject);
        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses songs from JSON object and adds them to the playlist
    private void addSongs(Playlist p, JSONObject jsonobject) {
        JSONArray jsonArray = jsonobject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(p, nextSong);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses song from JSON object and adds it to the playlist
    private void addSong(Playlist p, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean liked = jsonObject.getBoolean("liked");
        Song song = new Song(name);
        if (liked) {
            song.like();
        }
        p.addSong(song);
    }
}
