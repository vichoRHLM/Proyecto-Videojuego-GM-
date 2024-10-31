package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class PlatanoDorado extends Platano{
	
	private static Texture dropGoldPlatano = new Texture(Gdx.files.internal("platano_dorado.png"));
	
	public PlatanoDorado(Texture newTextureBanana, Sound ss) {
		super(newTextureBanana, ss);

	}
	@Override
	public void efectoPlatano(GranMono gm) {
		gm.sumarPuntos(100);
		gm.invertirControles();
		sonidoColision.play();
	}
	
	public void draw(SpriteBatch batch , Rectangle hitBox) {
		batch.draw(dropGoldPlatano, hitBox.x, hitBox.y);
	}
	
	
}
