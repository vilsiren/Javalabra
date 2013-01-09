package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;
import hirvioluola.peli.Hirvioluolakayttis;

public abstract class Loitsu { 
    
    /**
     * vähennetään loitsijan mp:stä kun loisu suoritetaan.
     */
    public abstract int kuluttaaMPta();
    
    /**
     * vähennetään pelaajan kokemuspisteistä kun pelaaja oppii loitsun.
     */
    public abstract int vaatiiKokemuspisteita();
    
    protected abstract void teeLoitsu(Taistelija loitsija);
    
    /**
     * Jos loitsijan mp riittävät, metodi vähentää loitsijan mp:tä, tekee itse loitsun ja
     * käskee käyttöliittymää piirtämään loitsun. Jos mp:t ei riitä mitään ei tapahdu.
     * @param loitsija 
     */
    public void suorita(Taistelija loitsija){
        if(loitsija.getMp() >= kuluttaaMPta()){
            loitsija.vahennaMPta( kuluttaaMPta() );
            teeLoitsu(loitsija);
            
            Taistelu taistelu = loitsija.getTaistelu();
            if(taistelu == null) return;
            Hirvioluolakayttis kayttis = taistelu.getKayttis();
            if(kayttis == null) return;
            kayttis.piirraLoitsu(this, loitsija.getX(), loitsija.getY());
        }        
    }
    
}
