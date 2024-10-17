package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlatanoNormal extends Platano{

	private static Texture dropNormalPlatano = new Texture(Gdx.files.internal("Platano_normal.png"));
	
	public PlatanoNormal(Texture newTextureBanana, Sound ss) {
		super(newTextureBanana, ss);

	}
	
	public void efectoPlatano(GranMono gm) {
		gm.sumarPuntos(10);
		sonidoColision.play();
	}
	
	public void draw(SpriteBatch batch , Rectangle hitBox) {
		batch.draw(dropNormalPlatano, hitBox.x, hitBox.y);
	}
	
}
