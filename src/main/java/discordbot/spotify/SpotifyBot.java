package discordbot.spotify;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import discordbot.main.BotRunner;
import discordbot.spotify.commands.FeaturedPlaylistsCommand;
import discordbot.spotify.commands.HelpCommand;
import discordbot.spotify.commands.InfoCommand;
import discordbot.spotify.commands.NewReleasesCommand;
import discordbot.spotify.listener.SpotifyMessageListener;
import discordbot.tokens.Tokens;
import discordbot.util.TokenTimer;


/**
 * Created by stebbun on 10/11/2017.
 */
public class SpotifyBot {

    private Api spotAPI;
    private String accessToken;
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
                cmdHandler.registerCommand(new FeaturedPlaylistsCommand());

                //setup spotify web api
                setupSpotAPI();

                //setup access token every 3000 seconds
                TokenTimer tokenTimer = new TokenTimer(3000);
                tokenTimer.startTokenTimer();
            }

            public void onFailure(Throwable throwable) {
                System.out.println("ERROR: Discord api failed to connect.");
            }
        });
    }

    private void setupSpotAPI(){
        this.spotAPI = Api.builder().clientId(clientID).clientSecret(clientSecret).redirectURI(redirectURI).build();
    }

    public void setupAccessToken(){
        final Api api = this.getSpotAPI();
        //create a request object
        final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

        //Use the request object to make asynchronous request
        final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

        //Add callbacks to handle success or failure
        Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
            @Override
            public void onSuccess(ClientCredentials clientCredentials) {
                accessToken = clientCredentials.getAccessToken();
                /* The tokens were retrieved successfully! */
                System.out.println("Successfully retrieved an access token! " + accessToken);
                System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");

                /* Set access token on the Api object so that it's used going forward */
                api.setAccessToken(accessToken);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("ERROR: Failed to retrieve an access token from Spotify API.");
            }
        });
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
