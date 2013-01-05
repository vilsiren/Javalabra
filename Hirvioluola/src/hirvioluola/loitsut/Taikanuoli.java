
package hirvioluola.loitsut;

import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Taikanuoli extends Suuntaloitsu{
    
    private int vahinko, kuluttaaMPta;
    private boolean lapaiseva;

    public Taikanuoli(int kuluttaaMPta, int vahinko, boolean lapaiseva) {
        this.kuluttaaMPta = kuluttaaMPta;
        this.vahinko = vahinko;
        this.lapaiseva = lapaiseva;
    }
    
    @Override
    public void teeLoitsu(Taistelija loitsija){
        Taistelu taistelu = loitsija.getTaistelu();
        int y = loitsija.getY() + super.dy;
        int x = loitsija.getX() + super.dx;
        while(taistelu.taistelukentanSisalla(x, y)){            
            Ruutuolio olio = taistelu.olioRuudussa(x, y);
            if(olio != null){
                olio.vahingoitu(vahinko);
                if(!lapaiseva) return;
            }
            x += super.dx;
            y += super.dy;
        }
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
        return kuluttaaMPta;
    }

}
