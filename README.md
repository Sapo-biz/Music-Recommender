# Music Recommender

A simple Java console application that recommends music based on your recent listening habits, all complete with a "Play Song" feature that takes you to a YouTube link.

## Features
- Input your last 3 played songs (title, artist, genre)
- Input your favorite song of the week (title, artist, genre)
- Get a personalized recommendation based on your favorite artists and genres

## How It Works
The application analyzes the artists and genres you provide, then suggests either more tracks from your favorite artist, new artists in your favorite genre, or encourages you to explore similar music.

## Usage
1. Compile the Java file:
   ```sh
   javac MusicRecommender.java
   ```
2. Run the application:
   ```sh
   java MusicRecommender
   ```
3. Follow the prompts to enter your recent songs and favorite song of the week.
4. Receive your personalized music recommendation!

## Example
```
Welcome to Music Recommender!
Please enter details for your last 3 played songs.
Song #1 title: Shape of You
Artist for 'Shape of You': Ed Sheeran
Genre for 'Shape of You': Pop
...
Now, tell us your favorite song of the week.
Favorite song title: Blinding Lights
Artist for 'Blinding Lights': The Weeknd
Genre for 'Blinding Lights': Pop

--- Recommendation ---
You enjoy the 'Pop' genre. Explore new artists in this genre!
Thank you for using Music Recommender!
```

## Requirements
- Java 8 or higher

## License
none
