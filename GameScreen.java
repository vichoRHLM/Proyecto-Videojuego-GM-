package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private GranMono monkey;
    private CaidaPlatanos dropsBananas;
    private Texture imagenDeFondo;
    private Sound gameOverSound;

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        initializeAssets();
        initializeGameElements();
    }

    private void initializeAssets() {
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("herida.mp3"));
        Texture dropGoldPlatano = new Texture(Gdx.files.internal("platano_dorado.png"));
        Texture dropRottenPlatano = new Texture(Gdx.files.internal("Platano_podrido.png"));
        Texture dropNormalPlatano = new Texture(Gdx.files.internal("Platano_normal.png"));
        
        imagenDeFondo = new Texture(Gdx.files.internal("jungleTheme.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("gameOverSound.mp3"));
        Music jungleMusic = Gdx.audio.newMusic(Gdx.files.internal("4-44. Scrapbook.mp3"));
        
        dropsBananas = new CaidaPlatanos(dropSound, jungleMusic, dropGoldPlatano, dropRottenPlatano, dropNormalPlatano);
        monkey = new GranMono(new Texture(Gdx.files.internal("GM.png")), hurtSound);
    }

    private void initializeGameElements() {
        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        // Initialize monkey and drop elements
        monkey.crear();
        dropsBananas.crear();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        renderBackground();
        renderText();
        
        if (!monkey.estaHerido()) {
            monkey.setEstrategiaMovimiento(new MovimientoNormal());
            if (!dropsBananas.actualizarMovimiento(monkey)) {
                handleGameOver();
            }
        }
        
        applyMovementStrategy();
        monkey.actualizarMovimiento();
        monkey.dibujar(batch);
        dropsBananas.actualizarDibujoCaidaBananas(batch);
        
        batch.end();
    }

    private void renderBackground() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(imagenDeFondo, 0, 0, 800, 480);
    }

    private void renderText() {
        font.draw(batch, "Bananas totales: " + monkey.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + monkey.getVidas(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);
    }

    private void handleGameOver() {
        gameOverSound.play();
        if (game.getHigherScore() < monkey.getPuntos()) {
            game.setHigherScore(monkey.getPuntos());
        }
        game.setScreen(new GameOverScreen(game));
        dispose();
    }

    private void applyMovementStrategy() {
        if (monkey.estaHerido()) {
            monkey.setEstrategiaMovimiento(new MovimientoHerido());
        } else if (monkey.estanControlesInvertidos()) {
            monkey.setEstrategiaMovimiento(new MovimientoInvertido());
            monkey.disminuirTiempoInvertido();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {
        dropsBananas.continuar();
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {
        dropsBananas.pausar();
        game.setScreen(new PausaScreen(game, this));
    }

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        monkey.destruir();
        dropsBananas.destruir();
    }
}