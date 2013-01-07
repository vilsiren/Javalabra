package hirvioluola.loitsut;

import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.peli.Taistelu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KutsuLiittolainenTest {
    
    KutsuLiittolainen kl;
    Pelaaja p;
    Taistelu taistelu;
    int liittolaisenVoima = 3;
    int liittolaisenHp = 4;
    int liittolainenX = 5;
    int liittolainenY = 7;
    
    
    @Before
    public void setUp() {
        kl = new KutsuLiittolainen(liittolaisenVoima,liittolaisenHp);
        p = new Pelaaja(2,4,100);
        taistelu = new Taistelu(10,10);
        taistelu.setPelaaja(p,0,0);
        kl.setRuutu(liittolainenX, liittolainenY, taistelu);
        kl.suorita(p);        
    }
    
    @Test
    public void liittolainenIlmestyyOikeaanRuutuun() {
        kl.setRuutu(liittolainenX, liittolainenY, taistelu);
        kl.suorita(p);
        assert(taistelu.olioRuudussa(liittolainenX, liittolainenY) instanceof Liittolainen);
    }
    
    @Test
    public void liittolaisenVoimaJaHpOikein(){
        kl.setRuutu(liittolainenX, liittolainenY, taistelu);
        kl.suorita(p);
        Ruutuolio olio = taistelu.olioRuudussa(liittolainenX, liittolainenY);
        Liittolainen l = (Liittolainen) olio;
        assert(l.getVoima() == liittolaisenVoima);
        assert(l.getHpMax() == liittolaisenHp);
    }
    
    @Test
    public void setRuutuFalseJosRuutuEiTyhja(){
        assert(!kl.setRuutu(p.getX(), p.getY(), taistelu));        
    }
    
}
