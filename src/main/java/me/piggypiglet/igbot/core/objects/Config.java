package me.piggypiglet.igbot.core.objects;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.igbot.IGBot;
import me.piggypiglet.igbot.core.framework.BinderModule;
import me.piggypiglet.igbot.core.utils.file.FileUtils;

import java.io.File;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class Config {
    @Inject private FileUtils futil;
    private File config;

    public Config() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        config = new File("./config.json");
        try {
            if (!config.exists()) {
                if (config.createNewFile()) {
                    if (futil.exportResource(IGBot.class.getResourceAsStream("/config.json"), "./config.json")) {
                        System.out.println("Config successfully created!");
                    } else {
                        System.out.println("Config creation failed!");
                    }
                }
            } else {
                System.out.println("Config successfully loaded!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getConfig() {
        return config;
    }
}
