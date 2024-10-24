package puppy.code;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotonPersonajeListener extends ChangeListener {
    private GameLluviaMenu game;
    
    public BotonPersonajeListener(GameLluviaMenu game) {
        this.game = game;
    }

    
    public void changed(ChangeEvent event, Actor actor) {
        //game.setScreen(new PersonajeScreen(game));
        dispose();
    }

	private void dispose() {}

}