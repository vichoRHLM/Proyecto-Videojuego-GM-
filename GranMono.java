package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class GranMono {
	   private Rectangle monkey;
	   private Texture monkeyImage;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   
	   public GranMono(Texture tex, Sound ss) {
		   monkeyImage = tex;
		   sonidoHerido = ss;
	   }
	   
		public int getVidas() {
			return vidas;
		}
	
		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return monkey;
		}
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		
	
	   public void crear() {
			   monkey = new Rectangle();
			   monkey.x = 800 / 2 - 64 / 2;
			   monkey.y = 20;
			   monkey.width = 64;
			   monkey.height = 64;
	   }
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   batch.draw(monkeyImage, monkey.x, monkey.y);
		 else {
		
		   batch.draw(monkeyImage, monkey.x, monkey.y+ MathUtils.random(-5,5));
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   } 
	   
	   
	   public void actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) monkey.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) monkey.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(monkey.x < 0) monkey.x = 0;
		   if(monkey.x > 800 - 64) monkey.x = 800 - 64;
	   }
	    

	public void destruir() {
			monkeyImage.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
