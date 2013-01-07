
package hirvioluola.loitsut;


import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParannusTest {
    
    private Parannus parannus;
    private Taistelija loitsija;
    private int parantaaHPta;
    
    @Before
    public void setUp() {
        parantaaHPta = 3;
        parannus = new Parannus(parantaaHPta);
        loitsija = new Pelaaja(2,5,20);
        loitsija.vahingoitu(4);
    }
    
    @Test
    public void parantaaOikeanMaaranHPta() {
        int hpAluksi = loitsija.getHp();
        parannus.suorita(loitsija);
        assert(loitsija.getHp() == hpAluksi + parantaaHPta);
    }    
    
    @Test
    public void kuluttaaOikeanMaaranMPta() {
        int mpAluksi = loitsija.getMp();
        parannus.suorita(loitsija);
        assert(loitsija.getMp() == mpAluksi - parannus.kuluttaaMPta());
    }
    
    @Test
    public void eiParannaYliMaxHPn() {
        Parannus parannus2 = new Parannus(loitsija.getHpMax() +1);
        parannus2.suorita(loitsija);
        assert(loitsija.getHp() == loitsija.getHpMax());        
    }
  
}
