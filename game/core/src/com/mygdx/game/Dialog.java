package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Corridor.GameCamera;
import com.mygdx.game.Corridor.Player;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Objects;

public class Dialog extends Text {
    private GameCamera camera;
    private Player player;

    private TextureRegion dialogArea, activePhrase;
    private TextureRegion[] aliceSprites, kostyaSprites, emotionIcons;

    private float dialogAreaWidth, dialogAreaHeight;
    private float activePhraseWidth, activePhraseHeight;
    private float aliceWidth, aliceHeight;
    private float kostyaWidth, kostyaHeight;
    private float emotionIconWidth, emotionIconHeight;

    private Vector2 dialogAreaPosition, activePhrasePosition, alicePosition, kostyaPosition;
    private Vector2[] emoIconPostions;

    private Label dialogLabel, characterLabel;
    private Label[] answers;
    private int[] emotionIconsIndexes;

    private boolean isShowDialog, isAnswerForm, isItqueue;
    private boolean[] hasQueue;

    private static JsonValue dialogs;

    private int subDialogCounter, kostyaEmotionIndex, dialogIndex, currentEmoIndex, queueIndex;
    ;

    private String speakingCharacter;

    public Dialog(GameCamera camera, Player player) {
        this.camera = camera;
        this.player = player;

        dialogArea = ResourcesClass.getResources().get(10)[0];
        activePhrase = ResourcesClass.getResources().get(10)[1];

        aliceSprites = new TextureRegion[2];
        kostyaSprites = new TextureRegion[7];
        emotionIcons = new TextureRegion[8];

        emoIconPostions = new Vector2[8];

        answers = new Label[8];
        emotionIconsIndexes = new int[answers.length];

        for (int i = 0; i < aliceSprites.length; i++)
            aliceSprites[i] = ResourcesClass.getResources().get(25)[i];

        for (int i = 0; i < kostyaSprites.length; i++)
            kostyaSprites[i] = ResourcesClass.getResources().get(26)[i];

        for (int i = 0; i < 2; i++)
            emotionIcons[i] = ResourcesClass.getResources().get(27)[i];

        //TEMPORARY
        for (int i = 2; i < emotionIcons.length; i++)
            emotionIcons[i] = ResourcesClass.getResources().get(27)[0];
        //TEMPORARY

        for (int i = 0; i < emoIconPostions.length; i++)
            emoIconPostions[i] = new Vector2();

        dialogAreaWidth = 1713;
        dialogAreaHeight = 363;

        aliceWidth = 728;
        aliceHeight = 922;

        kostyaWidth = 781;
        kostyaHeight = 808;

        emotionIconWidth = 61;
        emotionIconHeight = 81;

        activePhraseWidth = 1221;
        activePhraseHeight = 77;

        dialogAreaPosition = new Vector2(camera.getCameraPosition().x - dialogAreaWidth / 2, MyGdxGame.HEIGHT / 20);
        activePhrasePosition = new Vector2(dialogAreaPosition.x + dialogAreaWidth / 6.7f, dialogAreaPosition.y + dialogAreaHeight / 1.82f - 40 * 3);
        alicePosition = new Vector2(camera.getCameraPosition().x - 20, MyGdxGame.HEIGHT / 7);
        kostyaPosition = new Vector2(camera.getCameraPosition().x - 20, MyGdxGame.HEIGHT / 7);

        characterLabel = new Label("", new Label.LabelStyle(getCharacterNameFont(), Color.BLACK));
        dialogLabel = new Label("", new Label.LabelStyle(getDialogFont(), Color.BLACK));

        for (int i = 0; i < answers.length; i++)
            answers[i] = new Label("", new Label.LabelStyle(getDialogFont(), Color.BLACK));

        for (int i = 0; i < answers.length; i++)
            if (!Objects.equals(answers[i].getText(), "")) {
                answers[i].setBounds(dialogAreaPosition.x + dialogAreaWidth + emotionIconWidth * 1.25f, dialogAreaPosition.y + dialogAreaHeight / 4 - 40 * 2 * i, dialogAreaWidth / 1.5f, dialogAreaHeight / 1.5f);
                emoIconPostions[i].set(dialogAreaPosition.x + dialogAreaWidth / 6.5f, dialogAreaPosition.y + dialogAreaHeight / 4 - 40 * 2 * i + 80);
            }

        isShowDialog = false;
        isAnswerForm = false;

        subDialogCounter = 0;
        dialogIndex = -1;

        hasQueue = new boolean[8];
        queueIndex = 1;

        isItqueue = false;
    }

