package discordbot.util;

import discordbot.main.BotRunner;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by stebbun on 10/13/2017.
 */
public class TokenTimer {
    Timer timer;
    int seconds;

    public TokenTimer(int seconds){
        timer = new Timer();
        this.seconds = seconds;
    }

    public void startTokenTimer(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                BotRunner.spotBot.setupAccessToken();
            }
        }, 0, seconds * 1000);
    }
}
