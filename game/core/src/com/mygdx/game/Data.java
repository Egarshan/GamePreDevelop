package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Data {

    private static Preferences prefs;

    public Data() {
        prefs = Gdx.app.getPreferences("GamePreferences");
    }

    public static Preferences getPrefs() {
        return prefs;
    }

    public static void firstLaunchSettings() {
        prefs.putBoolean("launched", true);
        prefs.putFloat("player_position_x", 300);
        prefs.putFloat("player_position_y", 100);
        prefs.putFloat("camera_position_x", MyGdxGame.WIDTH/2f);
        prefs.putFloat("camera_position_y", MyGdxGame.HEIGHT/2f);
        prefs.putBoolean("dialog_1", false);
        prefs.flush();
    }

    public static void savePlayerPosition(float x, float y) {
        prefs.putFloat("player_position_x", x);
        prefs.putFloat("player_position_y", y);
        prefs.flush();
    }

    public static void saveCamera(float x, float y) {
        prefs.putFloat("camera_position_x", x);
        prefs.putFloat("camera_position_y", y);
        prefs.flush();
    }

    public static void saveDialog(String dialog) {
        prefs.putBoolean(dialog, true);
        prefs.flush();
    }
}
