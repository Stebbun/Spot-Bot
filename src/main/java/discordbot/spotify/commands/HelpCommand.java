package discordbot.spotify.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;

/**
 * Created by stebbun on 10/11/2017.
 */
public class HelpCommand implements CommandExecutor{
    private final CommandHandler commandHandler;

    public HelpCommand(CommandHandler commandHandler){
        this.commandHandler = commandHandler;
    }

    @Command(aliases = {"!s.help", "!help"}, description = "Shows a list of available commands for Spot Bot")
    public String onHelpCommand(){
        StringBuilder builder = new StringBuilder();

        //xml code block format
        builder.append("```xml");

        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands()) {
            if (!simpleCommand.getCommandAnnotation().showInHelpPage()) {
                continue; // skip command
            }
            builder.append("\n");
            if (!simpleCommand.getCommandAnnotation().requiresMention()) {
                // the default prefix only works if the command does not require a mention
                builder.append(commandHandler.getDefaultPrefix());
            }
            String usage = simpleCommand.getCommandAnnotation().usage();
            if (usage.isEmpty()) { // no usage provided, using the first alias
                usage = simpleCommand.getCommandAnnotation().aliases()[0];
            }
            builder.append(usage);
            String description = simpleCommand.getCommandAnnotation().description();
            if (!description.equals("none")) {
                builder.append(" | ").append(description);
            }
        }

        builder.append("\n```");    //end of code block format
        return builder.toString();
    }
}
