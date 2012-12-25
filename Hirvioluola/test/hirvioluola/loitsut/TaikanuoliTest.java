
package hirvioluola.loitsut;

import hirvioluola.domain.Orkki;
import hirvioluola.domain.Pelaaja;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaikanuoliTest {
    
    private Taikanuoli lapaiseva;
    private Taikanuoli eiLapaiseva;
    private Pelaaja pelaaja;
    private Orkki orkki;
    private Orkki orkki2;
    private Taistelu taistelu;
    private int vahinko = 2;
    
    @Before
    public void setUp() {
        lapaiseva = new Taikanuoli(2,vahinko,true);
        eiLapaiseva = new Taikanuoli(2,vahinko,false);
        pelaaja = new Pelaaja(0,0,2,5,5);
        orkki = new Orkki(0,3,2,5);
        orkki2 = new Orkki(0,5,2,5);
        taistelu = new Taistelu(pelaaja,10,10);
        taistelu.lisaaHirvio(orkki);
        taistelu.lisaaHirvio(orkki2);
    }
    
    @Test
    public void lapaisevaVahingoittaaMolempiaOrkkeja(){
        int hpAluksi = orkki.getHp();
        int hpAluksi2 = orkki2.getHp();
        lapaiseva.setSuunta(0, 1);
        lapaiseva.suorita(pelaaja);
        assert(orkki.getHp() == hpAluksi - vahinko);
        assert(orkki2.getHp() == hpAluksi2 - vahinko);
    }
    
    @Test
    public void eiLapaisevaVahingoittaaVainEkaaOrkkia(){
        int hpAluksi = orkki.getHp();
        int hpAluksi2 = orkki2.getHp();
        eiLapaiseva.setSuunta(0, 1);
        eiLapaiseva.suorita(pelaaja);
        assert(orkki.getHp() == hpAluksi - vahinko);
        assert(orkki2.getHp() == hpAluksi2);
    }    
}