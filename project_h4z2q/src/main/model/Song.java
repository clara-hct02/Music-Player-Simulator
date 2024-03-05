package model;

// Represents a song that has a title and is either favourited or not

import org.json.JSONObject;
import persistence.Writable;

public class Song implements Writable {
    boolean favourite;
    String name;

    // EFFECTS: Constructs a song class with a name and boolean favourite set to false initially
    public Song(String songName) {
        this.name = songName;
        this.favourite = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the favourite field to true if it is initially false, else sets it to false
    public void like() {
        if (!this.favourite) {
            this.favourite = true;
            EventLog.getInstance().logEvent(new Event("Song has been liked"));
        } else {
            this.favourite = false;
            EventLog.getInstance().logEvent(new Event("Song has been unliked"));
        }
    }

    // EFFECTS: returns the name of the song
    public String getName() {
        return name;
    }

    // EFFECTS: returns whether the song has been favourited or not
    public Boolean getLike() {
        return this.favourite;
    }


    // EFFECTS: converts the song into a jsonobject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("liked", this.getLike());
        return json;
    }

}
