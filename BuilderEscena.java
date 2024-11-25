package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class BuilderEscena implements Builder{

	private Texture imagenDeFondo;
	
	public void setFondo(Texture imagenDeFondo) {
		this.imagenDeFondo = imagenDeFondo;
	}

	public Texture getFondo() {
		return imagenDeFondo;
	}
	
	public void destruir() {
		imagenDeFondo.dispose();
	}
}
