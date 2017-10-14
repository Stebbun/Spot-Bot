package discordbot.spotify.commands;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.FeaturedPlaylistsRequest;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.SimplePlaylist;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import discordbot.main.BotRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by stebbun on 10/13/2017.
 */
public class FeaturedPlaylistsCommand implements CommandExecutor{

    @Command(aliases = {"spot.feat"}, description = "Displays featured playlists from Spotify.", usage = "spot.feat")
    public String onFeaturedPlaylistsCommand(){
        String accessToken = BotRunner.spotBot.getAccessToken();
        Api api = Api.builder().accessToken(accessToken).build();

        final FeaturedPlaylistsRequest request = api.getFeaturedPlaylists()
                .limit(10)
                .offset(0)
                .country("US")
                .timestamp(new Date())
                .build();

        try{
            FeaturedPlaylists featuredPlaylists = request.get();
            StringBuilder builder = new StringBuilder();

            //xml code block format
            builder.append("```xml");
            builder.append("\nFeatured Playlists:\n");
            List<SimplePlaylist> albumList = featuredPlaylists.getPlaylists().getItems();
            for(SimplePlaylist e : albumList){
                builder.append("\t" + e.getName() + "\n");
            }
            builder.append("\n```");
            return builder.toString();
        } catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
