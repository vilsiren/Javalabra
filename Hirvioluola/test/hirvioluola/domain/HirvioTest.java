
package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HirvioTest {
    
    Taistelu taistelu;
    Pelaaja pelaaja;
    Hirvio orkki1;
    Hirvio orkki2;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(0,0,1,5,5);
        taistelu = new Taistelu(pelaaja,10,10);
        orkki1 = new Hirvio(4,3,1,3,0);
        orkki2 = new Hirvio(3,2,1,3,0); 
        taistelu.lisaaHirvio(orkki1);
        taistelu.lisaaHirvio(orkki2);
    }
    
    @Test
    public void hyokkaysToimii() {
        orkki1.hyokkaa(pelaaja);
        assert(pelaaja.getHp() == pelaaja.getHpMax() - orkki1.getVoima());
    }
    
    @Test
    public void hyokkaysAlueellaTrueSilloinKunPitaakin(){
        assert(orkki1.hyokkaysalueella(orkki2));
    }
    
    @Test
    public void hyokkaysAluellaFalseSilloinKunPitääkin(){
        assert(!orkki1.hyokkaysalueella(pelaaja));
    }
    
    @Test
    public void orkki2LiikkuuKohtiPelaajaaMolemmillaAkseleillaKunKutsutaanMetodiaToimi(){
        int xAluksi = orkki2.getX();
        int yAluksi = orkki2.getY();
        orkki2.toimi();
        assert(orkki2.getX() == xAluksi - 1 && orkki2.getY() == yAluksi - 1);
    }
    
    @Test
    public void orkki1LiikkuuKohtiPelaajaaYakselillakunKutsutaanMetodiaToimi(){
        int xAluksi = orkki1.getX();
        int yAluksi = orkki1.getY();
        orkki1.toimi();
        assert(orkki1.getX() == xAluksi && orkki1.getY() == yAluksi - 1);
    }
    
    @Test 
    public void orkkiLiikkuuKohtiPelaajaaXakselillaKunYkoordinaattiSamaKuinPelaajalla(){
        Hirvio orkki3 = new Hirvio(2,0,1,3,0);
        taistelu.lisaaHirvio(orkki3);
        orkki3.toimi();
        assert(orkki3.getX() == 1 && orkki3.getY() == 0);
    }
    
    @Test
    public void orkkiHyokkaaKunPelaajaHyokkaysalueella(){
        Hirvio orkki4 = new Hirvio(1,0,1,3,0);
        taistelu.lisaaHirvio(orkki4);
        assert(orkki4.hyokkaysalueella(pelaaja));
        orkki4.toimi();
        assert(pelaaja.getHp() == pelaaja.getHpMax() - orkki4.getVoima());        
    }
}