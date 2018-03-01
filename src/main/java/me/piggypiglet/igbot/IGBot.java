package me.piggypiglet.igbot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.igbot.core.framework.BinderModule;
import me.piggypiglet.igbot.core.utils.file.ConfigUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class IGBot {
    @Inject private ConfigUtils cutil;

    private IGBot() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        try {
            new JDABuilder(AccountType.BOT)
                    .setToken(cutil.getItem("token"))
                    .setGame(Game.watching("InfinityGrid"))
                    .buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new IGBot();
    }
}