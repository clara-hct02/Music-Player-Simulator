package ui.tool;

import model.Playlist;
import ui.Controller;
import ui.MusicAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

// Creates a ViewPlaylistsTool that opens a new window that has all the playlists displayed as buttons
public class ViewPlaylistsTool extends Tool {
    private Controller controller;

    public ViewPlaylistsTool(MusicAppGUI musicAppGUI, JComponent parent, Controller controller) {
        super(musicAppGUI, parent);
        this.controller = controller;
    }

    // EFFECT: Creates a button with the label "View All Playlists"
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS: constructs an ActionListener which is added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new ViewPlaylistsTool.ViewPlaylistToolClickHandler());
    }

    private class ViewPlaylistToolClickHandler implements ActionListener {

        // MODIFIES: musicAppGUI
        // EFFECTS: displays all the playlists that have been created when View All Playlists is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            musicAppGUI.playlistWindow();
            for (HashMap.Entry<String, Playlist> entry : controller.getMap().entrySet()) {
                PlaylistButton button = new PlaylistButton(entry.getKey(), controller);
                musicAppGUI.addPlaylist(button);
            }
            musicAppGUI.openPlaylistMenu();
        }
    }

    //EFFECTS: Returns the string for the label.
    private String getLabel() {
        return "View All Playlists";
    }

}
