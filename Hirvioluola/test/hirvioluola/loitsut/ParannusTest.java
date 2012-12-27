
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
    private int kuluttaaMPta;
    private int parantaaHPta;
    
    @Before
    public void setUp() {
        kuluttaaMPta = 2;
        parantaaHPta = 3;
        parannus = new Parannus(kuluttaaMPta, parantaaHPta);
        loitsija = new Pelaaja(0,0,2,5,5);
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
        loitsija.teeLoitsu(parannus);
        assert(loitsija.getMp() == mpAluksi - kuluttaaMPta);
    }
    
    @Test
    public void eiParannaYliMaxHPn() {
        Parannus parannus2 = new Parannus(kuluttaaMPta, loitsija.getHpMax() +1);
        parannus2.suorita(loitsija);
        assert(loitsija.getHp() == loitsija.getHpMax());        
    }
  
}
