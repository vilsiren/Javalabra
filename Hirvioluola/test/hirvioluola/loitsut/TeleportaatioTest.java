
package hirvioluola.loitsut;

import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeleportaatioTest {
    
    private Teleportaatio teleport;
    private Pelaaja pelaaja;
    private Vihollinen orkki;
    private Taistelu taistelu;
    
    @Before
    public void setUp() {
        teleport = new Teleportaatio();
        pelaaja = new Pelaaja(2,5,5);
        orkki = new Vihollinen(2,5,0);
        taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(pelaaja,0,0);
        taistelu.lisaaOlio(orkki,3,3);        
    }
    
    private boolean loitsijanKoordinaatitPysyySamanaKunYritaaTeleportataRuutuun(int x, int y){
        int xAlussa = pelaaja.getX();
        int yAlussa = pelaaja.getY();
        teleport.setRuutu(x, y, taistelu);
        teleport.suorita(pelaaja);
        return pelaaja.getX() == xAlussa && pelaaja.getY() == yAlussa;
    }
    
    @Test
    public void teleporttausToimii(){
        teleport.setRuutu(9, 9, taistelu);
        teleport.suorita(pelaaja);
        assert(pelaaja.getX() == 9 && pelaaja.getY() == 9);
    }    
    
    @Test
    public void loitsijanKoordinaatitEiMuutuKunYrittaaLiikkuaToisenTaistelijanRuuutuun(){
        assert(loitsijanKoordinaatitPysyySamanaKunYritaaTeleportataRuutuun(orkki.getX(), orkki.getY()));
    }
    
    @Test
    public void loitsijanKoordinaatitEiMuutuKunXkoordinaattiOnLiianSuuri(){
        assert(loitsijanKoordinaatitPysyySamanaKunYritaaTeleportataRuutuun(taistelu.getLeveys(),0));        
    }
    
    @Test
    public void loitsijanKoordinaatitEiMuutuKunYkoordinaattiOnLiianSuuri(){
        assert(loitsijanKoordinaatitPysyySamanaKunYritaaTeleportataRuutuun(0,taistelu.getKorkeus()));        
    } 
    
    @Test
    public void loitsijanKoordinaatitEiMuutuKunXkoordinaattiOnNegatiivinen(){
        assert(loitsijanKoordinaatitPysyySamanaKunYritaaTeleportataRuutuun(-1,0));        
    }
    
    @Test
    public void loitsijanKoordinaatitEiMuutuKunYkoordinaattiOnNegatiivinen(){
        assert(loitsijanKoordinaatitPysyySamanaKunYritaaTeleportataRuutuun(0,-1));        
    }    
}
