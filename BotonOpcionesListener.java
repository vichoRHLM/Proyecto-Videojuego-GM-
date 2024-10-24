package puppy.code;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotonOpcionesListener extends ChangeListener {
    private GameLluviaMenu game;
    
    public BotonOpcionesListener(GameLluviaMenu game) {
        this.game = game;
    }
    

    public void changed(ChangeEvent event, Actor actor) {
        //game.setScreen(new OpcionesScreen(game));
        dispose();
    }

	private void dispose() {}

}