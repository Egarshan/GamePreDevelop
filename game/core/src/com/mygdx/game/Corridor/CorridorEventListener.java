package com.mygdx.game.Corridor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.*;

public class CorridorEventListener implements InputProcessor {

    private MyGdxGame game;
    private CorridorScene corridorScene;
    private Player player;
    private Diary diary;
    private Inventory inventory;
    private MaskSelector maskSelector;
    private Hud hud;
    Dialog dialog;

    private ExitConfirm exitConfirm;
    private Vector2 worldCords;

    private boolean cursorOnInteractive, needGlowCheck;

    public CorridorEventListener(MyGdxGame game, CorridorScene corridorScene, Player player, Diary diary, Hud hud, ExitConfirm exitConfirm, Inventory inventory, MaskSelector maskSelector, Dialog dialog) {
        this.game = game;
        this.corridorScene = corridorScene;
        this.player = player;
        this.diary = diary;
        this.inventory = inventory;
        this.maskSelector = maskSelector;
        this.hud = hud;
        this.exitConfirm = exitConfirm;
        this.dialog = dialog;
        worldCords = new Vector2();

        cursorOnInteractive = false;
        needGlowCheck = true;

        AudioPlayer.getMusic()[0].setLooping(true);
        AudioPlayer.getMusic()[0].play();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                if (!corridorScene.finishWindow.isDraw()) {
                    MyGdxGame.changeCursor("simple");
                    exitConfirm.setDraw(true);
                }
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (!exitConfirm.isDraw() && !corridorScene.finishWindow.isDraw() && !diary.isShowDiary() && !dialog.getIsShowDialog() && !inventory.isShowInventory() && !maskSelector.isShowMaskSelector()) {
            if (diary.isShowDiary()) {
                if (screenX >= MyGdxGame.WIDTH / 2f) {
                    diary.setN(diary.getN() + 1);
                } else {
                    diary.setN(diary.getN() - 1);
                }
            }
            worldCords.set(CorridorScene.getViewport1().unproject(new Vector2(screenX, screenY)));
            if (!player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() && !maskSelector.isShowMaskSelector() &&
                    !dialog.getIsShowDialog() && !hud.isCursorOnSmth()) {
                player.setDestination((int) worldCords.x, (int) Math.min(worldCords.y, 200));
            }

            //exit_door
            if (checkCursorPosition(corridorScene.getItems()[0].getPosition(), corridorScene.getItems()[0].getWidth(), corridorScene.getItems()[0].getHeight())) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("keyGlow"))
                        corridorScene.getReactions().setReaction("non-use");
                    else
                        corridorScene.getReactions().setReaction("photo");
                        player.setReactionName("exit_door");
                    return true;
                }

