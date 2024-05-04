/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tributacao.main;

/**
 *
 * @author gusta
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Random;

public class Main {
    private static final int[] COMUM_SEQUENCE = new int[600];
    private static final int[] PREFERENCIAL_SEQUENCE = new int[600];
    private static final Random RANDOM = new Random();
    private static int comumIndex = 0;
    private static int preferencialIndex = 0;

    public static void main(String[] args) {
        gerarSequencias();
        
        SwingUtilities.invokeLater(() -> new MainFrame());
        
    }

    private static void gerarSequencias() {
        for (int i = 0; i < 600; i++) {
            COMUM_SEQUENCE[i] = i + 1;
            PREFERENCIAL_SEQUENCE[i] = i + 1;
        }
    }

    public static String chamarSenha() {
        int mesa = RANDOM.nextInt(5) + 1;
        String senhaChamada;
        if (RANDOM.nextBoolean()) {
            senhaChamada = "C" + COMUM_SEQUENCE[comumIndex];
            comumIndex = (comumIndex + 1) % 100;
        } else {
            senhaChamada = "P" + PREFERENCIAL_SEQUENCE[preferencialIndex];
            preferencialIndex = (preferencialIndex + 1) % 100;
        }
        return senhaChamada + " - Mesa: " + mesa;
    }
}

