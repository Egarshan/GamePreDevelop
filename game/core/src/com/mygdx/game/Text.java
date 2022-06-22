package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Text {

    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private BitmapFont mainFont, dialogFont, characterNameFont;
    private FreeTypeFontGenerator mainFontGenerator, dialogFontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private JsonValue reactions, dialogs;
    private JsonReader jsonReader;

    public Text() {
        jsonReader = new JsonReader();
        reactions = jsonReader.parse(Gdx.files.internal("character_reactions.json")).get("reactions");

        mainFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("remains.ttf"));
        dialogFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("dialogues/dialogues_font.otf"));

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARACTERS;
        parameter.size = Gdx.graphics.getWidth()/20;
        parameter.color.add(Color.BLACK);

        mainFont = mainFontGenerator.generateFont(parameter);

        parameter.size = 40;
        dialogFont = dialogFontGenerator.generateFont(parameter);

        parameter.size = 60;
        characterNameFont = dialogFontGenerator.generateFont(parameter);

        mainFontGenerator.dispose();
    }

    public BitmapFont getMainFont() {
        return mainFont;
    }

    public BitmapFont getDialogFont() {
        return dialogFont;
    }

    public BitmapFont getCharacterNameFont() {
        return characterNameFont;
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
