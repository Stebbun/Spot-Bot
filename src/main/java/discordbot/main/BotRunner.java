package discordbot.main;

import discordbot.spotify.SpotifyBot;

/**
 * Created by stebbun on 10/11/2017.
 */
public class BotRunner {

    public static final String SPOTIFY_BOT_TOKEN = "MzY3NzcwODc3NzkxODMwMDM3.DMARrg.j-1kESbkOBUJTnxR6XZI354DemY";

    public static void main(String[] args){
        SpotifyBot spotBot = new SpotifyBot(SPOTIFY_BOT_TOKEN, true);
    }
}
