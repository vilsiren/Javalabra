package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

public abstract class Loitsu {  
    
    public abstract int kuluttaaMPta();        
    
    protected abstract void teeLoitsu(Taistelija loitsija);
    
    public void suorita(Taistelija loitsija){
        if(loitsija.getMp() >= kuluttaaMPta()){
            loitsija.vahennaMPta( kuluttaaMPta() );
            teeLoitsu(loitsija);
        }        
    }
    
}
