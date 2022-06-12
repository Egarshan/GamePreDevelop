package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Corridor.GameCamera;
import com.mygdx.game.Corridor.Player;
public class Dialogs extends Text {

    private static JsonValue dialogs;

    private TextureRegion dialogFrame, shortAnswer, shortAnswerMouseOn, longAnswer, longAnswerMouseOn;

    private Vector2 dialogFramePosition, answer1Position, answer2Position;

    private float dialogFrameWidth, dialogFrameHeight;
    private float shortAnswerWidth, shortAnswerHeight;
    private float longAnswerWidth, longAnswerHeight;
    private Label textLabel, answerLabel1, answerLabel2, answerLabel3;
    private boolean draw;

    private String dialog;
    private int n, k, i, count1, count2, speaker1Count, speaker2Count;

    private GameCamera camera;

    public Dialogs(GameCamera camera) {
        this.camera = camera;
        draw = false;

        dialogFrame = ResourcesClass.getResources().get(10)[0];
        shortAnswer = ResourcesClass.getResources().get(10)[4];
        shortAnswerMouseOn = ResourcesClass.getResources().get(10)[3];
        longAnswer = ResourcesClass.getResources().get(10)[2];
        longAnswerMouseOn = ResourcesClass.getResources().get(10)[1];

        dialogFrameWidth = MyGdxGame.WIDTH/1.05f;
        dialogFrameHeight = dialogFrameWidth/5.72f;
        shortAnswerWidth = dialogFrameWidth/6f;
        shortAnswerHeight = shortAnswerWidth/4.84f;
        longAnswerWidth = dialogFrameWidth/4f;
        longAnswerHeight = longAnswerWidth/7.228f;

        dialogFramePosition = new Vector2(camera.getCameraPosition().x-dialogFrameWidth/2f, MyGdxGame.HEIGHT/20f);
        answer1Position = new Vector2();
        answer2Position = new Vector2();

        textLabel = new Label("", new Label.LabelStyle(getFont(), Color.WHITE));
        textLabel.setWrap(true);
        textLabel.setAlignment(Align.left);
        textLabel.setFontScale(0.5f);
        textLabel.setBounds(dialogFramePosition.x+dialogFrameWidth/10f, dialogFramePosition.y+dialogFrameHeight/5f, dialogFrameWidth/1.5f, dialogFrameHeight/1.5f);
        n = 0;
        i = 0;
        k = 0;
        count1 = 1;
        count2 = 1;
        speaker1Count = 1;
        speaker2Count = 1;
    }

    public void update() {
        dialogFramePosition.set(camera.getCameraPosition().x-dialogFrameWidth/2f, MyGdxGame.HEIGHT/20f);
        textLabel.setBounds(dialogFramePosition.x+dialogFrameWidth/10f, dialogFramePosition.y+dialogFrameHeight/5f, dialogFrameWidth/1.5f, dialogFrameHeight/1.5f);
        if(draw) {
            if (count1 == count2) {
                try {
                    if(dialogs.get(0).get(n).name.equals("finish dialog")) {
                        finishDialog();
                        Data.saveDialog(dialog);
                    }
                    if (dialogs.get(0).get(n).isArray()) {
                        if (dialogs.get(0).get(n).size == 1) {
                            setTextLabel(dialogs.get(0).get(n).name.substring(0, dialogs.get(0).get(n).name.length() - 1) + "\n" + getText(dialogs, n, Player.getMask()).asString());
                            n++;
                        } else {
                            switch (Player.getMask()) {
                                case "anger":
                                    k = 0;
                                    break;
                                case "sad":
                                    k = 1;
                            }
                            if (i < dialogs.get(0).get(n).get(k).get(0).get(0).size) {
                                setTextLabel(dialogs.get(0).get(n).get(k).get(0).get(0).get(i).name.substring(0, dialogs.get(0).get(n).get(k).get(0).get(0).get(i).name.length() - 1) + "\n" + getText(dialogs, n, k, i).asString());
                                i++;
                            } else {
                                i = 0;
                                n++;
                                count2++;
                                update();
                            }
                        }
                    } else {
                        setTextLabel(dialogs.get(0).get(n).name.substring(0, dialogs.get(0).get(n).name.length() - 1) + "\n" + getText(dialogs, n).asString());
                        n++;
                        k = 0;
                    }
                } catch (Exception ignored) {
                    finishDialog();
                }
                count1++;
            }
        }
    }

    public void render() {
        if(draw) {
            MyGdxGame.batch.draw(dialogFrame, dialogFramePosition.x, dialogFramePosition.y, dialogFrameWidth, dialogFrameHeight);
            textLabel.draw(MyGdxGame.batch, 1);
        }
    }

    public void dispose() {

    }

    public void nextText() {
        count2++;
    }

    public void finishDialog() {
        draw = false;
        n = 0;
        k = 0;
        i = 0;
        count1 = 1;
        count2 = 1;
    }

    public void setTextLabel(String text) {
        textLabel.setText(text);
    }

    public void showDialog(String dialog) {
        dialogs = getDialogs(dialog);
        this.dialog = dialog;
        draw = true;
    }

    public boolean isShowDialog() {
        return draw;
    }
}
