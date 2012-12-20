
package hirvioluola;

import hirvioluola.domain.Orkki;
import hirvioluola.domain.Pelaaja;
import hirvioluola.gui.Kayttoliittyma;
import hirvioluola.loitsut.Parannus;
import hirvioluola.loitsut.Salama;
import hirvioluola.peli.Taistelu;
import javax.swing.SwingUtilities;

public class Hirvioluola {

    private static Taistelu taistelu;
    private static Pelaaja pelaaja;
    private static Orkki orkki1;
    private static Orkki orkki2;    

    public static void main(String[] args) {
        pelaaja = new Pelaaja(0,0,1,1,15);
        pelaaja.lisaaLoitsu(new Parannus(2,5));
        pelaaja.lisaaLoitsu(new Salama(4,6));
        taistelu = new Taistelu(pelaaja,10,10);
        orkki1 = new Orkki(3,3,1,3,taistelu);
        orkki2 = new Orkki(3,2,1,3,taistelu); 
        taistelu.lisaaHirvio(orkki1);
        taistelu.lisaaHirvio(orkki2);
        taistelu.lisaaHirvio(new Orkki(2,4,1,3,taistelu));
        taistelu.lisaaHirvio(new Orkki(1,4,1,3,taistelu));
        taistelu.lisaaHirvio(new Orkki(6,8,1,3,taistelu));
        
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
        taistelu.start();
    }
}
