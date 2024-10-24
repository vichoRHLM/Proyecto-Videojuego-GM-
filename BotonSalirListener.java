package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotonSalirListener extends ChangeListener {
	
	public void changed(ChangeEvent event, Actor actor) {
        Gdx.app.exit();
    }
	
}