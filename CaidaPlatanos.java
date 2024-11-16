package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class CaidaPlatanos {
	//Implementacion del patron Singleton
	private static CaidaPlatanos instancia;
	
    private Array<Platano> bananaDropsType;  //Array de clase abstracta platano
    private long lastDropTime;
    private final Sound dropSound;
    private final Music jungleMusic;
    private final Texture platanoPodridoTexture;
    private final Texture platanoNormalTexture;
    private final Texture platanoDoradoTexture;

    //Implementacion del patron Singleton
    private CaidaPlatanos(Sound dropSound, Music jungleMusic, Texture platanoDoradoTexture, Texture platanoPodridoTexture, Texture platanoNormalTexture) {
        this.dropSound = dropSound;
        this.jungleMusic = jungleMusic;
        this.platanoPodridoTexture = platanoPodridoTexture;
        this.platanoNormalTexture = platanoNormalTexture;
        this.platanoDoradoTexture = platanoDoradoTexture;
    }
    
    //Implementacion del patron Singleton
    public static CaidaPlatanos getInstancia(Sound dropSound, Music jungleMusic, Texture platanoDoradoTexture, Texture platanoPodridoTexture, Texture platanoNormalTexture) {
    	if (CaidaPlatanos.instancia == null)
    		CaidaPlatanos.instancia = new CaidaPlatanos(dropSound, jungleMusic, platanoDoradoTexture, platanoPodridoTexture, platanoPodridoTexture);
    	return CaidaPlatanos.instancia;
    }

    public void crear() {
        bananaDropsType = new Array<>();
        crearBananaDrops();
        iniciarMusicaDeFondo();
    }

    private void iniciarMusicaDeFondo() {
        jungleMusic.setLooping(true);
        jungleMusic.play();
    }

    private void crearBananaDrops() {
        Platano nuevoPlatano = generarTipoPlatano();
        bananaDropsType.add(nuevoPlatano);
        lastDropTime = TimeUtils.nanoTime();
    }

    private Platano generarTipoPlatano() {
        int tipoPlatano = MathUtils.random(1, 100);
        if (tipoPlatano < 6) {
            return new PlatanoDorado(platanoDoradoTexture, dropSound);
        } else if (tipoPlatano < 35) {
            return new PlatanoPodrido(platanoPodridoTexture, dropSound);
        } else {
            return new PlatanoNormal(platanoNormalTexture, dropSound);
        }
    }

    public boolean actualizarMovimiento(GranMono granMono) {
        if (TimeUtils.nanoTime() - lastDropTime > 150000000) {
            crearBananaDrops();
        }
        return procesarColisiones(granMono);
    }

    private boolean procesarColisiones(GranMono granMono) {
        for (int i = 0; i < bananaDropsType.size; i++) {
            Platano platano = bananaDropsType.get(i);
            actualizarPosicionPlatano(platano);

            if (haCaidoAlSuelo(platano)) {
                bananaDropsType.removeIndex(i);
            } else if (platano.colisionaConHitBoxMono(granMono.getArea())) {
                platano.efectoPlatano(granMono);
                bananaDropsType.removeIndex(i);
            }
        }
        return granMono.getVidas() > 0;
    }

    private void actualizarPosicionPlatano(Platano platano) {
        platano.getHitbox().y -= 300 * Gdx.graphics.getDeltaTime();
    }

    private boolean haCaidoAlSuelo(Platano platano) {
        return platano.getHitbox().y + 64 < 0;
    }

    public void actualizarDibujoCaidaBananas(SpriteBatch batch) {
        for (Platano platano : bananaDropsType) {
            platano.draw(batch, platano.getHitbox());
        }
    }

    public void destruir() {
        dropSound.dispose();
        jungleMusic.dispose();
    }

    public void pausar() {
        jungleMusic.stop();
    }

    public void continuar() {
        jungleMusic.play();
    }
}