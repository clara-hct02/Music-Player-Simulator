package ui;

import model.Playlist;
import ui.tool.FavouriteSongTool;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Creates a JComponent that displays the list of songs in the current playlist being viewed
public class SongQueue extends JComponent {
    private Playlist playlist;
    private JFrame parent;

    // MODIFIES: this
    // EFFECTS: Creates a song queue that displays all the songs in the playlist as a JList
    public SongQueue(Playlist playlist, JFrame parent) {
        this.playlist = playlist;
        this.parent = parent;
        addQueue();
        addButton();
    }

    // MODIFIES: this, parent
    // EFFECTS: adds the list of songs in the playlist in the form of a JList to the parent frame
    private void addQueue() {
        if (playlist.getNumSongs() == 0) {
            JLabel text = new JLabel("This playlist is empty!");
            this.parent.add(text);
        } else {
            List<String> songNames = playlist.getSongNames();
            JList list = new JList<>(songNames.toArray());
            list.setFont(new Font("Arial",Font.PLAIN, 20));
            list.setAlignmentX(CENTER_ALIGNMENT);
            list.setVisible(true);
            parent.add(list);
        }
    }

    // MODIFIES: parent
    // EFFECTS: creates a button and adds it to the parent class
    private void addButton() {
        FavouriteSongTool likeButton = new FavouriteSongTool(playlist);
        likeButton.setAlignmentX(CENTER_ALIGNMENT);

        likeButton.setVisible(true);
        parent.add(likeButton);
    }
}
