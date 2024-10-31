package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont fontButtons;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture imagenDeFondo;
    private Stage stage;
    private TextButton buttonJugar, buttonOpciones, buttonPersonaje, buttonSalir, buttonComoJugar;
    private TextButtonStyle textButtonStyle;
    private Texture flecha;
    
    
    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.fontButtons = game.getFont();
        this.font = game.getFontEspecial();
        font.setColor(Color.WHITE);
        fontButtons.setColor(Color.WHITE);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        imagenDeFondo = new Texture(Gdx.files.internal("menuPrincipal.png"));
        flecha = new Texture(Gdx.files.internal("flecha.png"));
        
        //Definir stage donde se dibujaran los botones.
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        //Definir estilo de boton.
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = fontButtons;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.downFontColor = Color.GREEN;

        //Creaccione de botones (nombres y tipos).
        buttonJugar = new TextButton("JUGAR", textButtonStyle);
        buttonOpciones = new TextButton("OPCIONES", textButtonStyle);
        buttonPersonaje = new TextButton("PERSONAJE", textButtonStyle);
        buttonSalir = new TextButton("SALIR", textButtonStyle);
        buttonComoJugar = new TextButton("?", textButtonStyle);
        
        
        //Posiciones de botones.
        buttonJugar.setPosition(520, 310);
        buttonOpciones.setPosition(510, 260);
        buttonPersonaje.setPosition(505, 210);
        buttonSalir.setPosition(525, 160);
        buttonComoJugar.setPosition(20, 20);
        
        //Reacciones de botones.
        buttonJugar.addListener(new BotonJugarListener(game));
        buttonOpciones.addListener(new BotonOpcionesListener(game));    // Falta OpcionesScreen
        buttonPersonaje.addListener(new BotonPersonajeListener(game));  // Falta PersonajesScreen
        buttonSalir.addListener(new BotonSalirListener());
        buttonComoJugar.addListener(new BotonComoJugarListener(game));          // Falta TutorialScreen 
        
        //Se agregan los botones en el stage.
        stage.addActor(buttonJugar);
        stage.addActor(buttonOpciones);
        stage.addActor(buttonPersonaje);
        stage.addActor(buttonSalir);
        stage.addActor(buttonComoJugar);
        
    }
    
    
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        // Dibujar el fondo
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(imagenDeFondo, 0, 0, 800, 480);
        batch.draw(flecha, 36, 33, 50, 50);
        
        font.getData().setScale(1.5f, 1.5f);
        fontButtons.getData().setScale(2, 2);
        font.draw(batch, "COMO JUGAR", 86 , 95);
        batch.end();
        
        // Dibujar y actualizar el stage
        stage.act(delta);
        stage.draw();
    }
    
    @Override
    public void dispose() {
        stage.dispose();
        imagenDeFondo.dispose();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.setToOrtho(false, width, height);
    }
    
    @Override
    public void show() {}
    
    @Override
    public void hide() {}
    
    @Override
    public void pause() {}
    
    @Override
    public void resume() {}
}
