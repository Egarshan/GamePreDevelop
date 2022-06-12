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
    private Hud hud;

    private ExitConfirm exitConfirm;
    private Vector2 worldCords;

    public CorridorEventListener(MyGdxGame game, CorridorScene corridorScene, Player player, Diary diary, Hud hud, ExitConfirm exitConfirm, Inventory inventory) {
        this.game = game;
        this.corridorScene = corridorScene;
        this.player = player;
        this.diary = diary;
        this.inventory = inventory;
        this.hud = hud;
        this.exitConfirm = exitConfirm;
        worldCords = new Vector2();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                exitConfirm.setDraw(true);
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
        /*System.out.println(character);
        if(character == '3') {
            exitConfirm.setDraw(true);
        }
        if(character == '4' && !Data.getPrefs().getBoolean("dialog_1")) {
            corridorScene.getDialogs().showDialog("dialog_1");
        }*/
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(!corridorScene.getDialogs().isShowDialog() && !exitConfirm.isDraw()) {
            if(diary.isShowDiary()) {
                if(screenX >= MyGdxGame.WIDTH/2f) {
                    diary.setN(diary.getN()+1);
                } else {
                    diary.setN(diary.getN()-1);
                }
            }
            worldCords.set(CorridorScene.getViewport1().unproject(new Vector2(screenX, screenY)));
            if (!player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() && !hud.isCursorOnSmth()) {
                player.setDestination((int) worldCords.x, (int) Math.min(worldCords.y, 200));
            }

            //hammer
            if (checkCursorPosition(corridorScene.getItems()[5].getPosition(), corridorScene.getItems()[5].getWidth(), corridorScene.getItems()[5].getHeight())) {
                corridorScene.getReactions().setReaction("hammer", Player.getMask());
                player.setReactionName("hammer");

                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (checkCursorPosition(corridorScene.getItems()[6].getPosition(), corridorScene.getItems()[6].getWidth(), corridorScene.getItems()[6].getHeight())) {
                corridorScene.getReactions().setReaction("mask", Player.getMask());
                player.setReactionName("mask");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (checkCursorPosition(corridorScene.getItems()[2].getPosition(), corridorScene.getItems()[2].getWidth(), corridorScene.getItems()[2].getHeight())) {
                corridorScene.getReactions().setReaction("rubbish_bin");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (worldCords.x >= 1985 && worldCords.x <= 2055 && worldCords.y >= 210 && worldCords.y <= 420) {
                corridorScene.getReactions().setReaction("flower_1");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (worldCords.x >= 2150 && worldCords.x <= 2240 && worldCords.y >= 215 && worldCords.y <= 440) {
                corridorScene.getReactions().setReaction("flower_2");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (worldCords.x >= 1135 && worldCords.x <= 1210 && worldCords.y >= 830 && worldCords.y <= 890) {
                corridorScene.getReactions().setReaction("clock");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (worldCords.x >= 2240 && worldCords.x <= 2400 && worldCords.y >= 570 && worldCords.y <= 730) {
                corridorScene.getReactions().setReaction("fire_extinguisher");
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
            }
            if (worldCords.x >= 1830 && worldCords.x <= 2230 && worldCords.y >= 510 && worldCords.y <= 800) {
                corridorScene.getReactions().setReaction("honor_board", Player.getMask());
                return true;
            } else {
                corridorScene.getReactions().cancelReaction();
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
            if (worldCords.x >= 706 && worldCords.x <= 980 && worldCords.y >= 200 && worldCords.y <= 800) {
                corridorScene.getReactions().setReaction("room_door_1", Player.getMask());
            }
            if (worldCords.x >= 1040 && worldCords.x <= 1300 && worldCords.y >= 200 && worldCords.y <= 800) {
                corridorScene.getReactions().setReaction("room_door_2", Player.getMask());
            }
            if (worldCords.x >= 1370 && worldCords.x <= 1620 && worldCords.y >= 200 && worldCords.y <= 800) {
                corridorScene.getReactions().setReaction("room_door_3", Player.getMask());
            }
        }
        if(corridorScene.getDialogs().isShowDialog()) {
            corridorScene.getDialogs().nextText();
        }
        if(exitConfirm.isDraw()) {
            if (exitConfirm.isDraw() && checkCursorPosition(exitConfirm.getLeftButtonPosition(), exitConfirm.getLeftButtonWidth(), exitConfirm.getLeftButtonHeight())) {
                exitConfirm.setTouchedLeftButton(true);
                return true;
            }
            if (exitConfirm.isDraw() && checkCursorPosition(exitConfirm.getRightButtonPosition(), exitConfirm.getRightButtonWidth(), exitConfirm.getRightButtonHeight())) {
                exitConfirm.setTouchedRightButton(true);
                return true;
            }
        }
        exitConfirm.setTouchedRightButton(false);
        exitConfirm.setTouchedLeftButton(false);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        worldCords.set(CorridorScene.getViewport1().unproject(new Vector2(screenX, screenY)));
        if(!diary.isShowDiary() && !player.getWalk() && hud.isTouchedDownBag() && checkCursorPosition(hud.getBagPosition(), hud.getBagWidth(), hud.getBagHeight())) {
            hud.setTouchedDownBag(false);
            corridorScene.noiseBackground.setIsNoise(!corridorScene.noiseBackground.getIsNoise());
            inventory.setShowInventory(!inventory.isShowInventory());
        }
        if(!inventory.isShowInventory() && hud.isTouchedDownDiary() && !player.getWalk() && checkCursorPosition(hud.getDiaryPosition(), hud.getDiaryWidth(), hud.getDiaryHeight())) {
            hud.setTouchedDownDiary(false);
            diary.setShowDiary(!diary.isShowDiary());
        }
        if(hud.isTouchedDownMask() && checkCursorPosition(hud.getMaskPosition(), hud.getMaskWidth(), hud.getMaskHeight())) {
            hud.setTouchedDownMask(false);
        }
        if(hud.isTouchedDownPhoto() && checkCursorPosition(hud.getPhotoPosition(), hud.getPhotoWidth(), hud.getPhotoHeight())) {
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

        //hammer
        if(!corridorScene.getItems()[5].getIsTaken() && checkCursorPosition(corridorScene.getItems()[5].getPosition(),corridorScene.getItems()[5].getWidth(),
                corridorScene.getItems()[5].getHeight()) && worldCords.y <= corridorScene.getItems()[5].getPosition().y + corridorScene.getItems()[5].getHeight()) {
            if (!MyGdxGame.curIsHand && !player.getHit() && !player.getTake() && !diary.isShowDiary() && !inventory.isShowInventory() && !hud.isCursorOnSmth())
                MyGdxGame.changeCursor();
            return true;
        }
        else if (MyGdxGame.curIsHand)
            MyGdxGame.changeCursor();

          if(checkCursorPosition(hud.getBagPosition(), hud.getBagWidth(), hud.getBagHeight())) {
            hud.setCursorOnBag(true);
            return true;
        }
        if(checkCursorPosition(hud.getDiaryPosition(), hud.getDiaryWidth(), hud.getDiaryHeight())) {
            hud.setCursorOnDiary(true);
            return true;
        }
        if(checkCursorPosition(hud.getMaskPosition(), hud.getMaskWidth(), hud.getMaskHeight())) {
            hud.setCursorOnMask(true);
            return true;
        }
        if(checkCursorPosition(hud.getPhotoPosition(), hud.getPhotoWidth(), hud.getPhotoHeight())) {
            hud.setCursorOnPhoto(true);
            return true;
        }
        if(checkCursorPosition(exitConfirm.getLeftButtonPosition(), exitConfirm.getLeftButtonWidth(), exitConfirm.getLeftButtonHeight()))  {
            exitConfirm.setCursorOnLeftButton(true);
            return true;
        }
        if(checkCursorPosition(exitConfirm.getRightButtonPosition(), exitConfirm.getRightButtonWidth(), exitConfirm.getRightButtonHeight()))  {
            exitConfirm.setCursorOnRightButton(true);
            return true;
        }

        //HERE
        for (InventoryItem invItem: inventory.getInventoryItems()) {
            if (inventory.isShowInventory() && checkCursorPosition(invItem.getPosition(), inventory.getCellWidth(), inventory.getCellHeight())
                    && corridorScene.getItems()[invItem.getID()].getIsTaken()) {
                inventory.setCellLightPosition(invItem.getPosition());
                inventory.setCursorOnIcon(true);
                return true;
            }
            else continue;
        }


        hud.setCursorOnBag(false);
        hud.setCursorOnDiary(false);
        hud.setCursorOnMask(false);
        hud.setCursorOnPhoto(false);
        exitConfirm.setCursorOnLeftButton(false);
        exitConfirm.setCursorOnRightButton(false);
        inventory.setCursorOnIcon(false);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean checkCursorPosition(Vector2 position, float width, float height) {
        if(worldCords.x >= position.x && worldCords.x <= position.x + width && worldCords.y >= position.y && worldCords.y <= position.y + height)
            return true;
        else return false;
    }
}
