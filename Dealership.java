package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dealership {	
	
	public void EscenaInicio(Builder escena) {
		
		Texture imagenDeFondo = new Texture(Gdx.files.internal("jungleTheme.png"));
		
		escena.setFondo(imagenDeFondo);
    	
    }
	
	public void Escena2(Builder escena) {
		
		escena.destruir();
		
		Texture imagenDeFondo = new Texture(Gdx.files.internal("fondoEscena2.jpg"));
		
		escena.setFondo(imagenDeFondo);

    }
	
	public void Escena3(Builder escena) {
		
		escena.destruir();
		
		Texture imagenDeFondo = new Texture(Gdx.files.internal("fondoEscena3.png"));
		
		escena.setFondo(imagenDeFondo);

    }
	
}
