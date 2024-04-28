package ui.tool;

import ui.MusicAppGUI;

import javax.swing.*;
import java.awt.*;

// Hierarchical structure of this class and its inheritors
// inspired by the SimpleDrawingPlayer program
// Creates an abstract tool
public abstract class Tool {
    protected JButton button;
    protected MusicAppGUI musicAppGUI;

    public Tool(MusicAppGUI musicAppGUI, JComponent parent) {
        this.musicAppGUI = musicAppGUI;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(600, 50));
        return button;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
