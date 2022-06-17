package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MaskSelector {
    private boolean showMaskSelector;

    private TextureRegion selectorArea;
    private TextureRegion[] selectorButtons, maskTextures;

    private Vector2 selectorAreaPosition;

    private float selectorAreaWidth, selectorAreaHeight, buttonSize, maskSize;
    private float[][] buttonsPositionsFixes;

    private Vector2[] maskPositions;

    private Camera camera;

    private Mask playerMask;

    private int activeBtnInd;

    public MaskSelector(Camera camera, Mask playerMask) {
        this.camera = camera;
        this.playerMask = playerMask;

        selectorArea = ResourcesClass.getResources().get(22)[0];

        selectorButtons = new TextureRegion[2];
        selectorButtons[0] = ResourcesClass.getResources().get(23)[0];  //кнопка_печаль
        selectorButtons[1] = ResourcesClass.getResources().get(23)[1];  //кнопка_гнев

        maskTextures = new TextureRegion[2];
        maskTextures[0] = ResourcesClass.getResources().get(24)[0];
        maskTextures[1] = ResourcesClass.getResources().get(24)[1];

        selectorAreaHeight = MyGdxGame.WIDTH/1.77f /1.35f;
        selectorAreaWidth = selectorAreaHeight;

        buttonSize = 340;
        maskSize = 175;

        selectorAreaPosition = new Vector2(camera.position.x- selectorAreaWidth /2f, MyGdxGame.HEIGHT/2f- selectorAreaHeight /2f);

        maskPositions = new Vector2[2];
        maskPositions[0] = selectorAreaPosition;
        maskPositions[1] = selectorAreaPosition;

        buttonsPositionsFixes = new float[2][2];

        buttonsPositionsFixes[0][0] =  238;
        buttonsPositionsFixes[0][1] = -56;

        buttonsPositionsFixes[1][0] = -45;
        buttonsPositionsFixes[1][1] = 230;

        activeBtnInd = -1;
    }

    public void update(float deltaTime) {
        selectorAreaPosition = new Vector2(camera.position.x- selectorAreaWidth /2f, MyGdxGame.HEIGHT/2f- selectorAreaHeight /2f);

        maskPositions[0] = new Vector2(camera.position.x- selectorAreaWidth /2f + 320, MyGdxGame.HEIGHT/2f- selectorAreaHeight /2f + 20);
        maskPositions[1] = new Vector2(camera.position.x- selectorAreaWidth /2f + 20, MyGdxGame.HEIGHT/2f- selectorAreaHeight /2f + 320);
    }

    public void render() {
        if (showMaskSelector) {
            MyGdxGame.batch.draw(selectorArea, selectorAreaPosition.x, selectorAreaPosition.y, selectorAreaWidth, selectorAreaHeight);

            renderActiveButton();
            renderMasks();
        }
    }

    public boolean isShowMaskSelector() {
        return showMaskSelector;
    }

    public void setShowMaskSelector(boolean b) {
        showMaskSelector = b;
    }

    public TextureRegion[] getMaskTextures() {
        return maskTextures;
    }

    public void setActiveBtn(int ind) {
        activeBtnInd = ind;
    }

    public Vector2 getMaskPosition(int ind) {
        return maskPositions[ind];
    }

    public float getMaskSize() {
        return maskSize;
    }

    private void renderActiveButton() {
        if (activeBtnInd != -1)
            if (playerMask.getTakenMasks()[activeBtnInd])
                MyGdxGame.batch.draw(selectorButtons[activeBtnInd], selectorAreaPosition.x + buttonsPositionsFixes[activeBtnInd][0], selectorAreaPosition.y +
                        buttonsPositionsFixes[activeBtnInd][1], buttonSize, buttonSize);
    }

    private void renderMasks() {
        for (int i = 0; i < maskTextures.length; i++) {
            if (playerMask.getTakenMasks()[i]) {
                if (activeBtnInd == i)
                    MyGdxGame.batch.draw(maskTextures[i], maskPositions[i].x - 20, maskPositions[i].y - 20, maskSize + 40, maskSize + 40);
                else
                    MyGdxGame.batch.draw(maskTextures[i], maskPositions[i].x, maskPositions[i].y, maskSize, maskSize);
            }
        }
    }
}
