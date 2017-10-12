package discordbot.spotify.commands;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import discordbot.main.BotRunner;

/**
 * Created by stebbun on 10/11/2017.
 */
public class NewReleasesCommand implements CommandExecutor {

    @Command(aliases = {"!s.releases"}, description = "Displays new music releases from Spotify.", usage = "!s.releases")
    public String onNewReleasesCommand(){

        return "";
    }
}
