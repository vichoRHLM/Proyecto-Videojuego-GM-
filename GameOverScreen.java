package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	private final GameLluviaMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Texture imagenDeFondo;
	private Music outroMusic;
	
	public GameOverScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        imagenDeFondo = new Texture(Gdx.files.internal("gameOver.png"));
        
        outroMusic = Gdx.audio.newMusic(Gdx.files.internal("gameOverMusic.mp3"));
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		outroMusic.setLooping(true);
		outroMusic.play();
		
		//definir fondo pantalla
		Gdx.gl.glClearColor(0,0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(imagenDeFondo, 0, 0, 800, 480);
		
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 170, 220);
		batch.end();
		
		if (Gdx.input.isTouched()) {
			outroMusic.pause();
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
