package discordbot.spotify;
import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import discordbot.spotify.listener.SpotifyMessageListener;


/**
 * Created by stebbun on 10/11/2017.
 */
public class SpotifyBot {
    public SpotifyBot(String token, boolean isBot){
        final DiscordAPI api = Javacord.getApi(token, isBot);

        //connect
        api.connect(new FutureCallback<DiscordAPI>() {
            public void onSuccess(DiscordAPI discordAPI) {
                SpotifyMessageListener spotListener = new SpotifyMessageListener();
                api.registerListener(spotListener);
            }

            public void onFailure(Throwable throwable) {

            }
        });
    }
}
