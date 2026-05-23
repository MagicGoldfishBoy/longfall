package io.github.longfall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.VisUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        VisUI.load();
        batch = new SpriteBatch();
        setScreen(new FirstScreen(this));

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
        throwable.printStackTrace();
        System.err.flush();
        });
    }
}