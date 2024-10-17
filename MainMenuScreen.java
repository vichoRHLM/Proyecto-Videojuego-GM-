package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen implements Screen {

	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Texture imagenDeFondo;
	
	
	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        font.setColor(Color.CHARTREUSE);
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		imagenDeFondo = new Texture(Gdx.files.internal("donkin.jpg"));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		
		//definir fondo pantalla
		Gdx.gl.glClearColor(0,0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(imagenDeFondo, 0, 0, 1280, 720);
		
		font.getData().setScale(2, 2);
		
		font.draw(batch, "Bienvenido a Recolecta Bananas!!! ", Gdx.graphics.getWidth() / 3, camera.viewportHeight/2+50);
		font.draw(batch, "Toca en cualquier lugar para comenzar!", Gdx.graphics.getWidth() / 3 - 30, camera.viewportHeight/2-50);

		batch.end();

		if (Gdx.input.isTouched()) {
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
