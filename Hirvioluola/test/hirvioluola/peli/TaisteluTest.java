package hirvioluola.peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import hirvioluola.domain.Hirvio;
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
    Hirvio orkki1;
    Hirvio orkki2;
    
    @Before
    public void setUp() {
        orkki1 = new Hirvio(9,9,1,2,0);
        orkki2 = new Hirvio(8,9,1,2,0);      
        pelaaja = new Pelaaja(0,0,1,5,5);
        taistelu = new Taistelu(pelaaja,10,10);
        taistelu.lisaaHirvio(orkki1);
        taistelu.lisaaHirvio(orkki2);          
    }
    
    @Test
    public void hirvionLisaysToimii(){        
        assert(taistelu.getHirviot().contains(orkki1));
        assert(taistelu.getHirviot().contains(orkki2));
        assert(orkki1.getTaistelu() == taistelu);
        assert(orkki2.getTaistelu() == taistelu);
    }
    
    @Test
    public void hirviotaEiLisataJosListassaOnHirvioSamoillaKoordinaateilla(){
        Hirvio orkki3 = new Hirvio(orkki1.getX(),orkki2.getY(),1,2,0);
        taistelu.lisaaHirvio(orkki3);
        assert(!taistelu.getHirviot().contains(orkki3));
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
    public void hirvioRuudussaPalauttaaOikeanHirvion(){
        assert(taistelu.hirvioRuudussa( orkki1.getX(), orkki1.getY() ) == orkki1);
        assert(taistelu.hirvioRuudussa( orkki2.getX(), orkki2.getY() ) == orkki2);
    }
    
    @Test
    public void hirvioRuudussaPalauttaaNullJosRuudussaEiHirviota(){
        assert(taistelu.hirvioRuudussa(5,5) == null);
    }
    
    @Test
    public void hirviolistaPaivittyy(){
        orkki1.vahingoitu(orkki1.getHp());
        taistelu.paivitaHirviolista();
        assert(!taistelu.getHirviot().contains(orkki1));
    }
}
