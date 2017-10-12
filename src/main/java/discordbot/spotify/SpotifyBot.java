package discordbot.spotify;
import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import discordbot.spotify.commands.HelpCommand;
import discordbot.spotify.commands.InfoCommand;
import discordbot.spotify.commands.NewReleasesCommand;
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
                //register message listener
                SpotifyMessageListener spotListener = new SpotifyMessageListener();
                api.registerListener(spotListener);

                //Initialize command handler and register commands
                CommandHandler cmdHandler = new JavacordHandler(api);
                HelpCommand helpCmd = new HelpCommand(cmdHandler);
                cmdHandler.registerCommand(helpCmd);
                cmdHandler.registerCommand(new InfoCommand());
                cmdHandler.registerCommand(new NewReleasesCommand());
            }

            public void onFailure(Throwable throwable) {

            }
        });
    }
}
