package au.edu.unsw.infs3634.musicrecommender;

import java.util.ArrayList;

public class Music {

    public Music(int id, String name, String singer, double rating, boolean likes, String description, int imageId, String url, String genre) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.rating = rating;
        this.likes = likes;
        this.description = description;
        this.imageId = imageId;
        this.url = url;
        this.genre = genre;
    }

    private int id;
    private String name;
    private String singer;
    private double rating;
    private boolean likes;
    private String description;
    private int imageId;
    private String url;
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isLikes() {
        return likes;
    }

    public void setLikes(boolean like) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String url) {
        this.genre = genre;
    }


    //For insert data into database
    public static ArrayList<Music> getMusic() {
        ArrayList<Music> music = new ArrayList<>();
        String description1 = "\"Stay\" (stylized in all caps) is a song by Australian rapper and singer the Kid Laroi and Canadian singer Justin Bieber. It was released through Grade A Productions and Columbia Records on 9 July 2021, as the lead single from Laroi's reloaded mixtape, F*ck Love 3: Over You. The song was produced by Cashmere Cat, Charlie Puth, Omer Fedi, and Blake Slatkin.";
        String description2 = "\"Ghost\" is a song by Canadian singer Justin Bieber. It was sent to French contemporary hit radio by Def Jam Recordings on September 10, 2021, as the sixth single from his sixth studio album, Justice. Bieber wrote the song with Jon Bellion, Jordan K. Johnson, Stefan Johnson, and Michael Pollack. The track was produced by Bellion and the Monsters & Strangerz.";
        String description3 = "\"Bad Habits\" is a song by English singer-songwriter Ed Sheeran. It was released on 25 June 2021, through Asylum Records UK, as the lead single of his upcoming fifth studio album, =. The song marked his first solo release from an album in over four years. A limited edition CD single was also released.";
        String description4 = "\"Cold Heart (Pnau remix)\" is a song by English singers Elton John and Dua Lipa, produced by Australian trio Pnau, and released through EMI and Mercury Records on 13 August 2021 as the lead single from John's 32nd studio album The Lockdown Sessions. The single became John’s first UK number one since 2005.";
        String description5 = "\"Boyz\" is the debut solo single by English singer Jesy Nelson, featuring Trinidadian-born rapper Nicki Minaj. It was released on 8 October 2021, through Polydor Records. The song uses elements of American rapper Diddy's 2001 song \"Bad Boy for Life\" and lyrically, speaks of Nelson's infatuation with rebellious men.";
        String description6 = "Face Off is Tech N9ne’s latest single, released 9th of October 2021, featuring Joey Cool, King Iso, Krizz Kaliko and Dwayne Johnson. This is The Rock’s debut song in the rap industry and has gained much attention from fans and critics alike.";
        String description7 = "\"Money\" is a song by Thai rapper and singer, and Blackpink member Lisa from her debut single album Lalisa (2021). It was released by YG Entertainment and Interscope Records on September 10, 2021. The lyrics for the track was written by Bekuh Boom and Vince, with music being composed by them alongside 24 and R. Tee.";
        String description8 = "\"Shivers\" is a song by English singer-songwriter Ed Sheeran, released through Asylum Records UK on 10 September 2021 as the second single from his upcoming fifth studio album, = (2021). \"Shivers\" entered at the top of the charts in the United Kingdom, Ireland, Germany and Sweden, dethroning Sheeran's previous single \"Bad Habits\" after eleven consecutive weeks.";
        String description9 = "\"Mountain Stream\" is a Chinese guzheng song, which is generally believed to have been created by guzheng artists after the Republic of China.  His music is also based on \"Boya's guqin meets a bosom friend\".  The most widely spread and influential music is the transmission of Zhejiang Wulin school, with elegant melody and meaningful flavor, showing \"lofty mountains and flowing water\".";
        String description10 = "The Seasons, Op. 37a (also seen as Op. 37b; Russian: Времена года; published with the French title Les Saisons), is a set of twelve short character pieces for solo piano by the Russian composer Pyotr Ilyich Tchaikovsky. Each piece is the characteristic of a different month of the year in Russia. June is one of them. ";
        music.add(new Music(1, "STAY", "Justin Bieber & The Kid Laroi", 0, true, description1, R.drawable.stay, "https://www.youtube.com/watch?v=kTJczUoc26U", "Pop"));
        music.add(new Music(2, "Ghost", "Justin Bieber", 0.5, false, description2, R.drawable.ghost, "https://www.youtube.com/watch?v=Fp8msa5uYsc", "Reggae"));
        music.add(new Music(3, "Bad Habits", "Ed Sheeran", 1, true, description3, R.drawable.badhabit, "https://music.youtube.com/watch?v=orJSJGHjBLI&list=PL4fGSI1pDJn44PMHPLYatj8rta8WYtZ8_", "Rap"));
        music.add(new Music(4, "Cold Heart", "Pnau,Dua Lipa & Elton John", 1.5, false, description4, R.drawable.coldheart, "https://music.youtube.com/watch?v=qod03PVTLqk&list=PL4fGSI1pDJn44PMHPLYatj8rta8WYtZ8_", "Disco"));
        music.add(new Music(5, "Boyz", "Nicki Minaj & Jesy Nelson", 2, true, description5, R.drawable.boyz, "https://music.youtube.com/watch?v=u884fEIPY3g&list=PL4fGSI1pDJn44PMHPLYatj8rta8WYtZ8_", "Pop"));
        music.add(new Music(6, "Face Off", "Tech N9ne, King Iso, Dwayne Johnson & Joey Cool", 2.5, false, description6, R.drawable.faceoff, "https://music.youtube.com/playlist?list=PL4fGSI1pDJn44PMHPLYatj8rta8WYtZ8_", "Rap"));
        music.add(new Music(7, "MONEY", "LISA", 3, true, description7, R.drawable.money, "https://music.youtube.com/watch?v=dNCWe_6HAM8&list=PL4fGSI1pDJn44PMHPLYatj8rta8WYtZ8_", "Hip hop"));
        music.add(new Music(8, "Shivers", "Ed Sheeran", 3.5, false, description8, R.drawable.shivers, "https://music.youtube.com/watch?v=Il0S8BoucSA&list=PL4fGSI1pDJn44PMHPLYatj8rta8WYtZ8_", "Pop"));
        music.add(new Music(9, "Mountain Stream", "Unknown", 4, true, description9, R.drawable.mountainstream, "https://www.youtube.com/watch?v=GC-DHyrZBa0", "Classical"));
        music.add(new Music(10, "June Barcarolle", "Tchaikovsky", 5, false, description10, R.drawable.junebarcarolle, "https://www.youtube.com/watch?v=BvAsyHsGimA", "Classical"));

        return music;
    }


}