                if (!corridorScene.getDoorIsOpen())
                    corridorScene.getReactions().setReaction("exit_door");
                else {
                    corridorScene.getReactions().setReaction("photo");
                    player.setReactionName("finish_game");
                }
                return true;
            }
            else {
                corridorScene.getReactions().cancelReaction();
            }

            //hammer
            if (checkCursorPosition(corridorScene.getItems()[5].getPosition(), corridorScene.getItems()[5].getWidth(), corridorScene.getItems()[5].getHeight()) && corridorScene.getMeetIsDone()) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                if (!corridorScene.getItems()[5].getIsTaken()) {
                    corridorScene.getReactions().setReaction("hammer", player.getMask().getCurrentMask());
                    player.setReactionName("hammer");
                }
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //mask_anger
            if (checkCursorPosition(corridorScene.getItems()[3].getPosition(), corridorScene.getItems()[3].getWidth(), corridorScene.getItems()[3].getHeight())) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                if (!corridorScene.getItems()[3].getIsTaken()) {
                    corridorScene.getReactions().setReaction("mask", player.getMask().getCurrentMask());
                    player.setReactionName("mask");
                }
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //photo
            if (checkCursorPosition(corridorScene.getItems()[4].getPosition(), corridorScene.getItems()[4].getWidth(), corridorScene.getItems()[4].getHeight())) {
                if (!corridorScene.getMeetIsDone()) {
                    corridorScene.getReactions().setReaction("photo");
                    player.setReactionName("photo");

                    return true;
                }
                if (corridorScene.getWindowIsBroken()) {
                    if (MyGdxGame.cursorIsItem) {
                        if (!isItemCursorPressIsCorrect("no"))
                            corridorScene.getReactions().setReaction("non-use");
                        MyGdxGame.changeCursor("simple");
                        return true;
                    }

                    corridorScene.getReactions().setReaction("photo");
                    player.setReactionName("photo");
                    return true;
                }
            } else
                corridorScene.getReactions().cancelReaction();

            //rubbish_bin
            if (checkCursorPosition(corridorScene.getItems()[2].getPosition(), corridorScene.getItems()[2].getWidth(), corridorScene.getItems()[2].getHeight())) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("rubbish_bin");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //flower_with_key
            if (worldCords.x >= 1985 && worldCords.x <= 2055 && worldCords.y >= 210 && worldCords.y <= 420) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                if (corridorScene.getKeyIsAvailable() && !corridorScene.getItems()[8].getIsTaken()) {
                    corridorScene.getReactions().setReaction("key");
                    player.setReactionName("key");
                    return true;
                }

                corridorScene.getReactions().setReaction("flower_1");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //flower_2
            if (worldCords.x >= 2150 && worldCords.x <= 2240 && worldCords.y >= 215 && worldCords.y <= 440) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("flower_2");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //clock
            if (worldCords.x >= 1135 && worldCords.x <= 1210 && worldCords.y >= 830 && worldCords.y <= 890) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("clock");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //fire_extinguisher
            if (worldCords.x >= 2240 && worldCords.x <= 2400 && worldCords.y >= 570 && worldCords.y <= 730) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("fire_extinguisher");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //honor_board
            if (worldCords.x >= 1830 && worldCords.x <= 2230 && worldCords.y >= 510 && worldCords.y <= 800) {
                if (MyGdxGame.cursorIsItem) {
                    if (isItemCursorPressIsCorrect("hammerGlow") && !corridorScene.getWindowIsBroken()) {
                        if (player.getMask().getCurrentMask() == "anger") {
                            corridorScene.setWindowIsBroken(true);
                            player.setReactionName("hammer_honor_board");
                        }
                        corridorScene.getReactions().setReaction("hammer_honor_board", player.getMask().getCurrentMask());
                    } else if (!isItemCursorPressIsCorrect("hammerGlow") || corridorScene.getWindowIsBroken())
                        corridorScene.getReactions().setReaction("non-use");

                    MyGdxGame.changeCursor("simple");
                } else if (!corridorScene.getWindowIsBroken()) {
                    corridorScene.getReactions().setReaction("honor_board", player.getMask().getCurrentMask());
                    player.setReactionName("honor_board");
                }
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }

            //room_door_1
            if (worldCords.x >= 706 && worldCords.x <= 980 && worldCords.y >= 200 && worldCords.y <= 800) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("room_door_1", player.getMask().getCurrentMask());
                player.setReactionName("room_door_1");
            }

            //room_door_2
            if (worldCords.x >= 1040 && worldCords.x <= 1300 && worldCords.y >= 200 && worldCords.y <= 800) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("room_door_2", player.getMask().getCurrentMask());
                player.setReactionName("room_door_2");
            }

            //room_door_3
            if (worldCords.x >= 1370 && worldCords.x <= 1620 && worldCords.y >= 200 && worldCords.y <= 800) {
                if (MyGdxGame.cursorIsItem) {
                    if (!isItemCursorPressIsCorrect("no"))
                        corridorScene.getReactions().setReaction("non-use");
                    MyGdxGame.changeCursor("simple");
                    return true;
                }

                corridorScene.getReactions().setReaction("room_door_3", player.getMask().getCurrentMask());
                player.setReactionName("room_door_3");
            }
        }
        if (checkCursorPosition(hud.getBagPosition(), hud.getBagWidth(), hud.getBagHeight())) {
            hud.setTouchedDownBag(true);
        }
        if (checkCursorPosition(hud.getDiaryPosition(), hud.getDiaryWidth(), hud.getDiaryHeight())) {
            hud.setTouchedDownDiary(true);
        }
        if (checkCursorPosition(hud.getMaskPosition(), hud.getMaskWidth(), hud.getMaskHeight())) {
            hud.setTouchedDownMask(true);
        }
        if (checkCursorPosition(hud.getPhotoPosition(), hud.getPhotoWidth(), hud.getPhotoHeight())) {
            hud.setTouchedDownPhoto(true);
        }

        if (maskSelector.isShowMaskSelector()) {
            checkMaskPress();
        }

        //Dialog_form
        if (dialog.getIsShowDialog()) {
            if (!dialog.getIsAnswerForm())
                dialog.nextDialogPart();
            else {
                if (dialog.getIsShowDialog() && dialog.getIsAnswerForm()) {
                    for (int i = 0; i < dialog.getEmoIconPostions().length; i++)
                        if (checkCursorPosition(dialog.getEmoIconPostions()[i], 1221, 81)) {
                            dialog.phraseClicked(i);
                            return true;
                        }
                }
            }
        }

        //Exit_menu
        if (exitConfirm.isDraw()) {
            if (checkCursorPosition(exitConfirm.getLeftButtonPosition(), exitConfirm.getLeftButtonWidth(), exitConfirm.getLeftButtonHeight())) {
                exitConfirm.setTouchedLeftButton(true);
                return true;
            }
            if (checkCursorPosition(exitConfirm.getRightButtonPosition(), exitConfirm.getRightButtonWidth(), exitConfirm.getRightButtonHeight())) {
                exitConfirm.setTouchedRightButton(true);
                return true;
            }
        }

        //Finish_menu
        if (corridorScene.finishWindow.isDraw()) {
            if (checkCursorPosition(corridorScene.finishWindow.getOkButtonPosition(), corridorScene.finishWindow.getOkButtonWidth(), corridorScene.finishWindow.getOkButtonHeight())) {
                corridorScene.finishWindow.setTouchedOkButton(true);
            }
        }

        //Inventory
        if (inventory.isShowInventory()) {
            for (InventoryItem invItem : inventory.getInventoryItems()) {
                if (checkCursorPosition(invItem.getPosition(), inventory.getCellWidth(), inventory.getCellHeight())
                        && corridorScene.getItems()[invItem.getID()].getIsTaken()) {
                    MyGdxGame.changeCursor(invItem.getName());
                    MyGdxGame.cursorIsItem = true;

                    hud.setTouchedDownBag(false);
                    corridorScene.noiseBackground.setIsNoise(!corridorScene.noiseBackground.getIsNoise());
                    inventory.setShowInventory(!inventory.isShowInventory());
                    return true;
                } else continue;
            }
        }

        MyGdxGame.changeCursor("simple");

        exitConfirm.setTouchedRightButton(false);
        exitConfirm.setTouchedLeftButton(false);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        worldCords.set(CorridorScene.getViewport1().unproject(new Vector2(screenX, screenY)));
        if (!dialog.getIsShowDialog() && !maskSelector.isShowMaskSelector() && !diary.isShowDiary() && !player.getWalk() && hud.isTouchedDownBag() && checkCursorPosition(hud.getBagPosition(),
                hud.getBagWidth(), hud.getBagHeight()) && !corridorScene.finishWindow.isDraw()) {
            hud.setTouchedDownBag(false);
            corridorScene.noiseBackground.setIsNoise(!corridorScene.noiseBackground.getIsNoise());
            inventory.setShowInventory(!inventory.isShowInventory());
        }
        if (!dialog.getIsShowDialog() && !maskSelector.isShowMaskSelector() && !inventory.isShowInventory() && hud.isTouchedDownDiary() && !player.getWalk() && checkCursorPosition(hud.getDiaryPosition(),
                hud.getDiaryWidth(), hud.getDiaryHeight()) && !corridorScene.finishWindow.isDraw()) {
            hud.setTouchedDownDiary(false);
            diary.setShowDiary(!diary.isShowDiary());
        }
        if (!dialog.getIsShowDialog() && !inventory.isShowInventory() && !diary.isShowDiary() && !player.getWalk() && hud.isTouchedDownMask() && checkCursorPosition(hud.getMaskPosition(),
                hud.getMaskWidth(), hud.getMaskHeight()) && !corridorScene.finishWindow.isDraw()) {
            hud.setTouchedDownMask(false);
            corridorScene.darkBackground.setIsDark(!corridorScene.darkBackground.getIsDark());
            maskSelector.setShowMaskSelector(!maskSelector.isShowMaskSelector());
        }
        if (hud.isTouchedDownPhoto() && checkCursorPosition(hud.getPhotoPosition(), hud.getPhotoWidth(), hud.getPhotoHeight())) {
            hud.setTouchedDownPhoto(false);
        }
        hud.setTouchedDownBag(false);
        hud.setTouchedDownDiary(false);
        hud.setTouchedDownMask(false);
        hud.setTouchedDownPhoto(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        worldCords.set(CorridorScene.getViewport1().unproject(new Vector2(screenX, screenY)));

        if (maskSelector.isShowMaskSelector())
            checkMaskSelectorMove();

        if (needGlowCheck)
            setCursorGlow(checkCursorGlow(false));

        //hammer
        if (!corridorScene.getItems()[5].getIsTaken() && checkCursorPosition(corridorScene.getItems()[5].getPosition(), corridorScene.getItems()[5].getWidth(),
                corridorScene.getItems()[5].getHeight())) {
            if (!MyGdxGame.cursorIsItem && !MyGdxGame.curIsChanged && !player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() &&
                    !hud.isCursorOnSmth() && !dialog.getIsShowDialog() && corridorScene.getMeetIsDone() && !corridorScene.finishWindow.isDraw()) {
                MyGdxGame.changeCursor("hand");
                return true;
            }
        }
        //flower_with_key
        else if (worldCords.x >= 1985 && worldCords.x <= 2055 && worldCords.y >= 210 && worldCords.y <= 420) {
            if (!player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() && !hud.isCursorOnSmth() && !dialog.getIsShowDialog() && !corridorScene.finishWindow.isDraw()) {
                if (MyGdxGame.cursorIsItem)
                    setCursorGlow(true);
                else if (corridorScene.getKeyIsAvailable() && !MyGdxGame.curIsChanged && !corridorScene.getItems()[8].getIsTaken())
                    MyGdxGame.changeCursor("hand");
                return true;
            }
        }
        //mask_anger
        else if (!corridorScene.getItems()[3].getIsTaken() && checkCursorPosition(corridorScene.getItems()[3].getPosition(), corridorScene.getItems()[3].getWidth(),
                corridorScene.getItems()[3].getHeight())) {
            if (!player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() && !hud.isCursorOnSmth() && !dialog.getIsShowDialog() && !corridorScene.finishWindow.isDraw())
                if (MyGdxGame.cursorIsItem)
                    setCursorGlow(true);
                else if (!MyGdxGame.curIsChanged)
                    MyGdxGame.changeCursor("hand");
            return true;
        }
        //photo
        else if (checkCursorPosition(corridorScene.getItems()[4].getPosition(), corridorScene.getItems()[4].getWidth(),
                corridorScene.getItems()[4].getHeight())) {
            if (!player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() && !hud.isCursorOnSmth() && !dialog.getIsShowDialog() && !corridorScene.finishWindow.isDraw())
                if (MyGdxGame.cursorIsItem)
                    setCursorGlow(true);
                else if (!MyGdxGame.curIsChanged && (!corridorScene.getMeetIsDone() || corridorScene.getWindowIsBroken()))
                    MyGdxGame.changeCursor("hand");
            return true;
        } else if (MyGdxGame.curIsChanged && !MyGdxGame.cursorIsItem) {
            MyGdxGame.changeCursor("simple");
        }

        if (checkCursorPosition(hud.getBagPosition(), hud.getBagWidth(), hud.getBagHeight())) {
            hud.setCursorOnBag(true);
            return true;
        }
        if (checkCursorPosition(hud.getDiaryPosition(), hud.getDiaryWidth(), hud.getDiaryHeight())) {
            hud.setCursorOnDiary(true);
            return true;
        }
        if (checkCursorPosition(hud.getMaskPosition(), hud.getMaskWidth(), hud.getMaskHeight())) {
            hud.setCursorOnMask(true);
            return true;
        }
        if (checkCursorPosition(hud.getPhotoPosition(), hud.getPhotoWidth(), hud.getPhotoHeight())) {
            hud.setCursorOnPhoto(true);
            return true;
        }
        if (checkCursorPosition(exitConfirm.getLeftButtonPosition(), exitConfirm.getLeftButtonWidth(), exitConfirm.getLeftButtonHeight())) {
            exitConfirm.setCursorOnLeftButton(true);
            return true;
        }
        if (checkCursorPosition(exitConfirm.getRightButtonPosition(), exitConfirm.getRightButtonWidth(), exitConfirm.getRightButtonHeight())) {
            exitConfirm.setCursorOnRightButton(true);
            return true;
        }
        if (checkCursorPosition(corridorScene.finishWindow.getOkButtonPosition(), corridorScene.finishWindow.getOkButtonWidth(), corridorScene.finishWindow.getOkButtonHeight())) {
            corridorScene.finishWindow.setCursorOnOkButton(true);
            return true;
        }

        //Inventory
        if (inventory.isShowInventory()) {
            for (InventoryItem invItem : inventory.getInventoryItems()) {
                if (checkCursorPosition(invItem.getPosition(), inventory.getCellWidth(), inventory.getCellHeight())
                        && corridorScene.getItems()[invItem.getID()].getIsTaken()) {
                    inventory.setCellLightPosition(invItem.getPosition());
                    inventory.setCursorOnIcon(true);
                    return true;
                } else continue;
            }
        }

        //Dialog
        if (dialog.getIsShowDialog() && dialog.getIsAnswerForm()) {
            for (int i = 0; i < dialog.getEmoIconPostions().length; i++)
                if (checkCursorPosition(dialog.getEmoIconPostions()[i], 1221, 81) && !dialog.getAnswers()[i].getText().isEmpty()) {
                    dialog.setDialogIndex(i);
                    return true;
                }
            dialog.setDialogIndex(-1);
        }

        hud.setCursorOnBag(false);
        hud.setCursorOnDiary(false);
        hud.setCursorOnMask(false);
        hud.setCursorOnPhoto(false);
        exitConfirm.setCursorOnLeftButton(false);
        exitConfirm.setCursorOnRightButton(false);
        corridorScene.finishWindow.setCursorOnOkButton(false);
        inventory.setCursorOnIcon(false);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private boolean checkCursorPosition(Vector2 position, float width, float height) {
        if (worldCords.x >= position.x && worldCords.x <= position.x + width && worldCords.y >= position.y && worldCords.y <= position.y + height)
            return true;
        else return false;
    }

    private boolean checkMaskSelectorMove() {
        for (int i = 0; i < maskSelector.getMaskTextures().length; i++) {
            if (checkCursorPosition(maskSelector.getMaskPosition(i), maskSelector.getMaskSize(), maskSelector.getMaskSize())) {
                maskSelector.setActiveBtn(i);

                return true;
            }
        }

        maskSelector.setActiveBtn(-1);

        return false;
    }

    private boolean checkMaskPress() {
        for (int i = 0; i < maskSelector.getMaskTextures().length; i++) {
            if (checkCursorPosition(maskSelector.getMaskPosition(i), maskSelector.getMaskSize(), maskSelector.getMaskSize()) &&
                    player.getMask().getTakenMasks()[i]) {
                player.getMask().changeMask(i);

                hud.setTouchedDownMask(false);
                corridorScene.darkBackground.setIsDark(!corridorScene.darkBackground.getIsDark());
                maskSelector.setShowMaskSelector(!maskSelector.isShowMaskSelector());

                return true;
            }
        }

        return false;
    }

    private boolean checkCursorGlow(boolean b) {
        if (!b) {
            if (checkCursorPosition(corridorScene.getItems()[2].getPosition(), corridorScene.getItems()[2].getWidth(), corridorScene.getItems()[2].getHeight()))
                return true;
            if (worldCords.x >= 2150 && worldCords.x <= 2240 && worldCords.y >= 215 && worldCords.y <= 440)
                return true;
            if (worldCords.x >= 1135 && worldCords.x <= 1210 && worldCords.y >= 830 && worldCords.y <= 890)
                return true;
            if (worldCords.x >= 2240 && worldCords.x <= 2400 && worldCords.y >= 570 && worldCords.y <= 730)
                return true;
            if (worldCords.x >= 1830 && worldCords.x <= 2230 && worldCords.y >= 510 && worldCords.y <= 800)
                return true;
            if (worldCords.x >= 706 && worldCords.x <= 980 && worldCords.y >= 200 && worldCords.y <= 800)
                return true;
            if (worldCords.x >= 1040 && worldCords.x <= 1300 && worldCords.y >= 200 && worldCords.y <= 800)
                return true;
            if (worldCords.x >= 1370 && worldCords.x <= 1620 && worldCords.y >= 200 && worldCords.y <= 800)
                return true;
            if (worldCords.x >= 2330 && worldCords.x <= 2991 && worldCords.y >= 153 && worldCords.y <= 854)
                return true;

            return false;
        } else return true;
    }

    private void setCursorGlow(boolean b) {
        if (b && MyGdxGame.cursorIsItem) {
            MyGdxGame.curIsChanged = false;
            MyGdxGame.changeCursor(MyGdxGame.currentCursor + "Glow");
            cursorOnInteractive = true;
        } else
            cursorOnInteractive = false;
        if (!cursorOnInteractive && MyGdxGame.cursorIsItem) {
            if (MyGdxGame.currentCursor.indexOf("G") != -1) {
                MyGdxGame.curIsChanged = false;
                String noGlowCursor = MyGdxGame.currentCursor.substring(0, MyGdxGame.currentCursor.indexOf("G"));
                MyGdxGame.changeCursor(noGlowCursor);
            }
        }
    }

    private boolean isItemCursorPressIsCorrect(String correctCursorName) {
        if (correctCursorName == MyGdxGame.currentCursor)
            return true;
        return false;
    }
}
