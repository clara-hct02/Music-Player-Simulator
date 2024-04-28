# My Personal Project: Music Player

## What will the application do?
The application will simulate the behaviour of a music player.

## Who will use it?
Anyone who enjoys music and wants to organize their favourite songs.

## Why is this project of interest to you?
Listening to music is my favourite hobby, and I personally love to organize my songs in playlists based on artist, 
genre, mood etc to make it easier to find the song I want to listen to. I want to design an application that allows users to do the same.

## User Stories
- As a user, I want to create multiple playlists and name them [Done]
- As a user, I want to add multiple songs to playlists [Done]
- As a user, I want to view the songs on my playlist [Done]
- As a user, I want to play the music on the playlist [Done]
- As a user, I want to be able to favourite songs [Done]
- As a user, I want to have the option to save all the playlists to the file [Done]
- As a user, I want to choose to load all the saved playlists when opening the program [Done]

## Instructions for Grader
IMPORTANT NOTE: to refresh any of the lists ie. lists of songs/list of playlists, you must close the current
                window in which they are displayed and click the "view" button again. There are no back buttons,
                but each window stays open so simply close the windows you don't need and go back to where you were.

- how to generate the first of the two required actions that are related to the required user story 
  "add multiple Xs to a Y"
  - First, create a playlist
  - Click "view all playlists" and click the playlist you just made
  - Click "add a song to this playlist" and type in the name of the song you would like to add
  - Click "view all songs" to view the songs you have added to the playlist

- how to generate the second of the two required actions
  - follow all steps above
  - select a song on the list and click "like this song"

- where to find the visual component that was added to your project
  - after creating a playlist and adding songs to it, click "Play the playlist" 
    which will open a new window with an image

- how does the user save the state of the application to file
  - the button the home screen that says "save all playlists to file"
  
- how does the user load the state of the application from file
  - the button the home screen that says "load all playlists from file"

## Phase 4: Task 2
New Playlist Created
Song added
Song added
Song has been liked
Song has been unliked

## Phase 4: Task 3
1. Making Controller a singleton
Since the "hashmap of playlists" that the controller class stores can only be initialized once per run
of the main map, it would be much better to make it a singleton. That way, the program ensures that
the controller can only be initialized once in the entire run of the program. This would also reduce coupling,
as my current method of having half of my tool classes have a Controller field means that if one thing gets changed
in the controller class, the entire program could fall apart.

2. Implementing an observer pattern
The current process of notifying my program that something changed is quite convoluted especially since I have a
class for every tool and button basically. I believe if I implemented an observer pattern to let my main class 
know when a new playlist was created or an existing playlist was edited, it would also make it easier for me to 
create a GUI that allowed the user to view the items and playlist being created in real time.

3. Putting all the tool classes into a main class with sub classes
Currently I'm having to pass a bunch of fields to each tool class, which as you can see on the UML diagram is creating
quite a bit of unnecessary coupling. Even while coding it, it was a bit tricky to keep track of which class
had which fields and what was getting passed to where.