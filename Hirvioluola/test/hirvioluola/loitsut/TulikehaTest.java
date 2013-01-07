package hirvioluola.loitsut;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.domain.Vihollinen;
import hirvioluola.peli.Taistelu;
import org.junit.Before;
import org.junit.Test;

public class TulikehaTest {
    
    Taistelu taistelu; 
    Pelaaja pelaaja;
    Tulikeha tulikehaSade1;
    Tulikeha tulikehaSade2;
    int olioidenHp = 6;
    int vahinko = 2;
    
    private boolean kaikkiOliotVahingoittuneetSateella(int sade){
        for(Ruutuolio olio : taistelu.ruutuOliot()){
            if(Math.abs(pelaaja.getX() - olio.getX()) <= sade && Math.abs(pelaaja.getY() - olio.getY()) <= sade){
                if(olio != pelaaja && olio.getHp() != olioidenHp - vahinko){
                    return false;
                }
            }
        }        
        return true;
    }
    
    @Before
    public void setUp(){
        taistelu = new Taistelu(20,20);
        pelaaja = new Pelaaja(2,5,100);
        taistelu.setPelaaja(pelaaja,10,10);
        tulikehaSade1 = new Tulikeha(vahinko,1);
        tulikehaSade2 = new Tulikeha(vahinko,2);
        for(int y = pelaaja.getY() - 2; y <= pelaaja.getY() + 2; y++){
            for(int x = pelaaja.getX() -2; x <= pelaaja.getX() + 2; x++){
                taistelu.lisaaOlio(new Vihollinen(2,olioidenHp,0), x, y);
            }
        }
    }
    
    @Test
    public void tulikehaSade1vahingoittaaSateella1(){
        tulikehaSade1.suorita(pelaaja);
        assert(kaikkiOliotVahingoittuneetSateella(1));
    }
    
    @Test
    public void tulikehaSade1EiVahingoitaSateenUlkopuolalla(){
        tulikehaSade1.suorita(pelaaja);
        for(Ruutuolio olio : taistelu.ruutuOliot()){
            if(Math.abs(pelaaja.getX() - olio.getX()) > 1 || Math.abs(pelaaja.getY() - olio.getY()) > 1){
                assert(olio.getHp() == olio.getHpMax());
            }
        }          
    }
    
    @Test
    public void tulikehaSade2vahingoittaaSateella2(){
        tulikehaSade2.suorita(pelaaja);
        assert(kaikkiOliotVahingoittuneetSateella(2));
    }
    
}
