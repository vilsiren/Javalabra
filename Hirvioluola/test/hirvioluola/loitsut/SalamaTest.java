
package hirvioluola.loitsut;

import hirvioluola.domain.Hirvio;
import hirvioluola.domain.Pelaaja;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SalamaTest {
    
    private Salama salama;
    private Pelaaja pelaaja;
    private Hirvio orkki;
    private Taistelu taistelu;
    private int vahinko;
    
    
    @Before
    public void setUp() {
        vahinko = 4;
        salama = new Salama(2,vahinko);
        pelaaja = new Pelaaja(2,5,5);
        orkki = new Hirvio(2,5,0);
        taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(pelaaja,0,0);
        taistelu.lisaaHirvio(orkki,3,3);
    }
    
    @Test
    public void vahingoittaaAsetetussaRuudussaOlevaaTaistelijaa() {
        int hpAlussa = orkki.getHp();
        salama.setRuutu(orkki.getX(), orkki.getY(), taistelu);
        salama.suorita(pelaaja);
        assert(orkki.getHp() == hpAlussa - vahinko);
    }
}
