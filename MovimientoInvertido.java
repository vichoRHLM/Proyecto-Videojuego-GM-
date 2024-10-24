package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

/**
 * Clase que implementa una estrategia de movimiento 
 * (Movimiento invertido del personaje)
 */
public class MovimientoInvertido implements EstrategiaMovimiento {
	
	
	
	@Override
	public void mover(Rectangle hitBoxMono, int velx) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			hitBoxMono.x += velx * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	hitBoxMono.x -= velx * Gdx.graphics.getDeltaTime();
        }
        
        // Limitar dentro de los bordes de la pantalla
        if (hitBoxMono.x < 0) hitBoxMono.x = 0;
        if (hitBoxMono.x > 800 - 64) hitBoxMono.x = 800 - 64;
	}
}
