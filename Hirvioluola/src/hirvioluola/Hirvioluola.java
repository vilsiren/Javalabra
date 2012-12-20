
package hirvioluola;

import hirvioluola.domain.Orkki;
import hirvioluola.domain.Pelaaja;
import hirvioluola.loitsut.Parannus;
import hirvioluola.loitsut.Salama;
import hirvioluola.peli.Taistelu;

public class Hirvioluola {

    private static Taistelu taistelu;
    private static Pelaaja pelaaja;
    private static Orkki orkki1;
    private static Orkki orkki2;    

    public static void main(String[] args) {
        pelaaja = new Pelaaja(0,0,1,15,15);
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
        taistelu.suorita();
    }
}
