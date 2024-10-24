package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class CaidaPlatanos {
	private Array<Platano> bananaDropsType;
	private long lastDropTime;
	private Sound dropSound;
	private Music jungleMusic;
	private Texture platanoPodridoTexture;
	private Texture platanoNormalTexture;
	private Texture platanoDoradoTexture;
	
	
	
	public CaidaPlatanos(Sound dropSound, Music jungleMusic,
			Texture platanoDoradoTexture, Texture platanoPodridoTexture, Texture  platanoNormalTexture) {
		this.dropSound = dropSound;
		this.jungleMusic = jungleMusic;
		this.platanoPodridoTexture = platanoPodridoTexture;
		this.platanoNormalTexture = platanoNormalTexture;
		this.platanoDoradoTexture = platanoDoradoTexture;
	}

	public void crear() {
		bananaDropsType = new Array<Platano>();
		crearBananaDrops();
	      // start the playback of the background music immediately
			jungleMusic.setLooping(true);
			jungleMusic.play();
	}
	
	private void crearBananaDrops() {
	
		Platano nuevoPlatano;

        int tipoPlatano = MathUtils.random(1, 100);
        
        if (tipoPlatano < 6) {
        	nuevoPlatano = new PlatanoDorado(platanoDoradoTexture, dropSound);
        	bananaDropsType.add(nuevoPlatano);
        } else if (tipoPlatano < 35) {
        	nuevoPlatano = new PlatanoPodrido(platanoPodridoTexture, dropSound);
        	bananaDropsType.add(nuevoPlatano);
        } else {
        	nuevoPlatano = new PlatanoNormal(platanoNormalTexture, dropSound);
        	bananaDropsType.add(nuevoPlatano);
        }	
	      lastDropTime = TimeUtils.nanoTime();
	      
	   }
	
   public boolean actualizarMovimiento(GranMono GM) { 
	   // generar lluvia platanos
	   if(TimeUtils.nanoTime() - lastDropTime > 150000000) crearBananaDrops();

	   // Ciclo que revisa si los platanos cayeron al suelo (1) o chocaron con el personaje (1)
	   for (int i=0; i < bananaDropsType.size; i++ ) {
		  // auxiliar de tipo platano
		  Platano platanoEnArray = bananaDropsType.get(i);
		  
		  platanoEnArray.getHitbox().y -= 300 * Gdx.graphics.getDeltaTime();
		  
	      //cae al suelo y se elimina (1)
	      if(platanoEnArray.getHitbox().y + 64 < 0) { 
	    	  bananaDropsType.removeIndex(i);
	      }
	    //platano choca con el personaje (2)
	      if(platanoEnArray.colisionaConHitBoxMono(GM.getArea())) { 
	    		platanoEnArray.efectoPlatano(GM);
	    	    bananaDropsType.removeIndex(i);
	      }
	   }
	  return GM.getVidas() > 0; 
   }
   
   public void actualizarDibujoCaidaBananas(SpriteBatch batch) { 
	   
	  for (int i=0; i < bananaDropsType.size; i++ ) {

		  Platano platanoEnArray = bananaDropsType.get(i);
		  Rectangle bananaDrop = platanoEnArray.getHitbox();
		  
		  platanoEnArray.draw(batch, bananaDrop);	  
	  }
   }
   
   public void destruir() {
      dropSound.dispose();
      jungleMusic.dispose();
   }
   public void pausar() {
	  jungleMusic.stop();
   }
   public void continuar() {
	  jungleMusic.play();
   }
}
