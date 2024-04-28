package ui;

import model.Playlist;
import model.Song;
import org.json.JSONArray;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tool.Tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// Music player application
public class MusicApp {
    private Scanner input;
    private HashMap<String, Playlist> listOfPlaylist = new HashMap<>();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_MUSIC = "./data/playlist.json";
    private List<Playlist> playlists;

    // EFFECTS: runs the music player application
    public MusicApp() {
        runMusic();
    }

    // MODIFIES: this
    // EFFECTS: processes user input at the main app interface
    private void runMusic() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMenu();
            String command = this.input.nextLine();

            if (command.equals("q")) {
                keepGoing = false;

            } else if (command.equals("n")) {
                createNewPlaylist();

            } else if (command.equals("v")) {
                viewPlaylist();

            } else if (command.equals("s")) {
                savePlaylists(listOfPlaylist);

            } else if (command.equals("l")) {
                loadPlaylist();

            } else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("Hope you enjoyed your listening session!");

    }

    // MODIFIES: this
    // EFFECTS: initializes the scanner
    private void init() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_MUSIC);
        jsonReader = new JsonReader(JSON_MUSIC);
    }

    // EFFECTS: displays the app interface to the user
    private void displayMenu() {
        System.out.println("Hello, welcome to your new listening session.");
        System.out.println("What would you like to do today?:");
        System.out.println("n -> create a new playlist");
        System.out.println("v -> view an existing playlist");
        System.out.println("s -> save all playlists to file");
        System.out.println(("l -> load all playlists from file"));
        System.out.println("q -> quit the application");
    }

    // MODIFIES: listOfPlaylist
    // EFFECTS: prompts the user for name of the new playlist and creates a new playlist with that name
    private void createNewPlaylist() {
        System.out.println("Please enter the name of the new playlist: ");
        String playlistName = this.input.nextLine();
        listOfPlaylist.put(playlistName, new Playlist(playlistName));
    }

    // EFFECTS: Prompts the user for the name of an existing playlist and prints the playlist onto the console.
    //          Then asks the user what they want to do with the playlist.
    //          If the playlist doesn't exist, let the user know.
    private void viewPlaylist() {
        System.out.println("Please enter the name of the playlist you would like to view: ");
        String playlistName = this.input.nextLine();
        Playlist currentPlaylist = listOfPlaylist.get(playlistName);
        if (currentPlaylist == null) {
            System.out.println("This playlist does not exist!");
        } else {
            printPlaylist(currentPlaylist);
            processCommand(currentPlaylist);
        }
    }

    // EFFECTS: Prints the playlist onto the console. If the playlist is empty, lets the user know that.
    private void printPlaylist(Playlist playlist) {
        List<Song> list = playlist.getListOfSongs();
        if (list.isEmpty()) {
            System.out.println("This playlist is empty!");
        } else {
            for (Song nextSong : list) {
                System.out.println(nextSong.getName());
            }
        }
    }

    // MODIFIES: this, playlist
    // EFFECTS: prompts the user for the name of a song to add to the current playlist
    private void addSong(Playlist playlist) {
        System.out.println("Please enter the name of the song you would like to add: ");
        String songName = this.input.nextLine();
        playlist.addSong(new Song(songName));
    }

    // EFFECTS: Prompts the user for a command while they are viewing a playlist
    private void displayModifyMenu() {
        System.out.println("What would you like to do?");
        System.out.println("n -> add a new song");
        System.out.println("p -> play the playlist");
        System.out.println("l -> favourite a song");
        System.out.println("b -> return to the main interface");
    }

    // MODIFIES: this
    // EFFECTS: Processes the user's command
    private void processCommand(Playlist currentPlaylist) {
        displayModifyMenu();
        String command = this.input.nextLine();
        List<Song> list = currentPlaylist.getListOfSongs();

        if (command.equals("b")) {
            runMusic();

        } else if (command.equals("n")) {
            addSong(currentPlaylist);

        } else if (command.equals("p")) {
            if (list.isEmpty()) {
                System.out.println("You cannot play an empty playlist!");
            } else {
                playMusic(list);
            }
        } else if (command.equals("l")) {
            likeSong(currentPlaylist);

        } else {
            System.out.println("Invalid input.");
        }
    }

    // MODIFIES: currentPlaylist
    // EFFECTS: prompts the user for a song they want to like
    public void likeSong(Playlist currentPlaylist) {
        System.out.println("Enter the name of the song you want to  like: ");
        String songName = input.nextLine();
        Song currentSong = currentPlaylist.findSong(songName);

        if (currentSong == null) {
            System.out.println("This song cannot be found!");
        } else {
            currentSong.like();
            if (currentSong.getLike()) {
                System.out.println("You have liked " + currentSong.getName());
            } else {
                System.out.println("You have unliked " + currentSong.getName());
            }
        }
    }

    // EFFECTS: Displays the name of the song on the console
    private void playMusic(List<Song> playlist) {
        System.out.println("Now listening to: " + playlist.get(0).getName());
    }

    // EFFECTS: saves all the playlists to file
    private void savePlaylists(HashMap<String, Playlist> map) {
        JSONArray playlists = new JSONArray();
        for (HashMap.Entry<String, Playlist> entry : map.entrySet()) {
            playlists.put(entry.getValue().toJson());
            System.out.println("Saved " + entry.getValue().getName() + " to " + JSON_MUSIC);
        }

        try {
            jsonWriter.open();
            jsonWriter.write(playlists);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_MUSIC);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads all the playlists from file
    private void loadPlaylist() {
        try {
            playlists = jsonReader.read();
            for (Playlist playlist : playlists) {
                listOfPlaylist.put(playlist.getName(), playlist);
                System.out.println("Loaded " + playlist.getName() + " from " + JSON_MUSIC);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_MUSIC);
        }
    }

}
