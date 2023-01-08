package com.example.finalproject;

import java.util.ArrayList;

public class DataManager
{

    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/p1JPKLa-Ofc/hqdefault.jpg")
                .setName("Beyoncé - CUFF IT")
                .setDuration(232)
        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/90RLzVUuXe4/hqdefault.jpg?sqp=-oaymwEcCPYBEIoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLD8wJDxmXiBFqNoJ2qVxk66LV2v0g")
                .setName("David Guetta & Bebe Rexha - I'm Good")
                .setDuration(177)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/Uq9gPaIzbe8/hqdefault.jpg?sqp=-oaymwEcCPYBEIoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCigTNrdUDse1JZuyAi1aIE4K-ZZQ")
                .setName("Sam Smith, Kim Petras - Unholy")
                .setDuration(275)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/JFFq8xgBQZI/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLDlIKQ5Hr8BwzNU11BQ4o9RlwVYkw")
                .setName("summer nostalgia - rihanna, avicii, justin bieber, kygo, selena gomez, alok, bastille, david guetta\n")
                .setDuration(4237)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/an_webp/MwpMEbgC7DA/mqdefault_6s.webp?du=3000&sqp=CICS2JwG&rs=AOn4CLBm8Iipo58RRyrp1QKw9YpaFE5Wsw")
                .setName("Another Love")
                .setDuration(247)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/an_webp/BoEKWtgJQAU/mqdefault_6s.webp?du=3000&sqp=COKS2JwG&rs=AOn4CLBQh7lkW0IkbAUobxpU68iSUPC4dA")
                .setName("JAY Z, Kanye West - Otis ft. Otis Redding")
                .setDuration(247)

        );

        songs.add(new Song()
                .setImage("https://images.app.goo.gl/eaHiRtd5mpsyKyPA8")
                .setName("10 hour of banan phone")
                .setDuration(36_000)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/an_webp/BoEKWtgJQAU/mqdefault_6s.webp?du=3000&sqp=COKS2JwG&rs=AOn4CLBQh7lkW0IkbAUobxpU68iSUPC4dA")
                .setName("JAY Z, Kanye West - Otis ft. Otis Redding")
                .setDuration(247)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/5tWsy2x3w0U/hqdefault.jpg?sqp=-oaymwEbCKgBEF5IVfKriqkDDggBFQAAiEIYAXABwAEG&rs=AOn4CLDlyy6EAgQlZqiPLCSHzZ3HAzVW-A")
                .setName("2Pac - All Eyez On Me")
                .setDuration(232)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/pZw9veQ76fo/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBR19Yd6nWz_dQNaQdwnzgW0vSVzg")
                .setName("Five Little Ducks")
                .setDuration(174)

        );

        songs.add(new Song()
                .setImage("https://i.ytimg.com/vi/tO7CCP7liwI/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLB5Fb-wth1ZsOlgXu01qZdVWJb7Hg")
                .setName("Coldplay: Everyday Life Live in Jordan")
                .setDuration(230)

        );

        return songs;
    }
}