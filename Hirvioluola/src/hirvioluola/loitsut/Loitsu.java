package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;
import hirvioluola.peli.Taistelukayttis;

public abstract class Loitsu {  
    
    public abstract int kuluttaaMPta();        
    
    protected abstract void teeLoitsu(Taistelija loitsija);
    
    public void suorita(Taistelija loitsija){
        if(loitsija.getMp() >= kuluttaaMPta()){
            loitsija.vahennaMPta( kuluttaaMPta() );
            teeLoitsu(loitsija);
            
            Taistelu taistelu = loitsija.getTaistelu();
            if(taistelu == null) return;
            Taistelukayttis kayttis = taistelu.getKayttis();
            if(kayttis == null) return;
            kayttis.piirraLoitsu(this, loitsija.getX(), loitsija.getY());
        }        
    }
    
}
