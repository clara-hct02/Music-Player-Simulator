package ui.tool;

import model.Playlist;
import model.Song;
import ui.MusicAppGUI;
import ui.exception.InvalidSongNameException;
import ui.exception.SongNotFoundException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a favourite song tool that allows the user to add a song to a favourites list
public class FavouriteSongTool extends JButton {
    private Playlist playlist;
    private MusicAppGUI musicApp;

    // MODIFIES: this
    // EFFECTS: constructs a favourite song tool
    public FavouriteSongTool(Playlist playlist) {
        super("Favourite a Song");
        this.playlist = playlist;
        addListener();
    }

    protected void addListener() {
        this.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: creates a listener that will detect when the like button is pressed
            //          when the button is pressed, prompts the user for the name of the song
            //          they would like to favourite
            //          if an error occurs, catch the exception and let the user know what
            //          happened
            @Override
            public void actionPerformed(ActionEvent e) {

                String songName = JOptionPane.showInputDialog("Enter the name of the song you would like to favourite");

                try {
                    likeSong(songName);
                } catch (InvalidSongNameException ex) {
                    JOptionPane.showMessageDialog(musicApp, "Please enter a valid song name");
                } catch (SongNotFoundException snf) {
                    JOptionPane.showMessageDialog(musicApp, "Could not find song on this playlist");
                }
            }
        });
    }

    // MODIFIES: song
    // EFFECTS: if the name of the song is null, empty, or is not found in the current playlist,
    //          throw the corresponding exception
    //          else, toggle the "liked" parameter of the song and let the user know
    //          that they have modified it
    public void likeSong(String songName) throws InvalidSongNameException, SongNotFoundException {
        if (songName == null || songName.equals("")) {
            throw new InvalidSongNameException();
        }

        Song song = playlist.findSong(songName);
        if (song == null) {
            throw new SongNotFoundException();
        }

        song.like();
        if (song.getLike()) {
            JOptionPane.showMessageDialog(musicApp, "You have liked: " + song.getName());
        } else {
            JOptionPane.showMessageDialog(musicApp, "You have unliked: " + song.getName());
        }
    }
}
