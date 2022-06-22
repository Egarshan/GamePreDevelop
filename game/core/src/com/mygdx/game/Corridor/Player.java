package com.mygdx.game.Corridor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.*;

import java.util.Objects;

public class Player {
    private Sprite[] stayAnimFrames, walkAnimFrames, hitAnimFrames, takeAnimFrames;
    private Animation<Sprite> stayAnim, walkAnim, hitAnim, takeAnim, maskAnim;
    private TextureRegion currentFrame;
    private Vector2 position, speed, speed0, destination;
    private float width, height, width0;
    private float delta1, delta2, delta3, delta4;
    private boolean stand, walk, changeDirection, right, isHitAnim, isStandAnim, isTakeAnim, intersected;
    private Timer standTimer;
    private Polygon polygon;
    private CorridorScene corridorScene;
    private GameCamera camera;
    private RubbishBin bin;
    private Flowers flowers;
    private Reactions reactions;
    private String reactionName;

    private Mask mask;
    private TextureRegion[] maskTextures;

    private boolean walkingSound, actionSound;

    private final float SOUND_VOLUME = 0.5f;


    public Player(CorridorScene corridorScene, GameCamera camera, RubbishBin bin, Flowers flowers, Reactions reactions) {
        this.corridorScene = corridorScene;
        this.camera = camera;
        this.bin = bin;
        this.flowers = flowers;
        this.reactions = reactions;
        mask = new Mask();
        stayAnimFrames = new Sprite[45];
        walkAnimFrames = new Sprite[18];
        hitAnimFrames = new Sprite[37];
        takeAnimFrames = new Sprite[17];


        for (int i = 0; i < stayAnimFrames.length; i++) {
            stayAnimFrames[i] = new Sprite(ResourcesClass.getResources().get(2)[i]);
        }
        for (int i = 0; i < walkAnimFrames.length; i++) {
            walkAnimFrames[i] = new Sprite(ResourcesClass.getResources().get(3)[i]);
        }
        for (int i = 0; i < hitAnimFrames.length; i++) {
            hitAnimFrames[i] = new Sprite(ResourcesClass.getResources().get(5)[i]);
        }
        for (int i = 0; i < takeAnimFrames.length; i++) {
            takeAnimFrames[i] = new Sprite(ResourcesClass.getResources().get(6)[i]);
        }

        stayAnim = new Animation<>(0.033f, stayAnimFrames);
        walkAnim = new Animation<>(0.033f, walkAnimFrames);
        hitAnim = new Animation<>(0.02f, hitAnimFrames);
        takeAnim = new Animation<>(0.033f, takeAnimFrames);


        for (Sprite stayAnimFrame : stayAnimFrames) {
            stayAnimFrame.flip(true, false);
        }
        for (Sprite walkAnimFrame : walkAnimFrames) {
            walkAnimFrame.flip(true, false);
        }
        for (Sprite hitAnimFrame : hitAnimFrames) {
            hitAnimFrame.flip(true, false);
        }
        for (Sprite takeAnimFrame : takeAnimFrames) {
            takeAnimFrame.flip(true, false);
        }

        position = new Vector2(Data.getPrefs().getFloat("player_position_x"), Data.getPrefs().getFloat("player_position_y"));
        speed = new Vector2(0, 0);
        speed0 = new Vector2(0, 0);
        destination = new Vector2();

        width = MyGdxGame.WIDTH / 5.5f;
        height = width * 1.364f;

        delta1 = 0f;
        delta2 = 0f;
        delta3 = 0f;
        delta4 = 0f;

        stand = true;
        walk = false;
        changeDirection = false;
        right = true;
        isHitAnim = false;
        isStandAnim = true;
        isTakeAnim = false;
        intersected = false;

        polygon = new Polygon(new float[]{position.x + width / 3f, position.y + height / 20f, position.x + width / 2f, position.y + height / 20f, position.x + width / 2f, position.y + height / 18f, position.x + width / 3f, position.y + height / 18f});

        reactionName = "";

        standTimer = new Timer();

        maskTextures = new TextureRegion[2];
        maskTextures[0] = ResourcesClass.getResources().get(21)[0];
        maskTextures[1] = ResourcesClass.getResources().get(21)[1];

        for (TextureRegion mask : maskTextures)
            mask.flip(true, false);

        walkingSound = false;
        actionSound = false;
    }

