
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Orkki;
import hirvioluola.domain.Pelaaja;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SalamaTest {
    
    private Salama salama;
    private Pelaaja pelaaja;
    private Orkki orkki;
    private Taistelu taistelu;
    private int vahinko;
    
    
    @Before
    public void setUp() {
        vahinko = 4;
        salama = new Salama(2,vahinko);
        pelaaja = new Pelaaja(0,0,2,5,5);
        orkki = new Orkki(3,3,2,5);
        taistelu = new Taistelu(pelaaja,10,10);
        taistelu.lisaaHirvio(orkki);
    }
    
    @Test
    public void vahingoittaaAsetetussaRuudussaOlevaaTaistelijaa() {
        int hpAlussa = orkki.getHp();
        salama.setRuutu(orkki.getX(), orkki.getY(), taistelu);
        salama.suorita(pelaaja);
        assert(orkki.getHp() == hpAlussa - vahinko);
    }
}
