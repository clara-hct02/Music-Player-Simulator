package ui.tool;

import model.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a PlayMusic button that opens a new window to "Play the playlist" when it's pressed
public class PlayMusicTool extends JButton {
    private Playlist playlist;
    private JFrame playMusic;
    private ImageIcon vinyl;

    // MODIFIES: this
    // EFFECTS: creates a button that will "play" the currently viewed playlist
    public PlayMusicTool(Playlist playlist) {
        super("Play Music");
        this.playlist = playlist;
        this.setPreferredSize(new Dimension(600, 50));
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: constructs an ActionListener which is added to this object
    protected void addListener() {
        this.addActionListener(new ActionListener() {
            // MODIFIES: playMusic
            // EFFECTS: opens a new window
            @Override
            public void actionPerformed(ActionEvent e) {
                newWindow();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Constructs the new window where the music playing will occur
    private void newWindow() {
        this.playMusic = new JFrame("Now Playing " + playlist.getName());
        playMusic.setLayout(new BoxLayout(playMusic.getContentPane(), BoxLayout.PAGE_AXIS));
        playMusic.setResizable(false);
        playMusic.setMinimumSize(new Dimension(400, 450));
        playMusic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        playMusic.setLocationRelativeTo(null);
        loadImage();
        playMusic.pack();

        playMusic.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads the image and the text that will be displayed in the new window
    private void loadImage() {
        if (playlist.getNumSongs() == 0) {
            JLabel text = new JLabel("This playlist is empty!");
            this.playMusic.add(text);
        } else {
            vinyl = new ImageIcon("vinyl.png");
            JLabel label = new JLabel();
            label.setIcon(vinyl);
            label.setAlignmentX(CENTER_ALIGNMENT);
            this.playMusic.add(label);

            JLabel text = new JLabel("Now playing: " + playlist.getListOfSongs().get(0).getName());
            text.setAlignmentX(CENTER_ALIGNMENT);
            text.setFont(new Font("Sans Serif", Font.PLAIN, 20));
            this.playMusic.add(text);
        }
    }
}
