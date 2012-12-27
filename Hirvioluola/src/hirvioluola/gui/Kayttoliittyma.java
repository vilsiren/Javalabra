
package hirvioluola.gui;

import hirvioluola.peli.Taistelu;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {
    private JFrame frame;   
    private Piirtoalusta alusta;
    private JLabel status;
    private Taistelu taistelu;
    private final int sivunPituus = 20;
    
    public Kayttoliittyma(Taistelu taistelu) {
        this.taistelu = taistelu;        
    }
 
    @Override
    public void run() {
        frame = new JFrame("Hirviöluola");
        int leveys = (taistelu.getLeveys()+1)*sivunPituus+10;
        int korkeus = (taistelu.getKorkeus()+2)*sivunPituus+10;
         
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }

    public Piirtoalusta getAlusta() {
        return alusta;
    }
    
    public JLabel getStatus(){
        return status;
    }
 
    public void luoKomponentit(Container container) {
        alusta = new Piirtoalusta(taistelu);
        alusta.setFocusable(true);
        status = new JLabel();
        status.setFocusable(true);
        GridLayout layout = new GridLayout(2, 1);
        container.setLayout(layout);
        container.add(status);
        container.add(alusta);
        frame.addKeyListener(new Nappaimistonkuuntelija(taistelu));
    }
  
    public JFrame getFrame() {
        return frame;
    }    
}