    public void update(float deltaTime) {
        polygon.setVertices(new float[]{position.x + width / 2.3f, position.y + height / 50f, position.x + width / 1.6f, position.y + height / 50f, position.x + width / 1.6f, position.y + height / 30f, position.x + width / 2.3f, position.y + height / 30f});
//        if ((Intersector.overlapConvexPolygons(polygon, bin.getPolygon()) || Intersector.overlapConvexPolygons(polygon, bin.getPolygon2()) || Intersector.overlapConvexPolygons(polygon, flowers.getPolygon1()) || Intersector.overlapConvexPolygons(polygon, flowers.getPolygon2()))) {
//            speed0.set(0, 0);
//            walk = false;
//            stand = true;
//            intersected = true;
//            delta1 = 0;
//        } else {
//            speed0.set(speed);
//        }
        speed0.set(speed);

        width = MyGdxGame.WIDTH / 5.5f - position.y / 2.5f;
        height = width * 1.364f;

        if (stand) {
            if (walkingSound) {
                AudioPlayer.getSounds()[0].stop();
                walkingSound = false;
            }
            startTimer();
        } else {
            stopTimer();
        }

        if (!isStandAnim) {
            delta1 = 0;
        } else {
            delta1 += deltaTime;
        }
        delta2 += deltaTime;
        if (!isHitAnim) {
            delta3 = 0;
        } else {
            delta3 += deltaTime;
        }
        if (!isTakeAnim) {
            delta4 = 0;
        } else {
            delta4 += deltaTime;
        }
        if (changeDirection) {
            for (Sprite stayAnimFrame : stayAnimFrames) {
                stayAnimFrame.flip(true, false);
            }
            for (Sprite walkAnimFrame : walkAnimFrames) {
                walkAnimFrame.flip(true, false);
            }
            for (Sprite hitAnimFrame : hitAnimFrames) {
                hitAnimFrame.flip(true, false);
            }
            for (Sprite takeAnimFrame : takeAnimFrames) {
                takeAnimFrame.flip(true, false);
            }

            for (TextureRegion mask : maskTextures) {
                mask.flip(true, false);
            }

            stand = false;
            walk = true;
            speed0.set(speed);
            changeDirection = false;
            intersected = false;
        }
        if (walk) {
            if (position.y <= 0) {
                position.y = 0;
            }
            if (position.y > 200) {
                position.y = 200;
            }
            if (position.x + (width / 2f) < 10) {
                position.x = 210 - width / 2f;
            }

            if (position.x + (width / 2f) - destination.x <= 0) {
                if (speed.x > 0) {
                    if (speed0.x == 0) {
                        position.add(speed0.x * deltaTime, speed0.y * deltaTime);
                    } else {
                        position.add(speed.x * deltaTime, speed.y * deltaTime);
                    }
                } else {
                    if (!reactions.isReactionEmpty()) {
                        reactions.setDraw(true);
                        if (Objects.equals(reactionName, "hammer") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            reactionName = "";
                            corridorScene.getItems()[5].setVisible(false);
                            corridorScene.getItems()[5].setIsTaken(true);

                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "mask") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            reactionName = "";
                            corridorScene.getItems()[3].setVisible(false);
                            corridorScene.getItems()[3].setIsTaken(true);
                            mask.takeMask("anger");
                            corridorScene.getHud().setIsShowMask(true);

                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "honor_board") && !actionSound) {
                            AudioPlayer.getSounds()[1].play(SOUND_VOLUME);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "hammer_honor_board") && !actionSound) {
                            walk = false;
                            isHitAnim = true;
                            AudioPlayer.getSounds()[2].play(SOUND_VOLUME);
                            corridorScene.getItems()[7].setVisible(true);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "photo") && !actionSound) {
                            if (!corridorScene.getMeetIsDone()) {
                                corridorScene.setMeetIsDone(true);

                                AudioPlayer.getSounds()[1].play(SOUND_VOLUME);
                                corridorScene.getDialog().nextDialog("dialog_1");
                                DarkBackground.setIsDark(true);
                            } else {
                                isTakeAnim = true;
                                walk = false;
                                corridorScene.getItems()[4].setVisible(false);
                                corridorScene.getItems()[4].setIsTaken(true);
                                corridorScene.getHud().setIsShowPhoto(true);

                                AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                                corridorScene.getDialog().nextDialog("dialog_2");
                                corridorScene.setKeyIsAvailable(true);
                            }
                            actionSound = true;
                        }
                        else if (Objects.equals(reactionName, "key") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            actionSound = true;

                            corridorScene.getItems()[8].setIsTaken(true);
                        }
                        else if (Objects.equals(reactionName, "exit_door") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            if (Objects.equals(MyGdxGame.currentCursor, "keyGlow")) {
                                AudioPlayer.getSounds()[4].play(SOUND_VOLUME);
                                actionSound = true;
                                corridorScene.setDoorIsOpen(true);
                                corridorScene.getItems()[0].setVisible(false);
                                corridorScene.getItems()[1].setVisible(true);
                            }
                            reactionName = "";
                            MyGdxGame.changeCursor("simple");
                        }
                        else if (Objects.equals(reactionName, "finish_game")) {
                            corridorScene.finishWindow.setDraw(true);
                        }
                    }
                    if (!isTakeAnim && !isHitAnim) {
                        walk = false;
                        stand = true;
                    }
                    camera.setMoveRight(false);
                }
            }

            if (position.x + (width / 2f) - destination.x >= 0) {
                if (speed.x < 0) {
                    if (speed0.x == 0) {
                        position.add(speed0.x * deltaTime, speed0.y * deltaTime);
                    } else {
                        position.add(speed.x * deltaTime, speed.y * deltaTime);
                    }
                } else {
                    if (!reactions.isReactionEmpty()) {
                        reactions.setDraw(true);
                        if (Objects.equals(reactionName, "hammer") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            reactionName = "";
                            corridorScene.getItems()[5].setVisible(false);
                            corridorScene.getItems()[5].setIsTaken(true);

                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "mask") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            reactionName = "";
                            corridorScene.getItems()[3].setVisible(false);
                            corridorScene.getItems()[3].setIsTaken(true);
                            mask.takeMask("anger");
                            corridorScene.getHud().setIsShowMask(true);

                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "honor_board") && !actionSound) {
                            AudioPlayer.getSounds()[1].play(SOUND_VOLUME);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "hammer_honor_board") && !actionSound) {
                            walk = false;
                            isHitAnim = true;
                            AudioPlayer.getSounds()[2].play(SOUND_VOLUME);
                            corridorScene.getItems()[7].setVisible(true);
                            actionSound = true;
                        } else if (Objects.equals(reactionName, "photo") && !actionSound) {
                            if (!corridorScene.getMeetIsDone()) {
                                corridorScene.setMeetIsDone(true);

                                AudioPlayer.getSounds()[1].play(SOUND_VOLUME);
                                corridorScene.getDialog().nextDialog("dialog_1");
                                DarkBackground.setIsDark(true);
                            } else {
                                isTakeAnim = true;
                                walk = false;
                                corridorScene.getItems()[4].setVisible(false);
                                corridorScene.getItems()[4].setIsTaken(true);
                                corridorScene.getHud().setIsShowPhoto(true);

                                AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                                corridorScene.getDialog().nextDialog("dialog_2");
                                corridorScene.setKeyIsAvailable(true);
                            }
                            actionSound = true;
                        }
                        else if (Objects.equals(reactionName, "key") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            actionSound = true;

                            corridorScene.getItems()[8].setIsTaken(true);
                        }
                        else if (Objects.equals(reactionName, "exit_door") && !actionSound) {
                            isTakeAnim = true;
                            walk = false;
                            if (Objects.equals(MyGdxGame.currentCursor, "keyGlow")) {
                                AudioPlayer.getSounds()[4].play(SOUND_VOLUME);
                                actionSound = true;
                                corridorScene.setDoorIsOpen(true);
                                corridorScene.getItems()[0].setVisible(false);
                                corridorScene.getItems()[1].setVisible(true);
                            }
                            reactionName = "";
                            MyGdxGame.changeCursor("simple");
                        }
                        else if (Objects.equals(reactionName, "finish_game")) {
                            corridorScene.finishWindow.setDraw(true);
                        }
                    }
                    if (!isTakeAnim && !isHitAnim) {
                        walk = false;
                        stand = true;
                    }
                    camera.setMoveLeft(false);
                }
            }
            if (!walkingSound) {
                AudioPlayer.getSounds()[0].play(SOUND_VOLUME);
                walkingSound = true;
            }
        } else {
            if (intersected) {
                if (!reactions.isReactionEmpty()) {
                    reactions.setDraw(true);
                    if (Objects.equals(reactionName, "hammer") && !actionSound) {
                        isTakeAnim = true;
                        walk = false;
                        reactionName = "";
                        corridorScene.getItems()[5].setVisible(false);
                        corridorScene.getItems()[5].setIsTaken(true);

                        AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                        actionSound = true;
                    } else if (Objects.equals(reactionName, "mask") && !actionSound) {
                        isTakeAnim = true;
                        walk = false;
                        reactionName = "";
                        corridorScene.getItems()[3].setVisible(false);
                        corridorScene.getItems()[3].setIsTaken(true);
                        mask.takeMask("anger");
                        corridorScene.getHud().setIsShowMask(true);

                        AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                        actionSound = true;
                    } else if (Objects.equals(reactionName, "honor_board") && !actionSound) {
                        AudioPlayer.getSounds()[1].play(SOUND_VOLUME);
                        actionSound = true;
                    } else if (Objects.equals(reactionName, "hammer_honor_board") && !actionSound) {
                        walk = false;
                        isHitAnim = true;
                        AudioPlayer.getSounds()[2].play(SOUND_VOLUME);
                        corridorScene.getItems()[7].setVisible(true);
                        actionSound = true;
                    } else if (Objects.equals(reactionName, "photo") && !actionSound) {
                        if (!corridorScene.getMeetIsDone()) {
                            corridorScene.setMeetIsDone(true);

                            AudioPlayer.getSounds()[1].play(SOUND_VOLUME);
                            corridorScene.getDialog().nextDialog("dialog_1");
                            DarkBackground.setIsDark(true);
                        } else {
                            isTakeAnim = true;
                            walk = false;
                            corridorScene.getItems()[4].setVisible(false);
                            corridorScene.getItems()[4].setIsTaken(true);
                            corridorScene.getHud().setIsShowPhoto(true);

                            AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                            corridorScene.getDialog().nextDialog("dialog_2");
                            corridorScene.setKeyIsAvailable(true);
                        }
                        actionSound = true;
                    }
                    else if (Objects.equals(reactionName, "key") && !actionSound) {
                        isTakeAnim = true;
                        walk = false;
                        AudioPlayer.getSounds()[3].play(SOUND_VOLUME);
                        actionSound = true;

                        corridorScene.getItems()[8].setIsTaken(true);
                    }
                    else if (Objects.equals(reactionName, "exit_door") && !actionSound) {
                        isTakeAnim = true;
                        walk = false;
                        if (Objects.equals(MyGdxGame.currentCursor, "keyGlow")) {
                            AudioPlayer.getSounds()[4].play(SOUND_VOLUME);
                            actionSound = true;
                            corridorScene.setDoorIsOpen(true);
                            corridorScene.getItems()[0].setVisible(false);
                            corridorScene.getItems()[1].setVisible(true);
                        }
                        reactionName = "";
                        MyGdxGame.changeCursor("simple");
                    }
                    else if (Objects.equals(reactionName, "finish_game")) {
                        corridorScene.finishWindow.setDraw(true);
                    }
                }
                if (!isTakeAnim && !isHitAnim) {
                    walk = false;
                    stand = true;
                }
            }
        }

        position.set(CorridorScene.getViewport1().project(position));
        if (position.x + width / 2f >= MyGdxGame.WIDTH / 2f && walk && !camera.isCameraOnRight()) {
            camera.setMoveRight(true);
            camera.setMoveLeft(false);
        } else {
            if (position.x + width / 2f < MyGdxGame.WIDTH / 2f && walk && !camera.isCameraOnLeft()) {
                camera.setMoveLeft(true);
                camera.setMoveRight(false);
            } else {
                camera.setMoveLeft(false);
                camera.setMoveRight(false);
            }
        }

        position.set(CorridorScene.getViewport1().unproject(position));

        position.y = MyGdxGame.HEIGHT - position.y;
        width0 = width;
        width = MyGdxGame.WIDTH / 5.5f - position.y / 2.5f;
        position.x += (width0 - width) / 2f;

        if (!camera.isCameraOnLeft() || !camera.isCameraOnRight()) {
            camera.setCameraPosition(position.x + width / 2f);
        }
    }

    public void render() {
        if (isStandAnim && stand && !isHitAnim && !walk) {
            currentFrame = stayAnim.getKeyFrame(delta1, false);
            if (stayAnim.isAnimationFinished(delta1)) {
                delta1 = 0;
                isStandAnim = false;
            }
        }
        if (walk && !stand && !isHitAnim) {
            currentFrame = walkAnim.getKeyFrame(delta2, true);
        }
        if (isHitAnim && !stand && !walk && !isTakeAnim) {
            currentFrame = hitAnim.getKeyFrame(delta3, false);
            if (hitAnim.isAnimationFinished(delta3)) {
                delta3 = 0f;
                isHitAnim = false;
                stand = true;
                walk = false;
            }
        }
        if (isTakeAnim && !stand && !walk && !isHitAnim) {
            currentFrame = takeAnim.getKeyFrame(delta4, false);
            if (takeAnim.isAnimationFinished(delta4)) {
                delta4 = 0f;
                isTakeAnim = false;
                stand = true;
                walk = false;
            }
        }
        if (!isStandAnim && stand && !walk && !isHitAnim) {
            currentFrame = stayAnim.getKeyFrames()[0];
        }
        MyGdxGame.batch.draw(currentFrame, position.x, position.y, width, height);

        MyGdxGame.batch.draw(getCurrentMaskTexture(), position.x, position.y, width, height);
    }

    public void dispose() {
        Data.savePlayerPosition(position.x, position.y);
    }

    private void startTimer() {
        if (standTimer.isEmpty()) {
            standTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    isStandAnim = true;
                }
            }, 5, 5);
        }
    }

    private void stopTimer() {
        if (!standTimer.isEmpty()) {
            standTimer.clear();
        }
    }

    public void setReactionName(String name) {
        reactionName = name;
    }

    public void setTake(boolean b) {
        isTakeAnim = b;
    }

    public boolean getTake() {
        return isTakeAnim;
    }

    public void setHit(boolean b) {
        isHitAnim = b;
    }

    public boolean getHit() {
        return isHitAnim;
    }

    public void setStand(boolean b) {
        stand = b;
    }

    public void setWalk(boolean b) {
        walk = b;
    }

    public boolean getWalk() {
        return walk;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setDestination(int x, int y) {
        stand = false;
        walk = true;
        actionSound = false;
        reactionName = "";
        destination.set(x, y);
        if (position.x + (width / 2f) < x) {
            speed.x = (float) (250 * Math.abs(position.x + (width / 2f) - destination.x) / Math.sqrt(Math.pow((position.x + (width / 2f) - destination.x), 2) + Math.pow((position.y - destination.y), 2)));
            if (!right) {
                setChangeDirection();
                right = true;
            }
        } else {
            speed.x = (float) (-250 * Math.abs(position.x + (width / 2f) - destination.x) / Math.sqrt(Math.pow((position.x + (width / 2f) - destination.x), 2) + Math.pow((position.y - destination.y), 2)));
            if (right) {
                setChangeDirection();
                right = false;
            }
        }
        if (position.y < y) {
            speed.y = Math.abs(speed.x) * Math.abs(position.y - destination.y) / Math.abs(position.x + (width / 2f) - destination.x);
        } else {
            speed.y = -Math.abs(speed.x) * Math.abs(position.y - destination.y) / Math.abs(position.x + (width / 2f) - destination.x);
        }
    }

    public void setChangeDirection() {
        changeDirection = true;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Mask getMask() {
        return mask;
    }

    private TextureRegion getCurrentMaskTexture() {
        switch (mask.getCurrentMask()) {
            case "sad":
                return maskTextures[0];
            case "anger":
                return maskTextures[1];
        }
        return maskTextures[0];
    }
}
