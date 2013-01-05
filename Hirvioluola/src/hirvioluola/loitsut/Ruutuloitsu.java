
package hirvioluola.loitsut;

import hirvioluola.peli.Taistelu;

public abstract class Ruutuloitsu extends Loitsu {
    
    protected int x, y;
    
    public abstract boolean setRuutu(int x, int y, Taistelu taistelu);
}
