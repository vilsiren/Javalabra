package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;

public abstract class Loitsu {
    
    protected int kuluttaaMPta;

    public Loitsu(int kuluttaaMPta) {
        this.kuluttaaMPta = kuluttaaMPta;
    }    
    
    public int kuluttaaMPta(){
        return kuluttaaMPta;
    }
    
    public void suorita(Loitsija loitsija){
        if(kuluttaaMPta > loitsija.getMp()){
            return;
        }
        loitsija.setMp( loitsija.getMp() - kuluttaaMPta );
    }
}
