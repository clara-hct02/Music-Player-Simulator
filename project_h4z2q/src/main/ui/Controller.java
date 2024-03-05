package ui;

import model.Playlist;

import java.util.HashMap;

// A class that stores the hashmap that contains all the playlists with the names
// of the playlists as the keys, and the methods that are used to modify it
public class Controller {
    private HashMap<String, Playlist> listOfPlaylist;

    // MODIFIES: this
    // EFFECTS: creates a controller that has an empty hashMap that stores
    //          the names of the playlists and the playlists
    public Controller() {
        listOfPlaylist = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the playlist to the hashmap where the key is the name of the playlist
    public void addPlaylist(Playlist playlist) {
        listOfPlaylist.put(playlist.getName(), playlist);
    }

    // EFFECTS: returns the playlist with key name
    public Playlist findPlaylist(String name) {
        return listOfPlaylist.get(name);
    }

    // EFFECTS: returns true if a playlist with the same name already exists
    public Boolean containsPlaylist(String name) {
        return listOfPlaylist.containsKey(name);
    }

    // EFFECTS: returns the hashmap
    public HashMap<String, Playlist> getMap() {
        return this.listOfPlaylist;
    }
}
