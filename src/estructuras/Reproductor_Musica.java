package estructuras;
/**
 *
 * @author Dennis
 */
import java.io.IOException;
import javax.sound.sampled.*;

public class Reproductor_Musica implements Runnable 
{
    private Clip cancion;
    private FloatControl controladorVolumen;
    private static Reproductor_Musica instancia; // Variable para almacenar la instancia única
    public boolean reproduciendo;

    private Reproductor_Musica() 
    {
        try 
        {
            cancion = AudioSystem.getClip();
            cancion.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/cancion/feid.wav")));
            controladorVolumen = (FloatControl) cancion.getControl(FloatControl.Type.MASTER_GAIN);
            controladorVolumen.setValue(-30.0f);
            reproduciendo = false;
        } 
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) 
        {
            e.printStackTrace();
        }
    }

    public static Reproductor_Musica getInstance() 
    { // Método para obtener la instancia única
        if (instancia == null) 
        {
            instancia = new Reproductor_Musica();
        }
        return instancia;
    }

    public void run() 
    {
        reproduciendo = true;
        cancion.start();
        cancion.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pausar() 
    {
        if (cancion != null && cancion.isRunning()) 
        {
            cancion.stop();
            reproduciendo = false;
        }
    }

    public void reanudar() 
    {
        if (cancion != null && !reproduciendo) 
        {
            cancion.start();
            reproduciendo = true;
        }
    }

    public void ajustarVolumen(float valor) 
    {
        if (controladorVolumen != null) 
        {
            controladorVolumen.setValue(valor);
        }
    }
}