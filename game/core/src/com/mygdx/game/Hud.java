package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Corridor.CorridorScene;
import com.mygdx.game.Corridor.GameCamera;
import com.mygdx.game.Corridor.Player;

public class Hud {

    private TextureRegion diary1, diary2;
    private TextureRegion mask1, mask2;
    private TextureRegion bag1, bag2;
    private TextureRegion photo1, photo2;
    private Vector2 diaryPosition, maskPosition, bagPosition, photoPosition;
    private float diaryWidth, diaryHeight;
    private float maskWidth, maskHeight;
    private float bagCloseWidth, bagCloseHeight, bagOpenWidth, bagOpenHeight;
    private float photoWidth, photoHeight;
    private boolean touchedDownDiary, touchedDownMask, touchedDownBag, touchedDownPhoto;
    private boolean isCursorOnDiary, isCursorOnMask, isCursorOnBag, isCursorOnPhoto;
    private GameCamera camera;
    private Player player;

    private boolean isShowMask, isShowPhoto;

    public Hud(GameCamera camera, Player player) {
        this.camera = camera;
        this.player = player;
        diary1 = ResourcesClass.getResources().get(9)[0];
        diary2 = ResourcesClass.getResources().get(9)[1];
        bag1 = ResourcesClass.getResources().get(9)[2];
        bag2 = ResourcesClass.getResources().get(9)[3];
        photo1 = ResourcesClass.getResources().get(9)[4];
        photo2 = ResourcesClass.getResources().get(9)[5];
        mask1 = ResourcesClass.getResources().get(9)[6];
        mask2 = ResourcesClass.getResources().get(9)[7];

        bagCloseWidth = MyGdxGame.WIDTH/20f;
        bagCloseHeight = bagCloseWidth/1.0096f;
        bagOpenWidth = bagCloseWidth*1.15f;
        bagOpenHeight = bagOpenWidth/1.075f;
        diaryWidth = bagCloseWidth;
        diaryHeight = diaryWidth*1.173f;
        maskWidth = bagCloseWidth;
        maskHeight = maskWidth;
        photoWidth = bagCloseWidth;
        photoHeight = photoWidth/1.0297f;

        bagPosition = new Vector2(camera.getCameraPosition().x-29*MyGdxGame.WIDTH/60f, MyGdxGame.HEIGHT-MyGdxGame.HEIGHT/20f-bagCloseHeight);
        diaryPosition = new Vector2(bagPosition.x, bagPosition.y-bagCloseHeight-bagCloseHeight/3f);
        maskPosition = new Vector2(diaryPosition.x, diaryPosition.y-bagCloseHeight-diaryHeight/3f);
        photoPosition = new Vector2(maskPosition.x, maskPosition.y-bagCloseHeight-maskHeight/3f);

        touchedDownBag = false;
        touchedDownDiary = false;
        touchedDownMask = false;
        touchedDownPhoto = false;

        isCursorOnBag = false;
        isCursorOnDiary = false;
        isCursorOnMask = false;
        isCursorOnPhoto = false;

        isShowMask = false;
        isShowPhoto = false;

    }

    public boolean getIsShowMask() {
        return isShowMask;
    }

    public boolean getIsShowPhoto() {
        return isShowPhoto;
    }

    public void setIsShowMask(boolean b) {
        isShowMask = b;
    }

    public void setIsShowPhoto(boolean b) {
        isShowPhoto = b;
    }

    public void update(float deltaTime) {
        bagPosition.set(camera.getCameraPosition().x-29*MyGdxGame.WIDTH/60f, bagPosition.y);
        diaryPosition.set(bagPosition.x, diaryPosition.y);
        maskPosition.set(bagPosition.x, maskPosition.y);
        photoPosition.set(bagPosition.x, photoPosition.y);
    }

