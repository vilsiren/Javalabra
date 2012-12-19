package hirvioluola.peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import hirvioluola.domain.Orkki;
import hirvioluola.domain.Pelaaja;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaisteluTest {
    
    Taistelu taistelu;
    Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(0,0,1,5,5);
        taistelu = new Taistelu(pelaaja,10,10);
    }
    
    @Test
    public void hirvionLisaysToimii(){        
        taistelu.lisaaHirvio(new Orkki(9,9,1,2,taistelu));
        taistelu.lisaaHirvio(new Orkki(9,9,1,2,taistelu));
        taistelu.lisaaHirvio(new Orkki(8,9,1,2,taistelu));
        assert(taistelu.getHirviot().size() == 2);
    }
}
