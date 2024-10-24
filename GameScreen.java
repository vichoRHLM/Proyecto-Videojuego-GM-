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
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private GranMono monkey;
	private CaidaPlatanos dropsBananas;
	private Texture imagenDeFondo;
	private Sound gameOverSound;
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("herida.mp3"));
		  
	      // load the drop sound effect and the rain background "music" 
         Texture dropGoldPlatano = new Texture(Gdx.files.internal("platano_dorado.png"));
         Texture dropRottenPlatano = new Texture(Gdx.files.internal("Platano_podrido.png"));
         Texture dropNormalPlatano = new Texture(Gdx.files.internal("Platano_normal.png"));
         
         imagenDeFondo = new Texture(Gdx.files.internal("jungleTheme.png"));
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
         gameOverSound = Gdx.audio.newSound(Gdx.files.internal("gameOverSound.mp3"));
        		 
	     Music jungleMusic = Gdx.audio.newMusic(Gdx.files.internal("4-44. Scrapbook.mp3"));

	     dropsBananas = new CaidaPlatanos(dropSound,jungleMusic,dropGoldPlatano,dropRottenPlatano,dropNormalPlatano);
	      
	     monkey = new GranMono(new Texture(Gdx.files.internal("GM.png")),hurtSound);
	     
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
		
		//definir fondo pantalla
		Gdx.gl.glClearColor(0,0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(imagenDeFondo, 0, 0, 800, 480);
		
		//dibujar textos
		font.draw(batch, "Bananas totales: " + monkey.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + monkey.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if(!monkey.estaHerido()) {
			// Estrategia de movimiento normal del personaje
			monkey.setEstrategiaMovimiento(new MovimientoNormal());
			// caida de palatanos
	       if (!dropsBananas.actualizarMovimiento(monkey) ) {
	    	  
	    	   gameOverSound.play();
	    	 //actualizar HigherScore
	    	  if (game.getHigherScore()<monkey.getPuntos())
	    		  game.setHigherScore(monkey.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		} 
		
		if(monkey.estaHerido()) { 
			
			// Estrategia de movimiento mientras esta herido (por el efecto del platano podrido)
			monkey.setEstrategiaMovimiento(new MovimientoHerido());
			
		} else if(monkey.estanControlesInvertidos()) {
				// Estrategia de movimiento mientras estan invertidos los controles (por el efecto del platano dorado)
				monkey.setEstrategiaMovimiento(new MovimientoInvertido());
				monkey.disminuirTiempoInvertido();
		}
		
		
		// movimiento del personaje desde teclado (Patron Strategy)
		monkey.actualizarMovimiento(); 
	
		
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
