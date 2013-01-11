
package hirvioluola.loitsut;

import hirvioluola.peli.Taistelu;

/**
 * Ruutuloitsu on loitsu, jolle on valittava ruutu (x- ja y-koordinaatit).
 * @author Ville
 */

public abstract class Ruutuloitsu extends Loitsu {
    
    protected int x, y;
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public abstract boolean setRuutu(int x, int y, Taistelu taistelu);
}
