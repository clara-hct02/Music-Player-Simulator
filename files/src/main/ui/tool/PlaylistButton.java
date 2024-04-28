package ui.tool;

import ui.Controller;
import ui.EditingPlaylistMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a button for each playlist that's been created
public class PlaylistButton extends JButton {
    private Controller controller;
    private String playlistName;

    // MODIFIES: this
    // EFFECTS: Creates a button for every playlist in the controller
    //          with label playlistName
    public PlaylistButton(String playlistName, Controller controller) {
        super(playlistName);
        this.controller = controller;
        this.playlistName = playlistName;
        addListener();
    }

    // EFFECTS: When the playlist button is clicked, opens a new window that expands the details
    //          of the playlist with the tools to edit the playlist
    public void addListener() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditingPlaylistMenu menu = new EditingPlaylistMenu(playlistName, controller);
            }
        });
    }
}
