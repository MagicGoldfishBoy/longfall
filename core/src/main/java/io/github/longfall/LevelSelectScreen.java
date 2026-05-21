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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dev.lyze.flexbox.FlexBox;
import io.github.orioncraftmc.meditate.YogaNode;
import io.github.orioncraftmc.meditate.enums.YogaEdge;
import io.github.orioncraftmc.meditate.enums.YogaFlexDirection;
import io.github.orioncraftmc.meditate.enums.YogaWrap;

public class LevelSelectScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;

    final Main game;

    Texture backgroundTexture;

    Stage stage = new Stage(new ScreenViewport());

    Skin CurrentSkin = new Skin(Gdx.files.internal("uiskin.json"));


    FlexBox optionsFlexBox = new FlexBox();


    YogaNode backButtonNode;
    TextButton backButton;


    public LevelSelectScreen(Main game) {

        this.game = game;

    }

    @Override
    public void show() {

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera); 
        viewport.apply();

        TextureAtlas atlas = new TextureAtlas("./ui/uiskin.atlas");

        CurrentSkin.addRegions(atlas);

        backgroundTexture = new Texture(Gdx.files.internal("ui/graphics/ui/LongfallMainMenuBackground.png"));

        optionsFlexBox.setFillParent(true);
        optionsFlexBox.getRoot()
            .setFlexDirection(YogaFlexDirection.ROW)
            .setWrap(YogaWrap.WRAP);
        stage.addActor(optionsFlexBox);

        TextButton.TextButtonStyle defaultStyle = new TextButton.TextButtonStyle();
        defaultStyle.font = CurrentSkin.getFont("default-font");
        defaultStyle.fontColor = Color.BROWN;
        defaultStyle.up = CurrentSkin.newDrawable("button-normal");
        defaultStyle.down = CurrentSkin.newDrawable("button-normal-pressed");

        backButton = new TextButton("Back", defaultStyle);
        backButton.getLabel().setFontScale(2.5f);

        backButtonNode = optionsFlexBox.add(backButton)
            .setFlexDirection(YogaFlexDirection.COLUMN)
            .setBorder(YogaEdge.ALL, 25)
            .setMargin(YogaEdge.LEFT, 50)
            .setMargin(YogaEdge.TOP, 75)
            .setWidth(150)
            .setHeight(75);

        backButton.addListener(BackButtonListener);
        backButton.setTouchable(Touchable.enabled);

    }

    ClickListener BackButtonListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            dispose();
            game.setScreen(new FirstScreen(game));
        }
    };

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
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {

        backgroundTexture.dispose();
        stage.dispose();

    }
    
}
