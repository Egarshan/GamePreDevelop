package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Text {

    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private BitmapFont font;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private JsonValue reactions, dialogs;
    private JsonReader jsonReader;

    public Text() {
        jsonReader = new JsonReader();
        reactions = jsonReader.parse(Gdx.files.internal("character_reactions.json")).get("reactions");

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("remains.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARACTERS;
        parameter.size = Gdx.graphics.getWidth()/20;
        parameter.color.add(Color.BLACK);

        font = fontGenerator.generateFont(parameter);

        fontGenerator.dispose();
    }

    public BitmapFont getFont() {
        return font;
    }

    public JsonValue getReactions() {
        return reactions;
    }

    public JsonValue getDialogs(String dialog) {
        dialogs = jsonReader.parse(Gdx.files.internal("dialogs.json")).get(dialog);
        return dialogs;
    }

    public JsonValue getText(JsonValue value, int n) {
        return value.get(0).get(n);
    }

    public JsonValue getText(JsonValue value, int n, int k) {
        return value.get(0).get(n).get(0).get(k);
    }

    public JsonValue getText(JsonValue value, int n, String name) {
        return value.get(0).get(n).get(0).get(name);
    }

    public JsonValue getText(JsonValue value, int n, int k, int i) {
        return value.get(0).get(n).get(k).get(0).get(0).get(i);
    }

    public JsonValue getText(JsonValue value, String name) {
        return value.get(0).get(name);
    }

    public JsonValue getText(JsonValue value, String name1, String name2) {
        return value.get(0).get(name1).get(0).get(name2);
    }
}
