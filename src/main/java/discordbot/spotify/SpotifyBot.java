package discordbot.spotify;
import com.google.common.util.concurrent.FutureCallback;
import com.wrapper.spotify.Api;
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
import discordbot.tokens.Tokens;


/**
 * Created by stebbun on 10/11/2017.
 */
public class SpotifyBot {

    private Api spotAPI;
    private String clientID = Tokens.getSpotifyClientID();
    private String clientSecret = Tokens.getSpotifyClientSecret();
    private String redirectURI = Tokens.getSpotifyRedirectURI();

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

                //setup spotify web api
                setupSpotAPI();
            }

            public void onFailure(Throwable throwable) {

            }
        });
    }

    private void setupSpotAPI(){
        this.spotAPI = Api.builder().clientId(clientID).clientSecret(clientSecret).redirectURI(redirectURI).build();
    }

    public Api getSpotAPI(){
        return this.spotAPI;
    }

    public void setSpotAPI(Api spotAPI) {
        this.spotAPI = spotAPI;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }
}
