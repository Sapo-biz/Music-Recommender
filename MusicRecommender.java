// Jason He
// 7/20/25
// MusicRecommender.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MusicRecommender extends JFrame {
    private JTextField[] songFields = new JTextField[4];
    private JTextField[] artistFields = new JTextField[4];
    private JButton recommendButton;
    private JButton playButton;
    private JTextArea resultArea;
    private String lastRecommendedSong = null;
    private String lastRecommendedArtist = null;

    // Example database: 100+ artists with several songs each
    private static final java.util.Map<String, java.util.List<String>> artistToSongs = new java.util.HashMap<>();
    static {
        artistToSongs.put("Ed Sheeran", Arrays.asList("Shape of You", "Perfect", "Photograph", "Thinking Out Loud", "Castle on the Hill"));
        artistToSongs.put("Drake", Arrays.asList("God's Plan", "Hotline Bling", "In My Feelings", "One Dance", "Nonstop"));
        artistToSongs.put("Taylor Swift", Arrays.asList("Love Story", "Blank Space", "Shake It Off", "You Belong With Me", "Cardigan"));
        artistToSongs.put("The Weeknd", Arrays.asList("Blinding Lights", "Starboy", "Can't Feel My Face", "Save Your Tears", "The Hills"));
        artistToSongs.put("Adele", Arrays.asList("Hello", "Someone Like You", "Rolling in the Deep", "Set Fire to the Rain", "Skyfall"));
        artistToSongs.put("Billie Eilish", Arrays.asList("bad guy", "when the party's over", "bury a friend", "everything i wanted", "ocean eyes"));
        artistToSongs.put("Beyoncé", Arrays.asList("Halo", "Single Ladies", "Crazy in Love", "Irreplaceable", "Formation"));
        artistToSongs.put("Bruno Mars", Arrays.asList("Uptown Funk", "Just the Way You Are", "That's What I Like", "24K Magic", "Grenade"));
        artistToSongs.put("Kanye West", Arrays.asList("Stronger", "Gold Digger", "Heartless", "Power", "Flashing Lights"));
        artistToSongs.put("Katy Perry", Arrays.asList("Firework", "Roar", "Dark Horse", "Teenage Dream", "California Gurls"));
        artistToSongs.put("Rihanna", Arrays.asList("Umbrella", "Diamonds", "We Found Love", "Work", "Only Girl (In the World)"));
        artistToSongs.put("Justin Bieber", Arrays.asList("Sorry", "Love Yourself", "What Do You Mean?", "Baby", "Peaches"));
        artistToSongs.put("Ariana Grande", Arrays.asList("7 rings", "thank u, next", "Into You", "No Tears Left To Cry", "Positions"));
        artistToSongs.put("Post Malone", Arrays.asList("Circles", "Rockstar", "Sunflower", "Congratulations", "Wow."));
        artistToSongs.put("Lady Gaga", Arrays.asList("Poker Face", "Bad Romance", "Shallow", "Just Dance", "Born This Way"));
        artistToSongs.put("Dua Lipa", Arrays.asList("Don't Start Now", "Levitating", "New Rules", "IDGAF", "Physical"));
        artistToSongs.put("Shawn Mendes", Arrays.asList("Stitches", "Treat You Better", "There's Nothing Holdin' Me Back", "In My Blood", "Señorita"));
        artistToSongs.put("Imagine Dragons", Arrays.asList("Believer", "Radioactive", "Demons", "Thunder", "Whatever It Takes"));
        artistToSongs.put("Maroon 5", Arrays.asList("Sugar", "Girls Like You", "Memories", "Moves Like Jagger", "Payphone"));
        artistToSongs.put("Coldplay", Arrays.asList("Viva La Vida", "Fix You", "Yellow", "The Scientist", "Paradise"));
        artistToSongs.put("Sam Smith", Arrays.asList("Stay With Me", "Too Good at Goodbyes", "I'm Not the Only One", "Lay Me Down", "How Do You Sleep?"));
        artistToSongs.put("Selena Gomez", Arrays.asList("Lose You to Love Me", "Come & Get It", "Bad Liar", "Hands to Myself", "Wolves"));
        artistToSongs.put("Halsey", Arrays.asList("Without Me", "Closer", "Bad at Love", "Graveyard", "Eastside"));
        artistToSongs.put("Sia", Arrays.asList("Chandelier", "Cheap Thrills", "Elastic Heart", "The Greatest", "Unstoppable"));
        artistToSongs.put("Harry Styles", Arrays.asList("Sign of the Times", "Watermelon Sugar", "Adore You", "Falling", "Golden"));
        artistToSongs.put("Bill Withers", Arrays.asList("Ain't No Sunshine", "Lean on Me", "Lovely Day", "Just the Two of Us", "Use Me"));
        artistToSongs.put("Queen", Arrays.asList("Bohemian Rhapsody", "Don't Stop Me Now", "We Will Rock You", "Somebody to Love", "Another One Bites the Dust"));
        artistToSongs.put("Elton John", Arrays.asList("Rocket Man", "Tiny Dancer", "Your Song", "Bennie and the Jets", "Candle in the Wind"));
        artistToSongs.put("Fleetwood Mac", Arrays.asList("Go Your Own Way", "Dreams", "The Chain", "Landslide", "Rhiannon"));
        artistToSongs.put("The Beatles", Arrays.asList("Hey Jude", "Let It Be", "Come Together", "Yesterday", "Here Comes the Sun"));
        artistToSongs.put("Michael Jackson", Arrays.asList("Billie Jean", "Thriller", "Beat It", "Smooth Criminal", "Man in the Mirror"));
        artistToSongs.put("Madonna", Arrays.asList("Like a Virgin", "Vogue", "Material Girl", "Hung Up", "La Isla Bonita"));
        artistToSongs.put("Prince", Arrays.asList("Purple Rain", "When Doves Cry", "Kiss", "1999", "Little Red Corvette"));
        artistToSongs.put("Whitney Houston", Arrays.asList("I Will Always Love You", "I Wanna Dance with Somebody", "Greatest Love of All", "How Will I Know", "I'm Every Woman"));
        artistToSongs.put("Celine Dion", Arrays.asList("My Heart Will Go On", "Because You Loved Me", "The Power of Love", "It's All Coming Back to Me Now", "I'm Alive"));
        artistToSongs.put("Bon Jovi", Arrays.asList("Livin' on a Prayer", "It's My Life", "You Give Love a Bad Name", "Always", "Bed of Roses"));
        artistToSongs.put("Alicia Keys", Arrays.asList("If I Ain't Got You", "No One", "Girl on Fire", "Fallin'", "Empire State of Mind"));
        artistToSongs.put("Usher", Arrays.asList("Yeah!", "DJ Got Us Fallin' in Love", "Burn", "U Got It Bad", "Love in This Club"));
        artistToSongs.put("Eminem", Arrays.asList("Lose Yourself", "The Real Slim Shady", "Love the Way You Lie", "Without Me", "Stan"));
        artistToSongs.put("Linkin Park", Arrays.asList("In the End", "Numb", "Crawling", "Somewhere I Belong", "One Step Closer"));
        artistToSongs.put("Green Day", Arrays.asList("Boulevard of Broken Dreams", "American Idiot", "Wake Me Up When September Ends", "21 Guns", "Good Riddance"));
        artistToSongs.put("Red Hot Chili Peppers", Arrays.asList("Californication", "Under the Bridge", "Scar Tissue", "Can't Stop", "Otherside"));
        artistToSongs.put("Foo Fighters", Arrays.asList("Everlong", "The Pretender", "Best of You", "Learn to Fly", "My Hero"));
        artistToSongs.put("Nirvana", Arrays.asList("Smells Like Teen Spirit", "Come as You Are", "Lithium", "Heart-Shaped Box", "In Bloom"));
        artistToSongs.put("Oasis", Arrays.asList("Wonderwall", "Don't Look Back in Anger", "Champagne Supernova", "Live Forever", "Stop Crying Your Heart Out"));
        artistToSongs.put("U2", Arrays.asList("With or Without You", "One", "Beautiful Day", "I Still Haven't Found What I'm Looking For", "Sunday Bloody Sunday"));
        artistToSongs.put("Bruce Springsteen", Arrays.asList("Born to Run", "Dancing in the Dark", "Streets of Philadelphia", "The River", "Glory Days"));
        artistToSongs.put("David Bowie", Arrays.asList("Heroes", "Space Oddity", "Let's Dance", "Life on Mars?", "Starman"));
        artistToSongs.put("The Rolling Stones", Arrays.asList("Paint It Black", "Angie", "Start Me Up", "Gimme Shelter", "(I Can't Get No) Satisfaction"));
        artistToSongs.put("Bob Dylan", Arrays.asList("Like a Rolling Stone", "Blowin' in the Wind", "Knockin' on Heaven's Door", "The Times They Are a-Changin'", "Hurricane"));
        artistToSongs.put("Queen Latifah", Arrays.asList("U.N.I.T.Y.", "Just Another Day", "Ladies First", "Come Into My House", "Black Hand Side"));
        artistToSongs.put("Missy Elliott", Arrays.asList("Work It", "Get Ur Freak On", "Lose Control", "Gossip Folks", "The Rain (Supa Dupa Fly)"));
        artistToSongs.put("Outkast", Arrays.asList("Hey Ya!", "Ms. Jackson", "Roses", "So Fresh, So Clean", "B.O.B"));
        artistToSongs.put("Jay-Z", Arrays.asList("Empire State of Mind", "99 Problems", "Big Pimpin'", "Hard Knock Life", "Izzo (H.O.V.A.)"));
        artistToSongs.put("Kendrick Lamar", Arrays.asList("HUMBLE.", "Alright", "DNA.", "Swimming Pools (Drank)", "King Kunta"));
        artistToSongs.put("Travis Scott", Arrays.asList("SICKO MODE", "goosebumps", "STARGAZING", "HIGHEST IN THE ROOM", "BUTTERFLY EFFECT"));
        artistToSongs.put("Cardi B", Arrays.asList("Bodak Yellow", "I Like It", "WAP", "Up", "Money"));
        artistToSongs.put("Megan Thee Stallion", Arrays.asList("Savage", "Hot Girl Summer", "Body", "Cash Shit", "Captain Hook"));
        artistToSongs.put("Doja Cat", Arrays.asList("Say So", "Woman", "Kiss Me More", "Streets", "Juicy"));
        artistToSongs.put("Lil Nas X", Arrays.asList("Old Town Road", "MONTERO (Call Me By Your Name)", "Panini", "INDUSTRY BABY", "SUN GOES DOWN"));
        artistToSongs.put("SZA", Arrays.asList("Good Days", "The Weekend", "Love Galore", "Broken Clocks", "Drew Barrymore"));
        artistToSongs.put("Frank Ocean", Arrays.asList("Thinkin Bout You", "Nights", "Pink + White", "Self Control", "Ivy"));
        artistToSongs.put("Childish Gambino", Arrays.asList("Redbone", "This Is America", "3005", "Sober", "Feels Like Summer"));
        artistToSongs.put("Lizzo", Arrays.asList("Truth Hurts", "Good as Hell", "Juice", "Cuz I Love You", "Tempo"));
        artistToSongs.put("Camila Cabello", Arrays.asList("Havana", "Never Be the Same", "Señorita", "My Oh My", "Liar"));
        artistToSongs.put("Demi Lovato", Arrays.asList("Sorry Not Sorry", "Cool for the Summer", "Heart Attack", "Skyscraper", "Confident"));
        artistToSongs.put("Nicki Minaj", Arrays.asList("Super Bass", "Anaconda", "Starships", "Moment 4 Life", "Chun-Li"));
        artistToSongs.put("Shakira", Arrays.asList("Hips Don't Lie", "Waka Waka", "Whenever, Wherever", "She Wolf", "La La La"));
        artistToSongs.put("Pitbull", Arrays.asList("Timber", "Give Me Everything", "Fireball", "International Love", "Don't Stop the Party"));
        artistToSongs.put("Daddy Yankee", Arrays.asList("Gasolina", "Dura", "Con Calma", "Shaky Shaky", "Limbo"));
        artistToSongs.put("Luis Fonsi", Arrays.asList("Despacito", "Échame la Culpa", "Imposible", "Calypso", "No Me Doy por Vencido"));
        artistToSongs.put("J Balvin", Arrays.asList("Mi Gente", "Ginza", "Safari", "Ay Vamos", "Ambiente"));
        artistToSongs.put("Bad Bunny", Arrays.asList("MIA", "DÁKITI", "Callaíta", "Vete", "Yonaguni"));
        artistToSongs.put("Maluma", Arrays.asList("Felices los 4", "Hawái", "Borró Cassette", "Corazón", "El Perdedor"));
        artistToSongs.put("Ozuna", Arrays.asList("Se Preparó", "Dile Que Tú Me Quieres", "Taki Taki", "Baila Baila Baila", "Caramelo"));
        artistToSongs.put("Enrique Iglesias", Arrays.asList("Bailando", "Hero", "Duele el Corazón", "El Perdón", "Escape"));
        artistToSongs.put("Jennifer Lopez", Arrays.asList("On The Floor", "Let's Get Loud", "Ain't Your Mama", "If You Had My Love", "Waiting for Tonight"));
        artistToSongs.put("Ricky Martin", Arrays.asList("Livin' la Vida Loca", "Vente Pa' Ca", "She Bangs", "La Mordidita", "María"));
        artistToSongs.put("Gloria Estefan", Arrays.asList("Conga", "Rhythm Is Gonna Get You", "Anything for You", "Get on Your Feet", "1-2-3"));
        artistToSongs.put("Cyndi Lauper", Arrays.asList("Girls Just Want to Have Fun", "Time After Time", "True Colors", "She Bop", "All Through the Night"));
        artistToSongs.put("Toto", Arrays.asList("Africa", "Hold the Line", "Rosanna", "Georgy Porgy", "I'll Be Over You"));
        artistToSongs.put("Journey", Arrays.asList("Don't Stop Believin'", "Faithfully", "Any Way You Want It", "Open Arms", "Separate Ways"));
        artistToSongs.put("Phil Collins", Arrays.asList("In the Air Tonight", "Against All Odds", "Another Day in Paradise", "You'll Be in My Heart", "One More Night"));
        artistToSongs.put("Genesis", Arrays.asList("Invisible Touch", "Land of Confusion", "That's All", "Follow You Follow Me", "I Can't Dance"));
        artistToSongs.put("Dire Straits", Arrays.asList("Sultans of Swing", "Money for Nothing", "Walk of Life", "Brothers in Arms", "Romeo and Juliet"));
        artistToSongs.put("The Police", Arrays.asList("Every Breath You Take", "Roxanne", "Message in a Bottle", "Don't Stand So Close to Me", "Walking on the Moon"));
        artistToSongs.put("Sting", Arrays.asList("Fields of Gold", "Englishman in New York", "Desert Rose", "If I Ever Lose My Faith in You", "Fragile"));
        artistToSongs.put("Eric Clapton", Arrays.asList("Tears in Heaven", "Layla", "Wonderful Tonight", "Change the World", "Cocaine"));
        artistToSongs.put("Santana", Arrays.asList("Smooth", "Black Magic Woman", "Maria Maria", "Oye Como Va", "The Game of Love"));
        artistToSongs.put("Carlos Vives", Arrays.asList("La Bicicleta", "Volví a Nacer", "Fruta Fresca", "Nota de Amor", "Robarte un Beso"));
        // ... (add more as needed)
    }

    // Top 1000 most popular songs (for demo, a small sample; expand as needed)
    private static final java.util.List<String> topSongs = Arrays.asList(
        "Blinding Lights - The Weeknd",
        "Shape of You - Ed Sheeran",
        "Dance Monkey - Tones and I",
        "Rockstar - Post Malone ft. 21 Savage",
        "One Dance - Drake ft. Wizkid & Kyla",
        "Closer - The Chainsmokers ft. Halsey",
        "Someone You Loved - Lewis Capaldi",
        "Sunflower - Post Malone & Swae Lee",
        "Senorita - Shawn Mendes & Camila Cabello",
        "Bad Guy - Billie Eilish",
        "Old Town Road - Lil Nas X ft. Billy Ray Cyrus",
        "Perfect - Ed Sheeran",
        "Believer - Imagine Dragons",
        "God's Plan - Drake",
        "Havana - Camila Cabello ft. Young Thug",
        "Uptown Funk - Mark Ronson ft. Bruno Mars",
        "Despacito - Luis Fonsi ft. Daddy Yankee",
        "Thinking Out Loud - Ed Sheeran",
        "Girls Like You - Maroon 5 ft. Cardi B",
        "Lucid Dreams - Juice WRLD"
        // ... add up to 1000 songs as needed ...
    );

    public MusicRecommender() {
        setTitle("Music Recommender");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < 3; i++) {
            inputPanel.add(new JLabel("Song #" + (i+1) + " title:"));
            songFields[i] = new JTextField();
            inputPanel.add(songFields[i]);
            inputPanel.add(new JLabel("Artist for song #" + (i+1) + ":"));
            artistFields[i] = new JTextField();
            inputPanel.add(artistFields[i]);
        }
        inputPanel.add(new JLabel("Favorite song of the week title:"));
        songFields[3] = new JTextField();
        inputPanel.add(songFields[3]);
        inputPanel.add(new JLabel("Artist for favorite song:"));
        artistFields[3] = new JTextField();
        inputPanel.add(artistFields[3]);

        recommendButton = new JButton("Get Recommendation");
        playButton = new JButton("Play Song");
        playButton.setEnabled(false);
        resultArea = new JTextArea(4, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        recommendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recommendSong();
            }
        });
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playRecommendedSong();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(recommendButton);
        bottomPanel.add(playButton);

        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.NORTH);
    }

    private void recommendSong() {
        Set<String> artists = new LinkedHashSet<>();
        Set<String> enteredSongs = new HashSet<>();
        for (int i = 0; i < artistFields.length; i++) {
            String artist = artistFields[i].getText().trim();
            String song = songFields[i].getText().trim();
            if (!artist.isEmpty()) {
                artists.add(artist);
            }
            if (!song.isEmpty()) {
                enteredSongs.add(song);
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        lastRecommendedSong = null;
        lastRecommendedArtist = null;
        for (String artist : artists) {
            java.util.List<String> songs = artistToSongs.get(artist);
            if (songs != null) {
                java.util.List<String> available = new java.util.ArrayList<>(songs);
                available.removeAll(enteredSongs);
                if (!available.isEmpty()) {
                    String rec = available.get(new java.util.Random().nextInt(available.size()));
                    sb.append("We recommend you listen to '")
                      .append(rec)
                      .append("' by ")
                      .append(artist)
                      .append("!\n");
                    lastRecommendedSong = rec;
                    lastRecommendedArtist = artist;
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            // Recommend from top 1000 songs, excluding already entered
            java.util.List<String> available = new java.util.ArrayList<>(topSongs);
            available.removeAll(enteredSongs);
            if (!available.isEmpty()) {
                String rec = available.get(new java.util.Random().nextInt(available.size()));
                sb.append("We recommend you listen to: ").append(rec).append("!\n");
                lastRecommendedSong = rec;
                lastRecommendedArtist = null;
            } else {
                sb.append("Try exploring more songs by your favorite artists!");
                lastRecommendedSong = null;
                lastRecommendedArtist = null;
            }
        }
        resultArea.setText(sb.toString());
        playButton.setEnabled(lastRecommendedSong != null);
    }

    private void playRecommendedSong() {
        if (lastRecommendedSong == null) return;
        String query;
        if (lastRecommendedArtist != null) {
            query = lastRecommendedSong + " " + lastRecommendedArtist;
        } else {
            query = lastRecommendedSong;
        }
        try {
            String url = "https://www.youtube.com/results?search_query=" + java.net.URLEncoder.encode(query, "UTF-8");
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to open browser.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MusicRecommender().setVisible(true);
        });
    }
}

