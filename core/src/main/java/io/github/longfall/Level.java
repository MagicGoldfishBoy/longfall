package io.github.longfall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dev.lyze.flexbox.FlexBox;
import io.github.orioncraftmc.meditate.YogaNode;
import io.github.orioncraftmc.meditate.enums.YogaEdge;
import io.github.orioncraftmc.meditate.enums.YogaFlexDirection;
import io.github.orioncraftmc.meditate.enums.YogaWrap;

abstract class Level implements Screen {

    final Main game;

    private OrthographicCamera camera;
    private Viewport viewport;

    Texture backgroundTexture;

    Stage Stage;

    TextureAtlas atlas;

    Skin CurrentSkin;

    Rectangle Infobox;

    FlexBox InfoFlexBox;

    TextButtonStyle textButtonStyleDefault;

    YogaNode backButtonNode;

    TextButton backButton;
   

    public Level(Main game) {

        this.game = game;

        Stage Stage = new Stage(new ScreenViewport());

        TextureAtlas atlas = new TextureAtlas("ui/uiskin.atlas");
            CurrentSkin.addRegions(atlas);

        Skin CurrentSkin = new Skin(Gdx.files.internal("uiskin.json"));

        Rectangle Infobox = new Rectangle();

        FlexBox InfoFlexBox = new FlexBox();
        InfoFlexBox.getRoot()
            .setFlexDirection(YogaFlexDirection.ROW)
            .setWrap(YogaWrap.WRAP);

        TextButtonStyle defaultStyle = new TextButtonStyle();
            defaultStyle.font = CurrentSkin.getFont("default-font");
            defaultStyle.fontColor = Color.BROWN;
            defaultStyle.up = CurrentSkin.newDrawable("button-normal");
            defaultStyle.down = CurrentSkin.newDrawable("button-normal-pressed");

        TextButton backButton = new TextButton("Back", defaultStyle);
        backButton.getLabel().setFontScale(2.5f);

        backButtonNode = InfoFlexBox.add(backButton)
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
            Gdx.app.postRunnable(() -> game.setScreen(new LevelSelectScreen(game)));
        }
    };

    @Override
    public void show() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hide'");
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
    
}
