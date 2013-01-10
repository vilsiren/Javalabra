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
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Ville
 */
public class GraafinenKayttis extends Tekstikayttis implements Runnable{
    
    private Piirtoalusta piirtoalusta;
    private JFrame ikkuna;

    @Override
    public void paivita() {
        piirtoalusta.repaint();
        try{
            Thread.sleep(100);
        }
        catch(Exception e){        
    }
    }

    public void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta();
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
        
    }

    @Override
    public void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY) {

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
