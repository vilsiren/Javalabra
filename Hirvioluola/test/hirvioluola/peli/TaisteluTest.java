package hirvioluola.peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import hirvioluola.domain.Vihollinen;
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
    Vihollinen orkki1;
    Vihollinen orkki2;
    
    @Before
    public void setUp() {
        orkki1 = new Vihollinen(1,2,0);
        orkki2 = new Vihollinen(1,2,0);      
        pelaaja = new Pelaaja(1,5,5);
        taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(pelaaja,0,0);
        taistelu.lisaaOlio(orkki1,9,9);
        taistelu.lisaaOlio(orkki2,8,9);          
    }
    
    @Test
    public void hirvionLisaysToimii(){        
        assert(taistelu.getViholliset().contains(orkki1));
        assert(taistelu.getViholliset().contains(orkki2));
        assert(orkki1.getTaistelu() == taistelu);
        assert(orkki2.getTaistelu() == taistelu);
    }
    
    @Test
    public void hirviotaEiLisataJosListassaOnHirvioSamoillaKoordinaateilla(){
        Vihollinen orkki3 = new Vihollinen(1,2,0);
        taistelu.lisaaOlio(orkki3, orkki1.getX(), orkki1.getY());
        assert(!taistelu.getViholliset().contains(orkki3));
        assert(orkki3.getTaistelu() == null);
    }
    
    @Test
    public void taistelukentanSisallaTrueJosSisalla(){
        assert(taistelu.taistelukentanSisalla(taistelu.getLeveys() - 1, taistelu.getKorkeus() -1));
        assert(taistelu.taistelukentanSisalla(0, 0));
    }
    
    @Test
    public void taistelukentanSisallaFalseJosXonLiianSuuri(){
        assert(!taistelu.taistelukentanSisalla(taistelu.getLeveys(), 0));
    }
    
    @Test
    public void taistelukentanSisallaFalseJosYonLiianSuuri(){
        assert(!taistelu.taistelukentanSisalla(0, taistelu.getKorkeus()));
    }
    
    @Test
    public void taistelukentanSisallaFalseJosXonNegatiivinen(){
        assert(!taistelu.taistelukentanSisalla(-1, 0));
    }
    
    @Test
    public void taistelukentanSisallaFalseJosYonNegatiivinen(){
        assert(!taistelu.taistelukentanSisalla(0, -1));
    }
    
    @Test
    public void olioRuudussaPalauttaaOikeanOlion(){
        assert(taistelu.olioRuudussa( orkki1.getX(), orkki1.getY() ) == orkki1);
        assert(taistelu.olioRuudussa( orkki2.getX(), orkki2.getY() ) == orkki2);
    }
    
    @Test
    public void hirvioRuudussaPalauttaaNullJosRuudussaEiHirviota(){
        assert(taistelu.olioRuudussa(5,5) == null);
    }
    
    @Test
    public void hirviolistaPaivittyy(){
        assert(taistelu.getViholliset().contains(orkki1));
        orkki1.vahingoitu(orkki1.getHp());
        taistelu.paivitaListat();
        assert(!taistelu.getViholliset().contains(orkki1));
    }
}
