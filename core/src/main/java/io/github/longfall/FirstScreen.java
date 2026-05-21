package io.github.longfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.widget.VisLabel;

import dev.lyze.flexbox.FlexBox;
import io.github.orioncraftmc.meditate.YogaNode;
import io.github.orioncraftmc.meditate.enums.YogaEdge;
import io.github.orioncraftmc.meditate.enums.YogaFlexDirection;
import io.github.orioncraftmc.meditate.enums.YogaWrap;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;

    final Main game;

    Texture backgroundTexture;

    Stage stage = new Stage(new ScreenViewport());
    FlexBox titleFlexBox = new FlexBox();

	public FirstScreen(final Main game) {

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera); 
        viewport.apply();

		this.game = game;

        Skin CurrentSkin = new Skin(Gdx.files.internal("uiskin.json"));

        backgroundTexture = new Texture(Gdx.files.internal("ui/graphics/ui/LongfallMainMenuBackground.png"));

        titleFlexBox.setFillParent(true);
        titleFlexBox.getRoot()
            .setFlexDirection(YogaFlexDirection.ROW)
            .setWrap(YogaWrap.WRAP);
        stage.addActor(titleFlexBox);

        YogaNode titleParentNode = titleFlexBox.add()
            .setFlexDirection(YogaFlexDirection.COLUMN)
            .setBorder(YogaEdge.ALL, 25)
            .setBackground(CurrentSkin.newDrawable("textfield"))
            .setMargin(YogaEdge.LEFT, 50)
            .setMargin(YogaEdge.TOP, 75);

		Label title = new Label("Longfall", CurrentSkin);
            title.setAlignment(Align.center);
            title.setColor(Color.SCARLET);
            title.setFontScale(2.0f);
            titleFlexBox.addAsChild(titleParentNode, title);

        stage.setDebugAll(true);
        // Label label = new Label("Longfall", CurrentSkin);
        // label.setAlignment(Align.center);
        // flexBox.add(label).setSize(100);
	}

    

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        draw();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
    private void draw() {
        ScreenUtils.clear(Color.RED);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        game.batch.end();
        stage.act();
        stage.draw();
    }
    
}