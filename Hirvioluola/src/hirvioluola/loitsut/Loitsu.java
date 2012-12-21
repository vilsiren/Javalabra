package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;

public abstract class Loitsu {
    
    protected Loitsija loitsija;
    protected int kuluttaaMPta;

    public Loitsu(int kuluttaaMPta, Loitsija loitsija) {
        this.loitsija = loitsija;
        this.kuluttaaMPta = kuluttaaMPta;
    }    
    
    public int kuluttaaMPta(){
        return kuluttaaMPta;
    }
    
    public void suorita(){
        if(kuluttaaMPta > loitsija.getMp()){
            return;
        }
        loitsija.setMp( loitsija.getMp() - kuluttaaMPta );
    }
}
