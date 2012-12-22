package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;

public abstract class LoitsuJolleValitaanSuunta extends Loitsu {
    
    protected int dx, dy;

    public LoitsuJolleValitaanSuunta(int kuluttaaMPta, Loitsija loitsija) {
        super(kuluttaaMPta, loitsija);
    }
    
    private int muuta(int d){
        if(d < 0){
            return -1;
        }
        if(d > 0){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    public void setSuunta(int dx, int dy){
        this.dx = muuta(dx);
        this.dy = muuta(dy);
    }
    
}
