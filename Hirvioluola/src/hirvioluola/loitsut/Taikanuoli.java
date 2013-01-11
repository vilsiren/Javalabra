
package hirvioluola.loitsut;

import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

/**
 * Vahingoittaa lähintä oliota valitussa suunnassa (jos ei lapaiseva) tai kaikkia olioita valitussa
 * suunnassa (jos lapaiseva).
 * @author Ville
 */

public class Taikanuoli extends Suuntaloitsu{
    
    private int vahinko;
    private int vikaX, vikaY;
    private boolean lapaiseva;

    public Taikanuoli(int vahinko, boolean lapaiseva) {
        this.vahinko = vahinko;
        this.lapaiseva = lapaiseva;
    }
    
    @Override
    protected void teeLoitsu(Taistelija loitsija){
        Taistelu taistelu = loitsija.getTaistelu();
        int y = loitsija.getY();
        int x = loitsija.getX();
        while(taistelu.taistelukentanSisalla(x, y)){
            x += super.dx;
            y += super.dy;            
            Ruutuolio olio = taistelu.olioRuudussa(x, y);
            if(olio != null){
                olio.vahingoitu(vahinko);
                if(!lapaiseva){
                    vikaX = olio.getX();
                    vikaY = olio.getY();
                    return;
                }
            }
        }
        vikaX = x;
        vikaY = y;
    }
    
    public int getVikaX(){
        return vikaX;
    }
    
    public int getVikaY(){
        return vikaY;
    }
    
    @Override
    public String toString(){
        String s = "";
        if(lapaiseva){
            s += "Läpäisevä ";
        }
        s += "Taikanuoli, vahingoittaa: " + vahinko + " hp, kuluttaa: " + kuluttaaMPta() + " mp";
        return s;
    }    

    @Override
    public int kuluttaaMPta() {
        if(!lapaiseva){
            return vahinko;
        }
        else{
            return vahinko*vahinko;
        }
    }

    @Override
    public int vaatiiKokemuspisteita() {
        if(lapaiseva){
           return 2*vahinko; 
        }
        else{
            return vahinko / 2;
        }
    }

}