    public void update() {
        if (isShowDialog) {
            dialogAreaPosition.set(camera.getCameraPosition().x - dialogAreaWidth / 2, MyGdxGame.HEIGHT / 20);
            kostyaPosition.set(camera.getCameraPosition().x - dialogAreaWidth / 2.35f, MyGdxGame.HEIGHT / 7);
            alicePosition.set(camera.getCameraPosition().x - 20, MyGdxGame.HEIGHT / 7);

            activePhrasePosition = new Vector2(dialogAreaPosition.x + dialogAreaWidth / 6.7f, dialogAreaPosition.y + dialogAreaHeight / 4 - 40 * 2 * dialogIndex + 65);

            dialogLabel.setBounds(dialogAreaPosition.x + dialogAreaWidth / 6.5f, dialogAreaPosition.y + dialogAreaHeight / 5, dialogAreaWidth / 1.5f, dialogAreaHeight / 1.5f);
            characterLabel.setBounds(dialogAreaPosition.x + dialogAreaWidth / 6.5f, dialogAreaPosition.y + dialogAreaHeight / 2.5f, dialogAreaWidth / 1.5f, dialogAreaHeight / 1.5f);

            for (int i = 0; i < answers.length; i++)
                if (!answers[i].getText().isEmpty()) {
                    answers[i].setBounds(dialogAreaPosition.x + dialogAreaWidth / 6.5f + emotionIconWidth * 1.25f, dialogAreaPosition.y + dialogAreaHeight / 4 - 40 * 2 * i - 15, dialogAreaWidth / 1.5f, dialogAreaHeight / 1.5f);
                    emoIconPostions[i].set(dialogAreaPosition.x + dialogAreaWidth / 6.5f, dialogAreaPosition.y + dialogAreaHeight / 4 - 40 * 2 * i + 65);
                }

        }
    }

    public void render() {
        if (isShowDialog) {
            MyGdxGame.batch.draw(aliceSprites[player.getMask().getMaskIndex()], alicePosition.x, alicePosition.y, aliceWidth, aliceHeight);
            MyGdxGame.batch.draw(kostyaSprites[kostyaEmotionIndex], kostyaPosition.x, kostyaPosition.y, kostyaWidth, kostyaHeight);
            MyGdxGame.batch.draw(dialogArea, dialogAreaPosition.x, dialogAreaPosition.y, dialogAreaWidth, dialogAreaHeight);
            if (dialogIndex != -1)
                MyGdxGame.batch.draw(activePhrase, activePhrasePosition.x, activePhrasePosition.y, activePhraseWidth, activePhraseHeight);

            if (!dialogLabel.getText().isEmpty())
                dialogLabel.draw(MyGdxGame.batch, 1);

            if (Objects.equals(speakingCharacter, "Алиса")) {
                for (int i = 0; i < answers.length; i++)
                    if (!answers[i].getText().isEmpty()) {
                        answers[i].draw(MyGdxGame.batch, 1);
                        MyGdxGame.batch.draw(emotionIcons[emotionIconsIndexes[i]], emoIconPostions[i].x, emoIconPostions[i].y, emotionIconWidth, emotionIconHeight);
                    }
                for (Label label : answers)
                    if (!Objects.equals(label, ""))
                        label.draw(MyGdxGame.batch, 1);
            }

            characterLabel.draw(MyGdxGame.batch, 1);
        }
    }

    public void dispose() {
    }

    public boolean getIsShowDialog() {
        return isShowDialog;
    }

    public void nextDialog(String dialogName) {
        dialogs = getDialogs(dialogName);
        isShowDialog = true;
        subDialogCounter = 0;

        nextDialogPart();
    }

