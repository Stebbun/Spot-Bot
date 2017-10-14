package discordbot.spotify.commands;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.NewReleasesRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import discordbot.main.BotRunner;

/**
 * Created by stebbun on 10/11/2017.
 */
public class NewReleasesCommand implements CommandExecutor {

    @Command(aliases = {"spot.releases"}, description = "Displays new music releases from Spotify.", usage = "spot.releases")
    public String onNewReleasesCommand(){
        String accessToken = BotRunner.spotBot.getAccessToken();
        Api api = Api.builder().accessToken(accessToken).build();

        final NewReleasesRequest request = api.getNewReleases()
                .limit(15)
                .offset(0)
                .country("US")
                .build();

        try {
            NewReleases newReleases = request.get();
            Page<SimpleAlbum> albums = newReleases.getAlbums();

            StringBuilder builder = new StringBuilder();

            //xml code block format
            builder.append("```xml");
            builder.append("\nNew Releases:\n");

            for(SimpleAlbum e : albums.getItems()){
                builder.append("\t" + e.getName());
                builder.append("\n");
            }

            builder.append("\n```");
            return builder.toString();
        } catch(Exception e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }

        return "";
    }
}
