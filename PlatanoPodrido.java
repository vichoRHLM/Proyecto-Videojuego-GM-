package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlatanoPodrido extends Platano{
	
	private static Texture dropRottenPlatano = new Texture(Gdx.files.internal("Platano_podrido.png"));
	
	public PlatanoPodrido(Texture newTextureBanana, Sound ss) {
		super(newTextureBanana, ss);

	}
	@Override
	public void efectoPlatano(GranMono gm) {
		gm.dañar();
	}
	
	public void draw(SpriteBatch batch , Rectangle hitBox) {
		batch.draw(dropRottenPlatano, hitBox.x, hitBox.y);
	}
	
}
