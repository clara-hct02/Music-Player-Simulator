package ui.tool;

import ui.exception.InvalidSongNameException;
import model.Playlist;
import model.Song;
import ui.Controller;
import ui.MusicAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a button that appears on the "Edit Playlist Menu" and is used to add songs
// to the current playlist
public class AddSongTool extends JButton {
    private Controller controller;
    private Playlist playlist;
    private MusicAppGUI musicApp;

    // MODIFIES: this
    // EFFECTS: Constructs a button with dimensions 600 x 50 and the label "Add Song to this playlist"
    public AddSongTool(Controller controller, Playlist playlist) {
        super("Add Song to this Playlist");
        this.controller = controller;
        this.playlist = playlist;
        this.setPreferredSize(new Dimension(600, 50));
        addListener();
    }

    protected void addListener() {
        this.addActionListener(new ActionListener() {
            // EFFECTS: when the button is clicked, prompts the user for the name of the song they want to add
            //          if the song name is invalid, throw an InvalidSongNameException
            @Override
            public void actionPerformed(ActionEvent e) {
                String songName = JOptionPane.showInputDialog("Enter the name of the song you would like to add: ");

                try {
                    addSong(songName);
                } catch (InvalidSongNameException ex) {
                    JOptionPane.showMessageDialog(musicApp, "Please enter a valid song name");
                }
            }
        });
    }

    // MODIFIES: playlist, controller
    // EFFECTS: If the user input is not null or the empty string, create a new song object and adds
    //          it to the current playlist
    //          adds the playlist with the changes into the controller
    private void addSong(String songName) throws InvalidSongNameException {
        if (songName == null || songName.equals("")) {
            throw new InvalidSongNameException();
        }

        Song song = new Song(songName);
        playlist.addSong(song);
        controller.addPlaylist(playlist);

        JOptionPane.showMessageDialog(musicApp, "You have added: " + songName + " to " + playlist.getName());

    }
}
