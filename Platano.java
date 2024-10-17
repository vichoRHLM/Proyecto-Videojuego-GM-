package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public abstract class Platano {
	protected Texture Platano;
	protected Sound sonidoColision;   
	protected Rectangle posicion;
	
	public Platano(Texture nuevaTexture, Sound nuevoSonido) {
		
		posicion = new Rectangle();
		
		Platano = nuevaTexture;
		sonidoColision = nuevoSonido;
		
		posicion.x = MathUtils.random(0, 800-64);
		posicion.y = 480;
		posicion.width = 64;
		posicion.height = 64;
		
	}
	
	public boolean colisionaConHitBoxMono(Rectangle areaMono) {
        return posicion.overlaps(areaMono);
    }
	
	public Rectangle getHitbox() {
		return posicion;
	}
	
	public abstract void draw(SpriteBatch batch , Rectangle hitBox);
	public abstract void efectoPlatano(GranMono gm);
	
}
