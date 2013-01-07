
package hirvioluola.peli;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Suuntaloitsu;

public interface Hirvioluolakayttis {
        
    void paivita();
    String odotaPelaajanKomentoa();
    String valitseKokemuspisteidenKaytto();
    int valitseOpittavaLoitsu();
    void setPeli(Peli peli);
    void setTaistelu(Taistelu taistelu);
    void valitseRuutu(Ruutuloitsu loitsu);
    void valitseSuunta(Suuntaloitsu loitsu);
    void piirraHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY);
    void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY);
}
