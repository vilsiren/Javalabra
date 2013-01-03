
package hirvioluola;

import hirvioluola.domain.Este;
import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.TeleportHirvio;
import hirvioluola.gui.Kayttoliittyma;
import hirvioluola.gui.Piirtoalusta;
import hirvioluola.loitsut.KutsuLiittolainen;
import hirvioluola.loitsut.Parannus;
import hirvioluola.loitsut.Salama;
import hirvioluola.loitsut.Taikanuoli;
import hirvioluola.loitsut.Teleportaatio;
import hirvioluola.loitsut.Tulikeha;
import hirvioluola.peli.Taistelu;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Hirvioluola {
   

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Pelaaja pelaaja = new Pelaaja(2,100,100);
        pelaaja.lisaaLoitsu(new Parannus(2,5));
        pelaaja.lisaaLoitsu(new Salama(4,6));
        pelaaja.lisaaLoitsu(new Teleportaatio(2));
        pelaaja.lisaaLoitsu(new Taikanuoli(2,5, true));
        pelaaja.lisaaLoitsu(new Tulikeha(3,2));
        pelaaja.lisaaLoitsu(new KutsuLiittolainen(5, 3, 15));
        Taistelu taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(pelaaja,0,0);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),3,3);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),3,2);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),2,4);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),1,4);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),6,8);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),7,8);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),8,8);
        taistelu.lisaaOlio(new TeleportHirvio(4,10,10),9,3);
        taistelu.lisaaOlio(new Este(5,'e'),0,1);
        taistelu.lisaaOlio(new Este(5,'e'),1,0);
        taistelu.lisaaOlio(new Este(5,'e'),1,1);
        taistelu.suorita();
//        Kayttoliittyma kali = new Kayttoliittyma(taistelu);
//        
//        SwingUtilities.invokeLater(kali);
//        while (kali.getAlusta() == null) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                System.out.println("Piirtoalustaa ei ole vielä luotu.");
//            }
//        }        
//        taistelu.setAlusta(kali.getAlusta());
//        taistelu.setStatus(kali.getStatus());
        
        

        
        

    }
}
