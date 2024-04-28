package ui;

import model.Playlist;
import ui.tool.AddSongTool;
import ui.tool.PlayMusicTool;
import ui.tool.ViewSongsTool;

import javax.swing.*;
import java.awt.*;

// Creates a new window where the user can access all the tools needed to edit the current playlist
public class EditingPlaylistMenu extends JFrame {
    private Controller controller;
    private Playlist playlist;
    private String playlistName;

    // MODIFIES: this
    // EFFECTS: creates the playlist editing window
    public EditingPlaylistMenu(String playlistName, Controller controller) {
        super("Viewing " + playlistName);
        this.playlistName = playlistName;
        this.controller = controller;
        initializeFields();

        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setMinimumSize(new Dimension(600, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel playlistArea = new JPanel();
        playlistArea.setLayout(new GridLayout(0,1));
        playlistArea.setSize(new Dimension(0, 0));
        add(playlistArea, BorderLayout.NORTH);
        this.add(playlistArea);

        editPlaylistTools();
        pack();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates the tools needed for the edit playlist interface
    private void editPlaylistTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        PlayMusicTool playMusicTool = new PlayMusicTool(playlist);
        toolArea.add(playMusicTool);

        ViewSongsTool viewSongsTool = new ViewSongsTool(playlist);
        toolArea.add(viewSongsTool);

        AddSongTool addSongTool = new AddSongTool(controller, playlist);
        toolArea.add(addSongTool);
    }

    // MODIFIES: this
    // EFFECTS: sets the current playlist to the playlist with the name playlistName
    private void initializeFields() {
        this.playlist = controller.findPlaylist(playlistName);
    }

}
