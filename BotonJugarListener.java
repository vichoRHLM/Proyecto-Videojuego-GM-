package puppy.code;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotonJugarListener extends ChangeListener {
    private GameLluviaMenu game;
    
    public BotonJugarListener(GameLluviaMenu game) {
        this.game = game;
    }
    

    public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new GameScreen(game));
        dispose();
    }

	private void dispose() {}

}