package discordbot.main;

import discordbot.spotify.SpotifyBot;
import discordbot.tokens.Tokens;

/**
 * Created by stebbun on 10/11/2017.
 */
public class BotRunner {

    public static final String SPOTIFY_BOT_TOKEN = Tokens.getSpotBotToken();
    public static SpotifyBot spotBot;

    public static void main(String[] args){
        spotBot = new SpotifyBot(SPOTIFY_BOT_TOKEN, true);
    }
}
