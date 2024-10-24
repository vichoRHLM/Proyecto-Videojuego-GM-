package puppy.code;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotonComoJugarListener extends ChangeListener{
	private GameLluviaMenu game;
    
    public BotonComoJugarListener(GameLluviaMenu game) {
        this.game = game;
    }
    
    public void changed(ChangeEvent event, Actor actor) {
        //game.setScreen(new TutorialScreen(game));
        dispose();
    }

	private void dispose() {}
}