package ui.tool;

import ui.exception.InvalidPlaylistNameException;
import model.Playlist;
import ui.Controller;
import ui.MusicAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a NewPlaylistTool that handles the creation of new playlist objects
public class NewPlaylistTool extends Tool {
    private Controller controller;
    private MusicAppGUI musicApp;

    // MODIFIES: this
    // EFFECTS: creates a NewPlaylistTool that has a musicApp and a controller
    public NewPlaylistTool(MusicAppGUI musicApp, JComponent parent, Controller controller) {
        super(musicApp, parent);
        this.musicApp = musicApp;
        this.controller = controller;
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
        button.addActionListener(new NewPlaylistToolClickHandler());
    }

    private class NewPlaylistToolClickHandler implements ActionListener {
        // EFFECTS: opens an input dialog in a new window and prompts the user to enter the name of
        //          the playlist they would like to create
        //          if the name of the playlist is invalid, thrown an InvalidPlaylistNameException
        @Override
        public void actionPerformed(ActionEvent e) {
            String playlistName = JOptionPane.showInputDialog("Enter the name of the playlist: ");

            try {
                createPlaylist(playlistName);
            } catch (InvalidPlaylistNameException ex) {
                JOptionPane.showMessageDialog(musicApp, "Please enter a valid playlist name");
            }
        }
    }

    // MODIFIES: controller
    // EFFECTS: If there is not already a playlist called playlistName, creates a new playlist called playlistName
    //          and adds an entry to the controller
    private void createPlaylist(String playlistName) throws InvalidPlaylistNameException {
        if (playlistName == null || playlistName.equals("")) {
            throw new InvalidPlaylistNameException();
        }
        if (checkPlaylist(playlistName)) {
            JOptionPane.showMessageDialog(musicApp, "You already have a playlist called " + playlistName);
        } else {
            Playlist playlist = new Playlist(playlistName);
            controller.addPlaylist(playlist);
            JOptionPane.showMessageDialog(musicApp, "You have created playlist: " + playlistName);
        }
    }

    //EFFECTS: Returns the string for the label.
    //
    public String getLabel() {
        return "New Playlist";
    }


    // EFFECTS: Returns true if there is already a playlist called "name"
    //
    private Boolean checkPlaylist(String name) {
        return controller.containsPlaylist(name);
    }

}
