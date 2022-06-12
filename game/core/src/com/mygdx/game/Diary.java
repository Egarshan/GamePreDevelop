package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Diary {

    private TextureRegion diaryTexture1, diaryTexture2;
    private TextureRegion text0_1, text0_2, text0_3, text0_4, text0_5;
    private TextureRegion text1_1, text1_2, text1_3;
    private TextureRegion text2_1, text2_2, text2_3, text2_4;
    private TextureRegion text3_1, text3_2, text3_3, text3_4, text3_5;
    private TextureRegion text4_1, text4_2, text4_3, text4_4, text4_5, text4_6;
    private TextureRegion text5_1, text5_2, text5_3, text5_4;
    private TextureRegion text6_1, text6_2, text6_3;
    private Vector2 diaryPosition;
    private Vector2 text0_1Position, text0_2Position, text0_3Position, text0_4Position, text0_5Position;
    private Vector2 text1_1Position, text1_2Position, text1_3Position;
    private Vector2 text2_1Position, text2_2Position, text2_3Position, text2_4Position;
    private Vector2 text3_1Position, text3_2Position, text3_3Position, text3_4Position, text3_5Position;
    private Vector2 text4_1Position, text4_2Position, text4_3Position, text4_4Position, text4_5Position, text4_6Position;
    private Vector2 text5_1Position, text5_2Position, text5_3Position, text5_4Position;
    private Vector2 text6_1Position, text6_2Position, text6_3Position;
    private float diaryWidth, diaryHeight;
    private float text0_1Width, text0_1Height, text0_2Width, text0_2Height, text0_3Width, text0_3Height, text0_4Width, text0_4Height, text0_5Width, text0_5Height;
    private float text1_1Width, text1_1Height, text1_2Width, text1_2Height, text1_3Width, text1_3Height;
    private float text2_1Width, text2_1Height, text2_2Width, text2_2Height, text2_3Width, text2_3Height, text2_4Width, text2_4Height;
    private float text3_1Width, text3_1Height, text3_2Width, text3_2Height, text3_3Width, text3_3Height, text3_4Width, text3_4Height, text3_5Width, text3_5Height;
    private float text4_1Width, text4_1Height, text4_2Width, text4_2Height, text4_3Width, text4_3Height, text4_4Width, text4_4Height, text4_5Width, text4_5Height, text4_6Width, text4_6Height;
    private float text5_1Width, text5_1Height, text5_2Width, text5_2Height, text5_3Width, text5_3Height, text5_4Width, text5_4Height;
    private float text6_1Width, text6_1Height, text6_2Width, text6_2Height, text6_3Width, text6_3Height;
    private boolean showDiary;
    private int n;
    private Camera camera;

    public Diary(Camera camera) {
        this.camera = camera;
        diaryTexture1 = ResourcesClass.getResources().get(7)[0];
        diaryTexture2 = ResourcesClass.getResources().get(7)[1];

        text0_1 = ResourcesClass.getResources().get(8)[0];
        text0_2 = ResourcesClass.getResources().get(8)[1];
        text0_3 = ResourcesClass.getResources().get(8)[2];
        text0_4 = ResourcesClass.getResources().get(8)[3];
        text0_5 = ResourcesClass.getResources().get(8)[4];

        text1_1 = ResourcesClass.getResources().get(11)[0];
        text1_2 = ResourcesClass.getResources().get(11)[1];
        text1_3 = ResourcesClass.getResources().get(11)[2];

        text2_1 = ResourcesClass.getResources().get(12)[0];
        text2_2 = ResourcesClass.getResources().get(12)[1];
        text2_3 = ResourcesClass.getResources().get(12)[2];
        text2_4 = ResourcesClass.getResources().get(12)[3];

        text3_1 = ResourcesClass.getResources().get(13)[0];
        text3_2 = ResourcesClass.getResources().get(13)[1];
        text3_3 = ResourcesClass.getResources().get(13)[2];
        text3_4 = ResourcesClass.getResources().get(13)[3];
        text3_5 = ResourcesClass.getResources().get(13)[4];

        text4_1 = ResourcesClass.getResources().get(14)[0];
        text4_2 = ResourcesClass.getResources().get(14)[1];
        text4_3 = ResourcesClass.getResources().get(14)[2];
        text4_4 = ResourcesClass.getResources().get(14)[3];
        text4_5 = ResourcesClass.getResources().get(14)[4];
        text4_6 = ResourcesClass.getResources().get(14)[5];

        text5_1 = ResourcesClass.getResources().get(15)[0];
        text5_2 = ResourcesClass.getResources().get(15)[1];
        text5_3 = ResourcesClass.getResources().get(15)[2];
        text5_4 = ResourcesClass.getResources().get(15)[3];

        text6_1 = ResourcesClass.getResources().get(16)[0];
        text6_2 = ResourcesClass.getResources().get(16)[1];
        text6_3 = ResourcesClass.getResources().get(16)[2];

        diaryWidth = MyGdxGame.WIDTH/1.3f;
        diaryHeight = diaryWidth/1.77f;

        text0_1Width = diaryWidth/2.7f;
        text0_1Height = text0_1Width/1.13f;
        text0_2Width = diaryWidth/2.7f;
        text0_2Height = text0_2Width/3.08f;
        text0_3Width = diaryWidth/2.7f;
        text0_3Height = text0_3Width/1.28f;
        text0_4Width = diaryWidth/2.7f;
        text0_4Height = text0_4Width/5.32f;
        text0_5Width = diaryWidth/2.7f;
        text0_5Height = text0_5Width/5.5f;

        text1_1Width = diaryWidth/2.8f;
        text1_1Height = text1_1Width*1.4212f;
        text1_2Width = diaryWidth/2.8f;
        text1_2Height = text1_2Width*1.5844f;
        text1_3Width = diaryWidth/2.7f;
        text1_3Height = text1_3Width/2.9318f;

        text2_1Width = diaryWidth/2.8f;
        text2_1Height = text2_1Width/1.4141f;
        text2_2Width = diaryWidth/2.7f;
        text2_2Height = text2_2Width/1.5247f;
        text2_3Width = diaryWidth/2.7f;
        text2_3Height = text2_3Width/1.2458f;
        text2_4Width = diaryWidth/2.7f;
        text2_4Height = text2_4Width/1.9544f;

        text3_1Width = diaryWidth/2.8f;
        text3_1Height = text3_1Width/1.3835f;
        text3_2Width = diaryWidth/2.7f;
        text3_2Height = text3_2Width/1.65689f;
        text3_3Width = diaryWidth/2.7f;
        text3_3Height = text3_3Width/1.3893f;
        text3_4Width = diaryWidth/2.7f;
        text3_4Height = text3_4Width/5.2f;
        text3_5Width = diaryWidth/2.7f;
        text3_5Height = text3_5Width/2.327f;

        text4_1Width = diaryWidth/2.9f;
        text4_1Height = text4_1Width/1.088f;
        text4_2Width = diaryWidth/2.9f;
        text4_2Height = text4_2Width/2.044f;
        text4_3Width = diaryWidth/2.8f;
        text4_3Height = text4_3Width/1.165f;
        text4_4Width = diaryWidth/2.8f;
        text4_4Height = text4_4Width/1.9618f;
        text4_5Width = diaryWidth/10.9f;
        text4_5Height = text4_5Width*5.5454f;
        text4_6Width = diaryWidth/10.9f;
        text4_6Height = text4_6Width*5.5454f;

        text5_1Width = diaryWidth/2.8f;
        text5_1Height = text5_1Width/1.177f;
        text5_2Width = diaryWidth/2.7f;
        text5_2Height = text5_2Width/1.763f;
        text5_3Width = diaryWidth/2.8f;
        text5_3Height = text5_3Width/1.216f;
        text5_4Width = diaryWidth/2.8f;
        text5_4Height = text5_4Width/1.8f;

        text6_1Width = diaryWidth/2.8f;
        text6_1Height = text6_1Width/1.199f;
        text6_2Width = diaryWidth/2.8f;
        text6_2Height = text6_2Width/1.7448f;
        text6_3Width = diaryWidth/2.8f;
        text6_3Height = text6_3Width*1.33f;


        diaryPosition = new Vector2(camera.position.x-diaryWidth/2f, MyGdxGame.HEIGHT/2f-diaryHeight/2f);

        text0_1Position = new Vector2(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text0_1Height-diaryHeight/8f);
        text0_2Position = new Vector2(text0_1Position.x, text0_1Position.y-text0_2Height);
        text0_3Position = new Vector2(diaryPosition.x+diaryWidth-text0_3Width-diaryWidth/9.5f, diaryPosition.y+diaryHeight-text0_3Height-diaryHeight/8f);
        text0_4Position = new Vector2(text0_3Position.x, text0_3Position.y-text0_4Height-diaryHeight/18f);
        text0_5Position = new Vector2(text0_4Position.x, text0_4Position.y-text0_5Height);

        text1_1Position = new Vector2(diaryPosition.x+diaryWidth/18f, diaryPosition.y+diaryHeight-text1_1Height-diaryHeight/12f);
        text1_2Position = new Vector2(diaryPosition.x+diaryWidth-text1_2Width-diaryWidth/9f, diaryPosition.y+diaryHeight-text1_2Height-diaryHeight/12f);
        text1_3Position = new Vector2(text1_2Position.x, diaryPosition.y+diaryHeight/30f);

        text2_1Position = new Vector2(diaryPosition.x+diaryWidth/22f, diaryPosition.y+diaryHeight-text2_1Height-diaryHeight/10f);
        text2_2Position = new Vector2(text2_1Position.x, text2_1Position.y-text2_1Height+text2_1Height/20f);
        text2_3Position = new Vector2(diaryPosition.x+diaryWidth-text2_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text2_3Height-diaryHeight/8f);
        text2_4Position = new Vector2(text2_3Position.x, text2_3Position.y-text2_4Height);

        text3_1Position = new Vector2(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text3_1Height-diaryHeight/10f);
        text3_2Position = new Vector2(text3_1Position.x-text3_1Width/10f, text3_1Position.y-text3_1Height+text3_1Height/11f);
        text3_3Position = new Vector2(diaryPosition.x+diaryWidth-text3_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text3_3Height-diaryHeight/10f);
        text3_4Position = new Vector2(text3_3Position.x, text3_3Position.y-text3_4Height+text3_4Height/10f);
        text3_5Position = new Vector2(text3_3Position.x, text3_4Position.y-text3_5Height);

        text4_1Position = new Vector2(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text3_1Height-diaryHeight/10f);
        text4_2Position = new Vector2(text3_1Position.x-text3_1Width/10f, text3_1Position.y-text3_1Height+text3_1Height/11f);
        text4_3Position = new Vector2(diaryPosition.x+diaryWidth-text3_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text3_3Height-diaryHeight/10f);
        text4_4Position = new Vector2(text3_3Position.x, text3_3Position.y-text3_4Height+text3_4Height/10f);
        text4_5Position = new Vector2(text3_3Position.x, text3_4Position.y-text3_5Height);
        text4_6Position = new Vector2(text3_3Position.x, text3_4Position.y-text3_5Height);

        text5_1Position = new Vector2(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text5_1Height-diaryHeight/10f);
        text5_2Position = new Vector2(text5_1Position.x, text5_1Position.y-text5_2Height+text5_2Height/15f);
        text5_3Position = new Vector2(diaryPosition.x+diaryWidth-text5_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text5_3Height-diaryHeight/11f);
        text5_4Position = new Vector2(text5_3Position.x, text5_3Position.y-text5_4Height);

        text6_1Position = new Vector2(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text6_1Height-diaryHeight/10f);
        text6_2Position = new Vector2(text6_1Position.x, text6_1Position.y-text6_2Height+text6_2Height/20f);
        text6_3Position = new Vector2(diaryPosition.x+diaryWidth-text6_3Width-diaryWidth/9f, diaryPosition.y+diaryHeight-text6_3Height-diaryHeight/8f);


        showDiary = false;
        n = 1;
    }

    public void update(float deltaTime) {
        diaryPosition = new Vector2(camera.position.x-diaryWidth/2f, MyGdxGame.HEIGHT/2f-diaryHeight/2f);


        text0_1Position.set(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text0_1Height-diaryHeight/8f);
        text0_2Position.set(text0_1Position.x, text0_1Position.y-text0_2Height);
        text0_3Position.set(diaryPosition.x+diaryWidth-text0_3Width-diaryWidth/9.5f, diaryPosition.y+diaryHeight-text0_3Height-diaryHeight/8f);
        text0_4Position.set(text0_3Position.x, text0_3Position.y-text0_4Height-diaryHeight/18f);
        text0_5Position.set(text0_4Position.x, text0_4Position.y-text0_5Height);

        text1_1Position.set(diaryPosition.x+diaryWidth/18f, diaryPosition.y+diaryHeight-text1_1Height-diaryHeight/12f);
        text1_2Position.set(diaryPosition.x+diaryWidth-text1_2Width-diaryWidth/9f, diaryPosition.y+diaryHeight-text1_2Height-diaryHeight/12f);
        text1_3Position.set(text1_2Position.x, diaryPosition.y+diaryHeight/30f);

        text2_1Position.set(diaryPosition.x+diaryWidth/22f, diaryPosition.y+diaryHeight-text2_1Height-diaryHeight/10f);
        text2_2Position.set(text2_1Position.x, text2_1Position.y-text2_1Height+text2_1Height/20f);
        text2_3Position.set(diaryPosition.x+diaryWidth-text2_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text2_3Height-diaryHeight/8f);
        text2_4Position.set(text2_3Position.x, text2_3Position.y-text2_4Height);

        text3_1Position.set(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text3_1Height-diaryHeight/10f);
        text3_2Position.set(text3_1Position.x-text3_1Width/10f, text3_1Position.y-text3_1Height+text3_1Height/11f);
        text3_3Position.set(diaryPosition.x+diaryWidth-text3_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text3_3Height-diaryHeight/10f);
        text3_4Position.set(text3_3Position.x, text3_3Position.y-text3_4Height+text3_4Height/10f);
        text3_5Position.set(text3_3Position.x, text3_4Position.y-text3_5Height);

        text4_1Position.set(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text4_1Height-diaryHeight/10f);
        text4_2Position.set(text4_1Position.x, text4_1Position.y-text4_2Height);
        text4_3Position.set(diaryPosition.x+diaryWidth-text4_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text4_3Height-diaryHeight/10f);
        text4_4Position.set(text4_3Position.x, text4_3Position.y-text4_4Height);
        text4_5Position.set(diaryPosition.x+diaryWidth/2f+text4_5Width/5f, diaryPosition.y+diaryHeight/40f);
        text4_6Position.set(diaryPosition.x+diaryWidth/2f-1.85f*text4_6Width, diaryPosition.y+diaryHeight/40f);

        text5_1Position.set(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text5_1Height-diaryHeight/10f);
        text5_2Position.set(text5_1Position.x, text5_1Position.y-text5_2Height+text5_2Height/15f);
        text5_3Position.set(diaryPosition.x+diaryWidth-text5_3Width-diaryWidth/10f, diaryPosition.y+diaryHeight-text5_3Height-diaryHeight/11f);
        text5_4Position.set(text5_3Position.x, text5_3Position.y-text5_4Height);

        text6_1Position.set(diaryPosition.x+diaryWidth/20f, diaryPosition.y+diaryHeight-text6_1Height-diaryHeight/10f);
        text6_2Position.set(text6_1Position.x, text6_1Position.y-text6_2Height+text6_2Height/20f);
        text6_3Position.set(diaryPosition.x+diaryWidth-text6_3Width-diaryWidth/9f, diaryPosition.y+diaryHeight-text6_3Height-diaryHeight/8f);
    }

    public void render() {
        if (showDiary) {
            if(n == 8) {
                MyGdxGame.batch.draw(diaryTexture1, diaryPosition.x, diaryPosition.y, diaryWidth, diaryHeight);
            } else {
                MyGdxGame.batch.draw(diaryTexture2, diaryPosition.x, diaryPosition.y, diaryWidth, diaryHeight);
            }

            switch (n) {

                case 1: MyGdxGame.batch.draw(text1_1, text1_1Position.x, text1_1Position.y, text1_1Width, text1_1Height);
                        MyGdxGame.batch.draw(text1_2, text1_2Position.x, text1_2Position.y, text1_2Width, text1_2Height);
                        MyGdxGame.batch.draw(text1_3, text1_3Position.x, text1_3Position.y, text1_3Width, text1_3Height); break;

                case 2: MyGdxGame.batch.draw(text2_1, text2_1Position.x, text2_1Position.y, text2_1Width, text2_1Height);
                        MyGdxGame.batch.draw(text2_2, text2_2Position.x, text2_2Position.y, text2_2Width, text2_2Height);
                        MyGdxGame.batch.draw(text2_3, text2_3Position.x, text2_3Position.y, text2_3Width, text2_3Height);
                        MyGdxGame.batch.draw(text2_4, text2_4Position.x, text2_4Position.y, text2_4Width, text2_4Height); break;

                case 3: MyGdxGame.batch.draw(text3_1, text3_1Position.x, text3_1Position.y, text3_1Width, text3_1Height);
                        MyGdxGame.batch.draw(text3_2, text3_2Position.x, text3_2Position.y, text3_2Width, text3_2Height);
                        MyGdxGame.batch.draw(text3_3, text3_3Position.x, text3_3Position.y, text3_3Width, text3_3Height);
                        MyGdxGame.batch.draw(text3_4, text3_4Position.x, text3_4Position.y, text3_4Width, text3_4Height);
                        MyGdxGame.batch.draw(text3_5, text3_5Position.x, text3_5Position.y, text3_5Width, text3_5Height); break;

                case 4: MyGdxGame.batch.draw(text4_1, text4_1Position.x, text4_1Position.y, text4_1Width, text4_1Height);
                        MyGdxGame.batch.draw(text4_2, text4_2Position.x, text4_2Position.y, text4_2Width, text4_2Height);
                        MyGdxGame.batch.draw(text4_3, text4_3Position.x, text4_3Position.y, text4_3Width, text4_3Height);
                        MyGdxGame.batch.draw(text4_4, text4_4Position.x, text4_4Position.y, text4_4Width, text4_4Height);
                        MyGdxGame.batch.draw(text4_5, text4_5Position.x, text4_5Position.y, text4_5Width, text4_5Height); break;

                case 5: MyGdxGame.batch.draw(text4_1, text4_1Position.x, text4_1Position.y, text4_1Width, text4_1Height);
                        MyGdxGame.batch.draw(text4_2, text4_2Position.x, text4_2Position.y, text4_2Width, text4_2Height);
                        MyGdxGame.batch.draw(text4_3, text4_3Position.x, text4_3Position.y, text4_3Width, text4_3Height);
                        MyGdxGame.batch.draw(text4_4, text4_4Position.x, text4_4Position.y, text4_4Width, text4_4Height);
                        MyGdxGame.batch.draw(text4_6, text4_6Position.x, text4_6Position.y, text4_6Width, text4_6Height); break;

                case 6: MyGdxGame.batch.draw(text5_1, text5_1Position.x, text5_1Position.y, text5_1Width, text5_1Height);
                        MyGdxGame.batch.draw(text5_2, text5_2Position.x, text5_2Position.y, text5_2Width, text5_2Height);
                        MyGdxGame.batch.draw(text5_3, text5_3Position.x, text5_3Position.y, text5_3Width, text5_3Height);
                        MyGdxGame.batch.draw(text5_4, text5_4Position.x, text5_4Position.y, text5_4Width, text5_4Height); break;

                case 7: MyGdxGame.batch.draw(text6_1, text6_1Position.x, text6_1Position.y, text6_1Width, text6_1Height);
                        MyGdxGame.batch.draw(text6_2, text6_2Position.x, text6_2Position.y, text6_2Width, text6_2Height);
                        MyGdxGame.batch.draw(text6_3, text6_3Position.x, text6_3Position.y, text6_3Width, text6_3Height); break;

                case 8: MyGdxGame.batch.draw(text0_1, text0_1Position.x, text0_1Position.y, text0_1Width, text0_1Height);
                        MyGdxGame.batch.draw(text0_2, text0_2Position.x, text0_2Position.y, text0_2Width, text0_2Height);
                        MyGdxGame.batch.draw(text0_3, text0_3Position.x, text0_3Position.y, text0_3Width, text0_3Height);
                        MyGdxGame.batch.draw(text0_4, text0_4Position.x, text0_4Position.y, text0_4Width, text0_4Height);
                        MyGdxGame.batch.draw(text0_5, text0_5Position.x, text0_5Position.y, text0_5Width, text0_5Height); break;
            }

        }
    }

    public void dispose() {

    }

    public boolean isShowDiary() {
        return showDiary;
    }

    public void setShowDiary(boolean b) {
        showDiary = b;
    }

    public void setN(int number) {
        if(number > 8) {
            n = 8;
        } else {
            if(number < 1) {
                n = 1;
            } else {
                n = number;
            }
        }
    }

    public int getN() {
        return n;
    }
}
