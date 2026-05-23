package io.github.longfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.VisUI;

import games.rednblack.miniaudio.MASound;
import games.rednblack.miniaudio.MiniAudio;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public SpriteBatch batch;
    public MiniAudio miniAudio;
    public MASound bgMusic;
    
    @Override
    public void create() {
        VisUI.load();
        batch = new SpriteBatch();

        miniAudio = new MiniAudio();
        bgMusic = miniAudio.createSound("sound/songs/Buried_Souls.ogg");
        bgMusic.setLooping(true);
        bgMusic.setVolume(1.0f);
        bgMusic.play();
        bgMusic.fadeIn(60);

        setScreen(new FirstScreen(this));

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
        throwable.printStackTrace();
        System.err.flush();
        });
    }
}