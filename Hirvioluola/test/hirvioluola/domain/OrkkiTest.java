
package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrkkiTest {
    
    Taistelu taistelu;
    Pelaaja pelaaja;
    Orkki orkki1;
    Orkki orkki2;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(0,0,1,5,5);
        taistelu = new Taistelu(pelaaja,10,10);
        orkki1 = new Orkki(3,3,1,3,taistelu);
        orkki2 = new Orkki(3,2,1,3,taistelu); 
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
    public void orkki2LiikkuuKohtiPelaajaYakselillaKunKutsutaanMetodiaToimi(){
        orkki2.toimi();
        assert(orkki2.getX() == 3 && orkki2.getY() == 1);
    }
    
    @Test
    public void orkki1LiikkuuKohtiPelaajaaXakselillakunKutsutaanMetodiaToimi(){
        //orkit liikkuvat ensisijaisesti y-akselilla kohti pelaajaa, mutta koska
        //orkki2 on orkki1:n tiellä, orkki2 liikkuu x-akselilla
        orkki1.toimi();
        assert(orkki1.getX() == 2 && orkki1.getY() == 3);
    }
    
    @Test 
    public void orkkiLiikkuuKohtiPelaajaaXakselillaKunYkoordinaattiSamaKuinPelaajalla(){
        Orkki orkki3 = new Orkki(2,0,1,3,taistelu);
        taistelu.lisaaHirvio(orkki3);
        orkki3.toimi();
        assert(orkki3.getX() == 1 && orkki3.getY() == 0);
    }
    
    @Test
    public void orkkiHyokkaaKunPelaajaHyokkaysalueella(){
        Orkki orkki4 = new Orkki(1,0,1,3,taistelu);
        taistelu.lisaaHirvio(orkki4);
        orkki4.toimi();
        assert(pelaaja.getHp() == pelaaja.getHpMax() - orkki4.getVoima());        
    }
}
