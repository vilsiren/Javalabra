
package hirvioluola;

import hirvioluola.domain.Hirvio;
import hirvioluola.domain.Pelaaja;
import hirvioluola.gui.Kayttoliittyma;
import hirvioluola.gui.Piirtoalusta;
import hirvioluola.loitsut.Parannus;
import hirvioluola.loitsut.Salama;
import hirvioluola.loitsut.Taikanuoli;
import hirvioluola.loitsut.Teleportaatio;
import hirvioluola.loitsut.Tulikeha;
import hirvioluola.peli.Taistelu;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Hirvioluola {

    private static Taistelu taistelu;
    private static Pelaaja pelaaja;
    private static Hirvio orkki1;
    private static Hirvio orkki2;    

    public static void main(String[] args) {
        pelaaja = new Pelaaja(0,0,1,100,100);
        pelaaja.lisaaLoitsu(new Parannus(2,5));
        pelaaja.lisaaLoitsu(new Salama(4,6));
        pelaaja.lisaaLoitsu(new Teleportaatio(2));
        pelaaja.lisaaLoitsu(new Taikanuoli(2,5, true));
        pelaaja.lisaaLoitsu(new Tulikeha(3,2));
        taistelu = new Taistelu(pelaaja,10,10);
        orkki1 = new Hirvio(3,3,1,3,0);
        orkki2 = new Hirvio(3,2,1,3,0); 
        taistelu.lisaaHirvio(orkki1);
        taistelu.lisaaHirvio(orkki2);
        taistelu.lisaaHirvio(new Hirvio(2,4,1,3,0));
        taistelu.lisaaHirvio(new Hirvio(1,4,1,3,0));
        taistelu.lisaaHirvio(new Hirvio(6,8,1,3,0));
        taistelu.lisaaHirvio(new Hirvio(7,8,1,3,0));
        taistelu.lisaaHirvio(new Hirvio(8,8,1,3,0));      
        //taistelu.suorita();
        Kayttoliittyma kali = new Kayttoliittyma(taistelu);
        
        SwingUtilities.invokeLater(kali);
        while (kali.getAlusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }        
        taistelu.setAlusta(kali.getAlusta());
        taistelu.setStatus(kali.getStatus());
        
        

        
        

    }
}
