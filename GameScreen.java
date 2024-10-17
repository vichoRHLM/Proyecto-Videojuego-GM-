package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private GranMono monkey;
	private CaidaPlatanos dropsBananas;
	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("herida.mp3"));
		  monkey = new GranMono(new Texture(Gdx.files.internal("GM.png")),hurtSound);
		  
	      // load the drop sound effect and the rain background "music" 
         Texture dropGoldPlatano = new Texture(Gdx.files.internal("platano_dorado.png"));
         Texture dropRottenPlatano = new Texture(Gdx.files.internal("Platano_podrido.png"));
         Texture dropNormalPlatano = new Texture(Gdx.files.internal("Platano_normal.png"));
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        
	     Music jungleMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

	     dropsBananas = new CaidaPlatanos(dropSound,jungleMusic,dropGoldPlatano,dropRottenPlatano,dropNormalPlatano);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      monkey.crear();
	      
	      // creacion de la lluvia
	      dropsBananas.crear();
	      
	      
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
			
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//dibujar textos
		font.draw(batch, "Bananas totales: " + monkey.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + monkey.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!monkey.estaHerido()) {
			// movimiento del tarro desde teclado
			monkey.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!dropsBananas.actualizarMovimiento(monkey) ) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<monkey.getPuntos())
	    		  game.setHigherScore(monkey.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		monkey.dibujar(batch);
		dropsBananas.actualizarDibujoCaidaBananas(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
		dropsBananas.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		dropsBananas.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	  monkey.destruir();
	  dropsBananas.destruir();

	}

}
