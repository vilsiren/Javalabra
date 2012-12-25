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
    Orkki orkki1;
    Orkki orkki2;
    
    @Before
    public void setUp() {
        orkki1 = new Orkki(9,9,1,2);
        orkki2 = new Orkki(8,9,1,2);      
        pelaaja = new Pelaaja(0,0,1,5,5);
        taistelu = new Taistelu(pelaaja,10,10);
        taistelu.lisaaHirvio(orkki1);
        taistelu.lisaaHirvio(orkki2);          
    }
    
    @Test
    public void hirvionLisaysToimii(){        
        assert(taistelu.getHirviot().size() == 2);
        assert(orkki1.getTaistelu() == taistelu);
        assert(orkki2.getTaistelu() == taistelu);
    }
    
    @Test
    public void hirviotaEiLisataJosListassaOnHirvioSamoillaKoordinaateilla(){
        Orkki orkki3 = new Orkki(orkki1.getX(),orkki2.getY(),1,2);
        taistelu.lisaaHirvio(orkki3);
        assert(!taistelu.getHirviot().contains(orkki3));
        assert(orkki3.getTaistelu() == null);
    }
    
    @Test
    public void taistelukentanSisallaTrueJosSisalla(){
        assert(taistelu.taistelukentanSisalla(0, 9));
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
    public void hirviolistaPaivittyy(){
        orkki1.vahingoitu(orkki1.getHp());
        taistelu.paivitaHirviolista();
        assert(!taistelu.getHirviot().contains(orkki1));
    }
}
