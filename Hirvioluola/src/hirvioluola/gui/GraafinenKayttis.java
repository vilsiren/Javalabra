/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hirvioluola.gui;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Suuntaloitsu;
import hirvioluola.peli.Hirvioluolakayttis;
import hirvioluola.peli.Peli;
import hirvioluola.peli.Taistelu;

/**
 *
 * @author Ville
 */
public class GraafinenKayttis implements Hirvioluolakayttis {
    
    private Piirtoalusta piirtoalusta;
    private Taistelu taistelu;

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String odotaPelaajanKomentoa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String valitseKokemuspisteidenKaytto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int valitseOpittavaLoitsu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPeli(Peli peli) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTaistelu(Taistelu taistelu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void valitseRuutu(Ruutuloitsu loitsu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void valitseSuunta(Suuntaloitsu loitsu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void piirraHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
