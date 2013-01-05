package hirvioluola.loitsut;


public abstract class Suuntaloitsu extends Loitsu {
    
    protected int dx,dy;
    
    public void setSuunta(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }
    
}
