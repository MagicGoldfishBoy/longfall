package io.github.longfall;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;

import games.rednblack.miniaudio.MASound;
import games.rednblack.miniaudio.MiniAudio;

public class Music implements ApplicationListener {

    static MiniAudio miniAudio;
    MASound maSound;
    AssetManager assetManager;

    @Override
    public void create() {

        miniAudio = new MiniAudio();

        maSound = miniAudio.createSound("sound/songs/Buried_Souls.ogg");

        maSound.fadeIn(60);

        maSound.setLooping(true);

        maSound.setVolume(1.0f);

        maSound.play();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
    
}
