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
        Orkki orkki1 = new Orkki(9,9,1,2);
        Orkki orkki2 = new Orkki(9,9,1,2);
        Orkki orkki3 = new Orkki(8,9,1,2);
        taistelu.lisaaHirvio(orkki1);
        taistelu.lisaaHirvio(orkki2);
        taistelu.lisaaHirvio(orkki3);
        assert(taistelu.getHirviot().size() == 2);
        assert(orkki1.getTaistelu() == taistelu);
        assert(orkki2.getTaistelu() == null);
        assert(orkki3.getTaistelu() == taistelu);
    }
    
    @Test
    public void taistelukentanSisallaTrueJosSisalla(){
        assert(taistelu.taistelukentanSisalla(0, 9));
    }
    
    @Test
    public void taistelukentanSisallaFalseJosUlkona(){
        assert(!taistelu.taistelukentanSisalla(15, 0));
    }
}
