
package hirvioluola.peli;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Suuntaloitsu;

public interface Taistelukayttis {
            
    void paivita();
    String odotaPelaajanKomentoa();
    void valitseRuutu(Ruutuloitsu loitsu);
    void valitseSuunta(Suuntaloitsu loitsu);
    void piirraHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY);
    void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY);
}
