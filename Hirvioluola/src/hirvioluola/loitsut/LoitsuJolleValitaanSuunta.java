package hirvioluola.loitsut;


public abstract class LoitsuJolleValitaanSuunta extends Loitsu {
    
    protected int dx, dy;

    public LoitsuJolleValitaanSuunta(int kuluttaaMPta) {
        super(kuluttaaMPta);
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
