package com.github.mouse0w0.filemanager;

import com.github.mouse0w0.filemanager.util.json.JsonUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static com.github.mouse0w0.filemanager.util.PathUtils.createFileIfNotExists;

public class UserSettings {
    public static final Path USER_SETTINGS = Path.of(SystemUtils.USER_HOME, ".file_manager", "settings.json");

    private static UserSettings instance;

    private LinkedList<String> recentOpen = new LinkedList<>();

    public static UserSettings getInstance() {
        return instance;
    }

    public static void init() {
        load();
        Thread shutdownHook = new Thread(UserSettings::save);
        shutdownHook.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public static void load() {
        createFileIfNotExists(USER_SETTINGS);
        try (var reader = Files.newBufferedReader(USER_SETTINGS)) {
            instance = com.github.mouse0w0.filemanager.util.json.JsonUtils.gson().fromJson(reader, UserSettings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (instance == null) {
            instance = new UserSettings();
        }
    }

    public static void save() {
        createFileIfNotExists(USER_SETTINGS);
        try (var writer = Files.newBufferedWriter(USER_SETTINGS)) {
            JsonUtils.gson().toJson(instance, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getRecentOpen() {
        return recentOpen;
    }

    public void addRecentOpen(Path path) {
        String stringPath = path.toAbsolutePath().toString();
        if (recentOpen.contains(stringPath)) return;
        recentOpen.addFirst(stringPath);
        while (recentOpen.size() > 10) recentOpen.removeLast();
    }
}
