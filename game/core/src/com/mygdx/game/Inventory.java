package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Corridor.CorridorScene;

public class Inventory {
    private TextureRegion frame, cellsArea, cellLight, hammerIcon, keyIcon;

    private boolean showInventory;
    private boolean isCursorOnIcon;

    private Vector2 framePosition, cellsAreaPosition;
    private Vector2 cellLightPosition;

    private float frameWidth, frameHeight;
    private float cellsAreaWidth, cellsAreaHeight;

    private float cellWidth, cellHeight;

    private Camera camera;

    private InventoryItem[] inventoryItems;

    public Inventory(Camera camera) {
        this.camera = camera;

        frame = ResourcesClass.getResources().get(18)[0];
        cellsArea = ResourcesClass.getResources().get(18)[1];
        cellLight = ResourcesClass.getResources().get(18)[2];

        hammerIcon = ResourcesClass.getResources().get(19)[0];
        keyIcon = ResourcesClass.getResources().get(19)[1];

        frameWidth = MyGdxGame.WIDTH/1.77f;
        frameHeight = frameWidth /1.55f;

        cellsAreaWidth = frameWidth /1.25f;
        cellsAreaHeight = cellsAreaWidth /1.6f;

        cellWidth = cellsAreaWidth /7.25f;
        cellHeight = cellsAreaHeight /4.6f;

        framePosition = new Vector2(camera.position.x- frameWidth /2f, MyGdxGame.HEIGHT/2f- frameHeight /2f);
        cellsAreaPosition = new Vector2(camera.position.x - cellsAreaWidth /2f, MyGdxGame.HEIGHT/2.2f - cellsAreaHeight /2f);

        inventoryItems = new InventoryItem[2];
        inventoryItems[0] = new InventoryItem(5, new Vector2(cellsAreaPosition.x * 1.008f, cellsAreaPosition.y + cellsAreaHeight - (cellHeight + 9)), "hammer");
        inventoryItems[1] = new InventoryItem(8, new Vector2(cellsAreaPosition.x * 1.008f, cellsAreaPosition.y + cellsAreaHeight - (cellHeight + 9)), "key");

        showInventory = false;
        isCursorOnIcon = false;
    }

    public void update(float deltaTime) {
        framePosition = new Vector2(camera.position.x- frameWidth /2f, MyGdxGame.HEIGHT/2f- frameHeight /2f);
        cellsAreaPosition = new Vector2(camera.position.x- cellsAreaWidth/2f, MyGdxGame.HEIGHT/2.2f - cellsAreaHeight /2f);

        inventoryItems[0].setPosition(new Vector2((camera.position.x - frameWidth/2.51f), (framePosition.y + frameHeight) * 0.715f));
        inventoryItems[1].setPosition(new Vector2((camera.position.x - frameWidth/3.81f), (framePosition.y + frameHeight) * 0.715f));
    }

    public void render(Item[] items) {
        if (showInventory) {
            MyGdxGame.batch.draw(frame, framePosition.x, framePosition.y, frameWidth, frameHeight);
            MyGdxGame.batch.draw(cellsArea, cellsAreaPosition.x, cellsAreaPosition.y, cellsAreaWidth, cellsAreaHeight);

            if (isCursorOnIcon)
                MyGdxGame.batch.draw(cellLight, cellLightPosition.x, cellLightPosition.y, cellWidth, cellHeight);

            if (items[5].getIsTaken())
                MyGdxGame.batch.draw(hammerIcon, inventoryItems[0].getPosition().x, inventoryItems[0].getPosition().y, cellWidth, cellHeight);
            if (items[8].getIsTaken())
                MyGdxGame.batch.draw(keyIcon, inventoryItems[1].getPosition().x, inventoryItems[1].getPosition().y, cellWidth, cellHeight);
        }
    }

    public boolean isShowInventory() {
        return showInventory;
    }

    public void setShowInventory(boolean b) {
        showInventory = b;
    }

    public void setCursorOnIcon(boolean b) {
        isCursorOnIcon = b;
    }

    public float getCellWidth() {
        return cellWidth;
    }

    public float getCellHeight() {
        return cellHeight;
    }

    public Vector2 getCellLightPosition() {
        return cellLightPosition;
    }

    public void setCellLightPosition(Vector2 position) {
        cellLightPosition = position;
    }

    public InventoryItem [] getInventoryItems() {
        return inventoryItems;
    }
}
