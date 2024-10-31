package puppy.code;

import com.badlogic.gdx.Gdx;
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
	   private boolean invertido = false;
	   private int tiempoInvertidoMax = 450;
	   private int tiempoInvertido;
       private Sound sonidoControlesInvertidos;
       private EstrategiaMovimiento moveStrategy;
	   
	   
	   public GranMono(Texture tex, Sound ss) {
		   monkeyImage = tex;
		   sonidoHerido = ss;
		   
		   sonidoControlesInvertidos = Gdx.audio.newSound(Gdx.files.internal("efectoDorado.mp3"));
	   }
	   
	   public void setEstrategiaMovimiento(EstrategiaMovimiento nuevaEstrategia) {
	        this.moveStrategy = nuevaEstrategia;
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
		  sonidoHerido.setVolume(1, 100);
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
	  
	   public void destruir() {
		   sonidoControlesInvertidos.stop();
		   monkeyImage.dispose();
	   }
	
	   public boolean estaHerido() {
		   return herido;
	   }
	   
	   public void invertirControles() {
		   sonidoControlesInvertidos.stop();
		   invertido = true;
		   tiempoInvertido = tiempoInvertidoMax; //Este valor se debe cambiar si se desea aumentar la duracion de los controles invertidos
		   sonidoControlesInvertidos.play();
	   }
	   
	   public boolean estanControlesInvertidos() {
		   return invertido;
	   }
	  
	   public void disminuirTiempoInvertido() {
		   // Reducir el temporizador
	       tiempoInvertido--;
		   
		   if (tiempoInvertido <= 0) {
	            invertido = false;
	       }
	   }
	   
	   public void actualizarMovimiento() {
		   	moveStrategy.mover(monkey, velx);
	   }
}
