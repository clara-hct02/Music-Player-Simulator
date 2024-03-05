package ui.tool;

import model.Playlist;
import persistence.JsonReader;
import ui.Controller;
import ui.MusicAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

// Creates a button that allows the user to save all the playlists to the file when clicked
public class LoadPlaylistsTool extends Tool {
    private JsonReader jsonReader;
    private Controller controller;
    private List<Playlist> playlists;
    private static final String JSON_MUSIC = "./data/playlist.json";

    // MODIFIES: this
    // EFFECTS: Creates a button that loads the playlists saved on the file
    public LoadPlaylistsTool(MusicAppGUI musicApp, JComponent parent, Controller controller) {
        super(musicApp, parent);
        this.controller = controller;
        initializeField();
    }

    // MODIFIES: this
    // EFFECTS: initializes the necessary fields for this class
    private void initializeField() {
        jsonReader = new JsonReader(JSON_MUSIC);
    }

    // MODIFIES: this
    // EFFECTS: creates a new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs an ActionListener which is added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new LoadPlaylistsTool.LoadPlaylistsToolClickHandler());
    }


    private class LoadPlaylistsToolClickHandler implements ActionListener {

        // EFFECTS: writes the hashmap containing all the playlists to the file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                playlists = jsonReader.read();
                for (Playlist playlist : playlists) {
                    controller.addPlaylist(playlist);
                    System.out.println("Loaded " + playlist.getName() + " from " + JSON_MUSIC);
                }
            } catch (IOException ioe) {
                System.out.println("Unable to read from file: " + JSON_MUSIC);
            }
        }
    }

    //EFFECTS: Returns the string for the label.
    private String getLabel() {
        return "Load Playlists";
    }
}
