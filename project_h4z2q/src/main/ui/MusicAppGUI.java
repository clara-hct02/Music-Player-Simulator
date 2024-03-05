package ui;

import model.Event;
import ui.tool.*;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

// The main class where all the GUI elements come together
// Inspired by the SimpleDrawingPlayer program
public class MusicAppGUI extends JFrame implements WindowListener {
    private List<Tool> tools;
    private Controller controller;
    private JFrame viewPlaylist;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 700;

    public static void main(String[] args) {
        new MusicAppGUI();
    }

    // EFFECTS: Constructs a Music Player window with the title "Music Player",
    // and initializes the graphics and tools
    //
    public MusicAppGUI() {
        super("Music Player");
        initializeFields();
        initializeGraphics();
        createTools();
        setResizable(false);
        addWindowListener(this);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: Constructs a list to store all the tools and a controller
    //
    private void initializeFields() {
        tools = new ArrayList<>();
        controller = new Controller();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this Music Player will operate, and loads the tools
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a new window that displays all the playlists that have been made
    public void playlistWindow() {
        this.viewPlaylist = new JFrame();
        viewPlaylist.setLayout(new GridLayout(0, 2));
        viewPlaylist.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        viewPlaylist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewPlaylist.setLocationRelativeTo(null);
    }


    // MODIFIES: this
    // EFFECTS: initializes the tools on the default interface on startup
    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        NewPlaylistTool newPlaylistTool = new NewPlaylistTool(this, toolArea, controller);
        tools.add(newPlaylistTool);

        ViewPlaylistsTool viewPlaylistsTool = new ViewPlaylistsTool(this, toolArea, controller);
        tools.add(viewPlaylistsTool);

        SavePlaylistsTool savePlaylistsTool = new SavePlaylistsTool(this, toolArea, controller);
        tools.add(savePlaylistsTool);

        LoadPlaylistsTool loadPlaylistsTool = new LoadPlaylistsTool(this, toolArea, controller);
        tools.add(loadPlaylistsTool);
    }

    // MODIFIES: this
    // EFFECTS: adds the new playlist button to the ViewPlaylist window
    public void addPlaylist(JButton button) {
        viewPlaylist.add(button);
    }

    // MODIFIES: this
    // EFFECTS: sets the viewing all playlists menu to visible
    public void openPlaylistMenu() {
        viewPlaylist.setVisible(true);
    }


    // EFFECTS:
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

