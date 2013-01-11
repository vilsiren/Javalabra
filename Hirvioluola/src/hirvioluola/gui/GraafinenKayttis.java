/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hirvioluola.gui;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Suuntaloitsu;
import hirvioluola.peli.Hirvioluolakayttis;
import hirvioluola.peli.Peli;
import hirvioluola.peli.Taistelu;
import hirvioluola.peli.Tekstikayttis;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author Ville
 */
public class GraafinenKayttis extends Tekstikayttis implements Runnable{
    
    private Piirtoalusta piirtoalusta;
    private JFrame ikkuna;
    private JLabel pelaajanStatus;

    @Override
    public void paivita() {
        naytaKuvaaXmillisekuntia(100);
    }
    
    private void naytaKuvaaXmillisekuntia(int millisekuntia){
        pelaajanStatus.setText(super.peli.getPelaaja().status());
        piirtoalusta.repaint();
         try{
            Thread.sleep(millisekuntia);
        }
        catch(Exception e){        
        }               
    }

    public void luoKomponentit(Container container) {
        
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        pelaajanStatus = new JLabel();
        pelaajanStatus.setPreferredSize(new Dimension(500,100));
        piirtoalusta = new Piirtoalusta();        
        container.add(pelaajanStatus);        
        container.add(piirtoalusta);
        
    }
    
    @Override
    public void setTaistelu(Taistelu taistelu){
        super.setTaistelu(taistelu);
        piirtoalusta.setTaistelu(taistelu);
    }
    
    @Override
    public void valitseRuutu(Ruutuloitsu loitsu){
        super.valitseRuutu(loitsu);
    }

    @Override
    public void piirraHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY) {
        piirtoalusta.setHyokkays(hyokkaajaX,hyokkaajaY,kohdeX,kohdeY);
        naytaKuvaaXmillisekuntia(200);
    }

    @Override
    public void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY) {
        piirtoalusta.setLoitsu(loitsu,loitsijaX,loitsijaY);
        naytaKuvaaXmillisekuntia(300);
    }
    
    public Piirtoalusta getPiirtoalusta(){
        return piirtoalusta;
    }

    @Override
    public void run() {
        ikkuna = new JFrame("Hirvioluola");
        ikkuna.setPreferredSize(new Dimension(500,505));
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        luoKomponentit(ikkuna.getContentPane());
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    
}
