package discordbot.spotify.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stebbun on 10/11/2017.
 */
public class InfoCommand implements CommandExecutor {

    @Command(aliases = {"!s.info", "!info"}, description = "Displays requested information.",
            usage = "!s.info [bot|author|time]")
    public String onInfoCommand(String[] args){
        //need to specify a tag
        if (args.length > 1 || args.length == 0) {
            return "Invalid Arguments.";
        }
        else if(args.length == 1){
            if(args[0].equals("bot")){
                return "- **Author:** Stebbun\n" +
                        "- **Language:** Java\n" +
                        "- **Command-Lib:** sdcf4j";
            }
            else if(args[0].equals("author")){
                return "- **Name:** Stebbun\n" +
                        "- **Age:** 20\n" +
                        "- **Website:** ";
            }
            else if(args[0].equals("time")){
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date currentDate = new Date(System.currentTimeMillis());
                return "It's " + format.format(currentDate);
            }
        }
        return "Invalid Arguments.";
    }
}
