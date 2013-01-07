package hirvioluola.loitsut;

/**
 * Suuntaloitsu on loitsu jolle on valittava suunta.
 * @author Ville
 */
public abstract class Suuntaloitsu extends Loitsu {
    
    protected int dx,dy;
    
    public void setSuunta(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }
    
}
