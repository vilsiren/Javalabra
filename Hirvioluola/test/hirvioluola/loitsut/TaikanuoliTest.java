
package hirvioluola.loitsut;

import hirvioluola.domain.Hirvio;
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
    private Hirvio orkki;
    private Hirvio orkki2;
    private Taistelu taistelu;
    private int vahinko = 2;
    
    @Before
    public void setUp() {
        lapaiseva = new Taikanuoli(2,vahinko,true);
        eiLapaiseva = new Taikanuoli(2,vahinko,false);
        pelaaja = new Pelaaja(2,5,5);
        orkki = new Hirvio(2,5,0);
        orkki2 = new Hirvio(2,5,0);
        taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(pelaaja, 0,0);
        taistelu.lisaaHirvio(orkki,0,3);
        taistelu.lisaaHirvio(orkki2, 0, 5);
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
