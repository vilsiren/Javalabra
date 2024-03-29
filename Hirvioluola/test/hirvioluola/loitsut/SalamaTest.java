
package hirvioluola.loitsut;

import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SalamaTest {
    
    private Salama salama;
    private Pelaaja pelaaja;
    private Vihollinen orkki;
    private Taistelu taistelu;
    private int vahinko;
    
    
    @Before
    public void setUp() {
        vahinko = 4;
        salama = new Salama(vahinko);
        pelaaja = new Pelaaja(2,5,10);
        orkki = new Vihollinen(2,5,0);
        taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(pelaaja,0,0);
        taistelu.lisaaOlio(orkki,3,3);
    }
    
    @Test
    public void vahingoittaaAsetetussaRuudussaOlevaaTaistelijaa() {
        int hpAlussa = orkki.getHp();
        salama.setRuutu(orkki.getX(), orkki.getY(), taistelu);
        salama.suorita(pelaaja);
        assert(orkki.getHp() == hpAlussa - vahinko);
    }
}
