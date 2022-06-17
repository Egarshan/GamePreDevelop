package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioPlayer {
    private static Music[] music;
    private static Sound[] sounds, lamp;

    public AudioPlayer() {
        music = new Music[2];
        music[0] = Gdx.audio.newMusic(Gdx.files.internal("audio/hall_music.mp3"));

        music[0].setVolume(0.2f);

        sounds = new Sound[4];
        sounds[0] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/walking.mp3"));
        sounds[1] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/glass_knock.mp3"));
        sounds[2] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/glass_broke.mp3"));
        sounds[3] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/take.mp3"));

        lamp = new Sound[5];
        lamp[0] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/lamp/lamp_noise.mp3"));
        lamp[1] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/lamp/lamp_flick_1.mp3"));
        lamp[2] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/lamp/lamp_flick_2.mp3"));
        lamp[3] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/lamp/lamp_flick_3.mp3"));
        lamp[4] = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/lamp/noise_patch.mp3"));

    }

    public static Music[] getMusic() {
        return music;
    }

    public static Sound[] getSounds() {
        return sounds;
    }

    public static Sound[] getLamp() {
        return lamp;
    }

    public static void dispose() {
        for (Music el: music)
            el.dispose();
        for (Sound el: sounds)
            el.dispose();
        for (Sound el: lamp)
            el.dispose();
    }
}