    public void nextDialogPart() {
        dialogLabel.setText("");
        for (Label answer : answers)
            answer.setText(null);

        if (hasQueue[currentEmoIndex]) {
            subDialogCounter--;
            isAnswerForm = false;
            if (queueIndex < dialogs.get(0).get(subDialogCounter).get(0).get(currentEmoIndex).get(0).size) {
                String currentCharacterName = dialogs.get(0).get(subDialogCounter).get(0).get(currentEmoIndex).get(0).get(queueIndex).name;

                if (Objects.equals(currentCharacterName, "sad_q")) {
                    currentEmoIndex = checkMaskPhrase(currentCharacterName.split("_")[0]);
                    hasQueue[currentEmoIndex] = true;
                    queueIndex = 0;
                    currentCharacterName = dialogs.get(0).get(subDialogCounter).get(0).get(currentEmoIndex).get(0).get(queueIndex).name;
                    player.getMask().changeMask(currentEmoIndex);

                    if (Objects.equals(currentCharacterName.substring(0, currentCharacterName.length() - 2), "Костя")) {
                        characterLabel.setText("Костя");
                        kostyaEmotionIndex = Integer.parseInt(currentCharacterName.substring(currentCharacterName.length() - 1));
                    } else
                        characterLabel.setText("Алиса");

                    dialogLabel.setText(dialogs.get(0).get(subDialogCounter).get(0).get(currentEmoIndex).get(0).getString(queueIndex++));
                    isItqueue = true;
                }
                else {
                    if (Objects.equals(currentCharacterName.substring(0, currentCharacterName.length() - 2), "Костя")) {
                        characterLabel.setText("Костя");
                        kostyaEmotionIndex = Integer.parseInt(currentCharacterName.substring(currentCharacterName.length() - 1));
                    } else
                        characterLabel.setText("Алиса");

                    dialogLabel.setText(dialogs.get(0).get(subDialogCounter).get(0).get(currentEmoIndex).get(0).getString(queueIndex++));
                    isItqueue = true;
                }
            } else {
                for (int i = 0; i < hasQueue.length; i++)
                    hasQueue[i] = false;
//                speakingCharacter = dialogs.get(0).get(subDialogCounter).name.substring(0, dialogs.get(0).get(subDialogCounter).name.length() - 1);
                isItqueue = false;
                subDialogCounter++;
                if (Objects.equals(speakingCharacter, "finish dialog"))
                    finishDialog();
            }
        }

        if (!isItqueue) {
            speakingCharacter = dialogs.get(0).get(subDialogCounter).name.substring(0, dialogs.get(0).get(subDialogCounter).name.length() - 1);
            if (!Objects.equals(speakingCharacter, "finish dialog")) {
                if (Objects.equals(speakingCharacter, "Костя") || Objects.equals(speakingCharacter, "Мальчик с фотографии")) {
                    isAnswerForm = false;
                    kostyaEmotionIndex = Integer.parseInt(dialogs.get(0).get(subDialogCounter).name.substring(dialogs.get(0).get(subDialogCounter).name.length() - 1)) - 1;
                    characterLabel.setText(speakingCharacter);
                    dialogLabel.setText(dialogs.get(0).getString(subDialogCounter));

                    subDialogCounter++;
                } else {
                    if (speakingCharacter.contains("_"))
                        switch (speakingCharacter.split("_")[1]) {
                            case "anger":
                                player.getMask().changeMask(1);
                                characterLabel.setText(speakingCharacter.split("_")[0]);
                                break;
                            case "sad":
                                player.getMask().changeMask(0);
                                characterLabel.setText(speakingCharacter.split("_")[0]);
                                break;
                            case "neutral":
                                System.out.println("FUCK");
                                break;
                            default:
                                break;
                        }
                    else
                        characterLabel.setText(speakingCharacter);

                    if (dialogs.get(0).get(subDialogCounter).size != 0) {
                        isAnswerForm = true;
                        int size = dialogs.get(0).get(subDialogCounter).get(0).size;
                        int maskInd = -1;
                        String phraseName;
                        for (int i = 0; i < size; i++) {
                            phraseName = dialogs.get(0).get(subDialogCounter).get(0).get(i).name;
                            if (phraseName.contains("_q")) {
                                maskInd = checkMaskPhrase(phraseName.substring(0, phraseName.length() - 2));
                                if (player.getMask().getTakenMasks()[maskInd]) {
                                    answers[i].setText(dialogs.get(0).get(subDialogCounter).get(0).get(i).get(0).getString(0));
                                    emotionIconsIndexes[i] = maskInd;
                                    hasQueue[i] = true;
                                    queueIndex = 1;
                                }
                            } else {
                                maskInd = checkMaskPhrase(phraseName);
                                if (player.getMask().getTakenMasks()[maskInd]) {
                                    answers[i].setText(dialogs.get(0).get(subDialogCounter).get(0).getString(i));
                                    emotionIconsIndexes[i] = maskInd;
                                }
                            }
                        }
                    } else {
                        dialogLabel.setText(dialogs.get(0).getString(subDialogCounter));
                    }
                    subDialogCounter++;
                }
            } else {
                finishDialog();
            }
        } else
            subDialogCounter++;
    }

    public boolean getIsAnswerForm() {
        return isAnswerForm;
    }

    public Vector2[] getEmoIconPostions() {
        return emoIconPostions;
    }

    public Label[] getAnswers() {
        return answers;
    }

    public void setDialogIndex(int index) {
        dialogIndex = index;
    }

    public void phraseClicked(int index) {
        currentEmoIndex = emotionIconsIndexes[index];
        player.getMask().changeMask(currentEmoIndex);
        dialogIndex = -1;
        nextDialogPart();
    }

    private int checkMaskPhrase(String phraseEmotion) {
        switch (phraseEmotion) {
            case "sad":
                return 0;
            case "anger":
                return 1;
            default:
                return -1;
        }
    }

    private void finishDialog() {
        DarkBackground.setIsDark(false);
        isShowDialog = false;
    }
}

