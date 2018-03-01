package me.piggypiglet.igbot.core.utils.file;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.igbot.core.framework.BinderModule;
import me.piggypiglet.igbot.core.objects.Config;

import java.io.FileReader;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class ConfigUtils {
    @Inject private Config cfg;

    public ConfigUtils() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);
    }

    public String getItem(String item) {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(cfg.getConfig()));
            Map<String, String> data = gson.fromJson(reader, LinkedTreeMap.class);
            if (data.containsKey(item)) {
                return data.get(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item + " not found in the config.";
    }
}
