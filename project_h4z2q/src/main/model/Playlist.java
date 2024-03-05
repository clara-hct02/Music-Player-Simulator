package model;

// Represents a playlist with a name and a list of songs on the playlist

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Writable {
    private String name;
    private List<Song> listOfSongs;

    // EFFECTS: Constructs a new playlist with a name and an empty list of songs
    public Playlist(String playlistName) {
        this.name = playlistName;
        this.listOfSongs = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("New Playlist Created"));
    }

    // MODIFIES: this
    // EFFECTS: Adds a song to the playlist
    public void addSong(Song song) {
        this.listOfSongs.add(song);
        EventLog.getInstance().logEvent(new Event("Song added"));
    }

    // EFFECTS: Returns the name of the playlist
    public String getName() {
        return this.name;
    }

    // EFFECTS: Returns the list of songs of a playlist
    public List<Song> getListOfSongs() {
        return this.listOfSongs;
    }

    public int getNumSongs() {
        return listOfSongs.size();
    }

    // EFFECTS: finds and returns the song on the playlist given the name
    public Song findSong(String songName) {
        for (Song nextSong : listOfSongs) {
            if (songName.equals(nextSong.getName())) {
                return nextSong;
            }
        }
        return null;
    }

    // EFFECTS: converts the playlist into a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());
        return json;
    }

    // EFFECTS: returns the names of the songs on the playlist as strings
    public List<String> getSongNames() {
        List<String> list = new ArrayList<>();
        for (Song song : this.listOfSongs) {
            list.add(song.getName());
        }
        return list;
    }

    // EFFECTS: returns the songs in this playlist as a JSON array
    public JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : listOfSongs) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}

