package ui.tool;

import model.Playlist;
import ui.SongQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a button that allows the user to view all the songs on the playlist in a new window
public class ViewSongsTool extends JButton {
    private Playlist playlist;
    private JFrame viewingMenu;

    public ViewSongsTool(Playlist playlist) {
        super("View Songs on the Playlist");
        this.setPreferredSize(new Dimension(600, 50));
        this.playlist = playlist;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: constructs an ActionListener which is added to this object's JButton
    protected void addListener() {
        this.addActionListener(new ActionListener() {
            // EFFECTS: Opens a new window to view the list of all the songs on the playlist
            @Override
            public void actionPerformed(ActionEvent e) {
                newWindow();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Constructs the new window where the user will be able to view all the songs on the playlist
    private void newWindow() {
        this.viewingMenu = new JFrame("Now Viewing " + playlist.getName());
        viewingMenu.setLayout(new BoxLayout(viewingMenu.getContentPane(), BoxLayout.PAGE_AXIS));
        viewingMenu.setResizable(false);
        viewingMenu.setMinimumSize(new Dimension(400, 450));
        viewingMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewingMenu.setLocationRelativeTo(null);
        viewingMenu.pack();

        SongQueue queue = new SongQueue(playlist, viewingMenu);
        viewingMenu.add(queue);

        viewingMenu.setVisible(true);
    }

}
