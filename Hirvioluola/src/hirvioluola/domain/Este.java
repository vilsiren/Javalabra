
package hirvioluola.domain;

/**
 * Este ei tee mitään, mutta voi vahingoittua ja tuhoutua. 
 * @author Ville
 */

public class Este extends Ruutuolio {
    
    private char merkki;
    
    public Este(int hpMax, char merkki) {
        super(hpMax);
        this.merkki = merkki;        
    }
    
    @Override
    public char merkki() {
        return merkki;
    }
        
}