    public void render() {
        if(isCursorOnBag) {
            if(touchedDownBag) {
                MyGdxGame.batch.draw(bag2, bagPosition.x-bagOpenWidth/6f, bagPosition.y-bagOpenHeight/6f, bagOpenWidth+bagOpenWidth/3f, bagOpenHeight+bagOpenHeight/3f);
            } else {
                MyGdxGame.batch.draw(bag2, bagPosition.x, bagPosition.y, bagOpenWidth, bagOpenHeight);
            }
        } else {
            MyGdxGame.batch.draw(bag1, bagPosition.x, bagPosition.y, bagCloseWidth, bagCloseHeight);
        }
        if(isCursorOnDiary) {
            if(touchedDownDiary) {
                MyGdxGame.batch.draw(diary2, diaryPosition.x-diaryWidth/6f, diaryPosition.y-diaryHeight/6f, diaryWidth+diaryWidth/3f, diaryHeight+diaryHeight/3f);
            } else {
                MyGdxGame.batch.draw(diary2, diaryPosition.x, diaryPosition.y, diaryWidth, diaryHeight);
            }
        } else {
            MyGdxGame.batch.draw(diary1, diaryPosition.x, diaryPosition.y, diaryWidth, diaryHeight);
        }

        if (isShowMask) {
            if(isCursorOnMask) {
                if(touchedDownMask) {
                    MyGdxGame.batch.draw(mask2, maskPosition.x-maskWidth/6f, maskPosition.y-maskHeight/6f, maskWidth+maskWidth/3f, maskHeight+maskHeight/3f);
                } else {
                    MyGdxGame.batch.draw(mask2, maskPosition.x, maskPosition.y, maskWidth, maskHeight);
                }
            } else {
                MyGdxGame.batch.draw(mask1, maskPosition.x, maskPosition.y, maskWidth, maskHeight);
            }
        }

        if (isShowPhoto) {
            if(isCursorOnPhoto) {
                if(touchedDownPhoto) {
                    MyGdxGame.batch.draw(photo2, photoPosition.x-photoWidth/6f, photoPosition.y-photoHeight/6f, photoWidth+photoWidth/3f, photoHeight+photoHeight/3f);
                } else {
                    MyGdxGame.batch.draw(photo2, photoPosition.x, photoPosition.y, photoWidth, photoHeight);
                }
            } else {
                MyGdxGame.batch.draw(photo1, photoPosition.x, photoPosition.y, photoWidth, photoHeight);
            }
        }
    }

    public void dispose() {
    }
    public Vector2 getBagPosition() {
        return bagPosition;
    }

    public Vector2 getDiaryPosition() {
        return diaryPosition;
    }

    public Vector2 getMaskPosition() {
        return maskPosition;
    }

    public Vector2 getPhotoPosition() {
        return photoPosition;
    }

    public float getDiaryWidth() {
        return diaryWidth;
    }

    public float getDiaryHeight() {
        return diaryHeight;
    }

    public float getMaskWidth() {
        return maskWidth;
    }

    public float getMaskHeight() {
        return maskHeight;
    }

    public float getBagWidth() {
        return bagCloseWidth;
    }

    public float getBagHeight() {
        return bagCloseHeight;
    }

    public float getPhotoWidth() {
        return photoWidth;
    }

    public float getPhotoHeight() {
        return photoHeight;
    }

    public void setTouchedDownDiary(boolean touchedDownDiary) {
        this.touchedDownDiary = touchedDownDiary;
    }

    public void setTouchedDownMask(boolean touchedDownMask) {
        this.touchedDownMask = touchedDownMask;
    }

    public void setTouchedDownBag(boolean touchedDownBag) {
        this.touchedDownBag = touchedDownBag;
    }

    public void setTouchedDownPhoto(boolean touchedDownPhoto) {
        this.touchedDownPhoto = touchedDownPhoto;
    }

    public boolean isTouchedDownDiary() {
        return touchedDownDiary;
    }

    public boolean isTouchedDownMask() {
        return touchedDownMask;
    }

    public boolean isTouchedDownBag() {
        return touchedDownBag;
    }

    public boolean isTouchedDownPhoto() {
        return touchedDownPhoto;
    }

    public void setCursorOnDiary(boolean cursorOnDiary) {
        isCursorOnDiary = cursorOnDiary;
    }

    public void setCursorOnMask(boolean cursorOnMask) {
        isCursorOnMask = cursorOnMask;
    }

    public void setCursorOnBag(boolean cursorOnBag) {
        isCursorOnBag = cursorOnBag;
    }

    public void setCursorOnPhoto(boolean cursorOnPhoto) {
        isCursorOnPhoto = cursorOnPhoto;
    }

    public boolean isCursorOnSmth() {
        return isCursorOnBag || isCursorOnDiary || isCursorOnMask || isCursorOnPhoto;
    }
}
