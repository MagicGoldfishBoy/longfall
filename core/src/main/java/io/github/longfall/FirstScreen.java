package io.github.longfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    FlexBox optionsFlexBox = new FlexBox();

    YogaNode playButtonNode;
    TextButton playButton;
    
    YogaNode optionsButtonNode;
    TextButton optionsButton;

    YogaNode quitButtonNode;
    TextButton quitButton;

    Skin CurrentSkin = new Skin(Gdx.files.internal("uiskin.json"));

    
	public FirstScreen(final Main game) {

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera); 
        viewport.apply();

		this.game = game;

        TextureAtlas atlas = new TextureAtlas("./ui/uiskin.atlas");
        // System.out.println(System.getProperty("user.dir"));
        // System.out.flush();

        CurrentSkin.addRegions(atlas);

        backgroundTexture = new Texture(Gdx.files.internal("ui/graphics/ui/LongfallMainMenuBackground.png"));

        titleFlexBox.setFillParent(true);
        titleFlexBox.getRoot()
            .setFlexDirection(YogaFlexDirection.ROW)
            .setWrap(YogaWrap.WRAP);
        stage.addActor(titleFlexBox);

        optionsFlexBox.setFillParent(true);
        optionsFlexBox.getRoot()
            .setFlexDirection(YogaFlexDirection.ROW)
            .setWrap(YogaWrap.WRAP);
        stage.addActor(optionsFlexBox);

        YogaNode titleParentNode = titleFlexBox.add()
            .setFlexDirection(YogaFlexDirection.COLUMN)
            .setBorder(YogaEdge.ALL, 25)
            .setBackground(CurrentSkin.newDrawable("textfield"))
            .setMargin(YogaEdge.LEFT, 200)
            .setMargin(YogaEdge.TOP, 75);

		Label title = new Label("Longfall", CurrentSkin);
            title.setAlignment(Align.center);
            title.setColor(Color.BROWN);
            title.setFontScale(4.0f);
            titleFlexBox.addAsChild(titleParentNode, title);


        TextButton.TextButtonStyle defaultStyle = new TextButton.TextButtonStyle();
        defaultStyle.font = CurrentSkin.getFont("default-font");
        defaultStyle.fontColor = Color.BROWN;
        defaultStyle.up = CurrentSkin.newDrawable("button-normal");
        defaultStyle.down = CurrentSkin.newDrawable("button-normal-pressed");

        playButton = new TextButton("Play", defaultStyle);
        playButton.getLabel().setFontScale(2.5f);

        playButtonNode = optionsFlexBox.add(playButton)
            .setFlexDirection(YogaFlexDirection.COLUMN)
            .setBorder(YogaEdge.ALL, 25)
            .setMargin(YogaEdge.LEFT, 50)
            .setMargin(YogaEdge.TOP, 275)
            .setWidth(150)
            .setHeight(75);

        playButton.addListener(playButtonListener);
        playButton.setTouchable(Touchable.enabled);

        optionsButton = new TextButton("Options", defaultStyle);
        optionsButton.getLabel().setFontScale(2.5f);

        optionsButtonNode = optionsFlexBox.add(optionsButton)
            .setFlexDirection(YogaFlexDirection.COLUMN)
            .setBorder(YogaEdge.ALL, 25)
            .setMargin(YogaEdge.LEFT, 50)
            .setMargin(YogaEdge.TOP, 275)
            .setWidth(200)
            .setHeight(75);

        optionsButton.addListener(optionsButtonListener);
        optionsButton.setTouchable(Touchable.enabled);

        quitButton = new TextButton("Quit", defaultStyle);
        quitButton.getLabel().setFontScale(2.5f);

        quitButtonNode = optionsFlexBox.add(quitButton)
            .setFlexDirection(YogaFlexDirection.COLUMN)
            .setBorder(YogaEdge.ALL, 25)
            .setMargin(YogaEdge.LEFT, 50)
            .setMargin(YogaEdge.TOP, 275)
            .setWidth(200)
            .setHeight(75);

        quitButton.addListener(quitButtonListener);
        quitButton.setTouchable(Touchable.enabled);

        //stage.setDebugAll(true);
	}

    ClickListener playButtonListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            dispose();
            game.setScreen(new LevelSelectScreen(game));
        }
    };

    ClickListener optionsButtonListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            dispose();
            game.setScreen(new OptionsScreen(game));
        }
    };
    
    ClickListener quitButtonListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            dispose();
            Gdx.app.exit();
        }
    };
    

    @Override
    public void show() {
        // This method is called when this screen becomes the current screen for a Game.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        game.batch.end();

        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }
    
}