/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tributacao.main;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 *
 * @author gusta
 */
public class Mp3Player {
    
    public static void iniciaSom() {
        // Inicializa o JavaFX Toolkit para uso
        JFXPanel jfxPanel = new JFXPanel();

        String mp3FilePath = "src\\main\\java\\tributacao\\main\\som.mp3"; // Caminho do arquivo MP3

        // Cria um objeto de mídia com o arquivo MP3
        Media media = new Media(new File(mp3FilePath).toURI().toString());

        // Cria um objeto MediaPlayer com a mídia
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Reproduz a mídia
        mediaPlayer.play();
    }
    
}
