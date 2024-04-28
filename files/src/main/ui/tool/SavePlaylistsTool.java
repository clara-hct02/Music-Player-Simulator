package ui.tool;

import model.Playlist;
import org.json.JSONArray;
import persistence.JsonWriter;
import ui.Controller;
import ui.MusicAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;

// Creates a SavePlaylistsTool that saves all the playlists to file when pressed
public class SavePlaylistsTool extends Tool {
    private Controller controller;
    private JsonWriter jsonWriter;
    private static final String JSON_MUSIC = "./data/playlist.json";

    // MODIFIES: this
    // EFFECTS: creates a SavePlaylistsTool that will save all the playlist to file
    public SavePlaylistsTool(MusicAppGUI musicApp, JComponent parent, Controller controller) {
        super(musicApp, parent);
        initializeField();
        this.controller = controller;
    }

    // MODIFIES: this
    // EFFECTS: initializes the necessary fields for this class
    private void initializeField() {
        jsonWriter = new JsonWriter(JSON_MUSIC);
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
        button.addActionListener(new SavePlaylistsTool.SavePlaylistsToolClickHandler());
    }


    private class SavePlaylistsToolClickHandler implements ActionListener {

        // EFFECTS: saves all current playlists to file
        @Override
        public void actionPerformed(ActionEvent e) {
            JSONArray playlists = new JSONArray();
            for (HashMap.Entry<String, Playlist> entry : controller.getMap().entrySet()) {
                playlists.put(entry.getValue().toJson());
                System.out.println("Saved " + entry.getValue().getName() + " to " + JSON_MUSIC);
            }

            try {
                jsonWriter.open();
                jsonWriter.write(playlists);
                jsonWriter.close();
            } catch (FileNotFoundException fnf) {
                System.out.println("Unable to write to file: " + JSON_MUSIC);
            }
        }
    }

    //EFFECTS: Returns the string for the label.
    private String getLabel() {
        return "Save All Playlists";
    }
}
