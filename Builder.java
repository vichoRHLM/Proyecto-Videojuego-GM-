package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public interface Builder {
	public void setFondo(Texture fondo);
	public Texture getFondo();
	public void destruir();
}
