package discordbot.spotify.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

/**
 * Created by stebbun on 10/11/2017.
 */
public class NewReleasesCommand implements CommandExecutor {

    @Command(aliases = {"!s.releases"}, description = "Displays new music releases from Spotify.", usage = "!s.releases")
    public String onNewReleasesCommand(){

        return "";
    }
}
