
package hirvioluola.loitsut;

import hirvioluola.domain.Hirvio;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Taikanuoli extends Loitsu implements ToimintoJolleValitaanSuunta{
    
    private int vahinko, dx, dy, kuluttaaMPta;
    private boolean lapaiseva;

    public Taikanuoli(int kuluttaaMPta, int vahinko, boolean lapaiseva) {
        this.kuluttaaMPta = kuluttaaMPta;
        this.vahinko = vahinko;
        this.lapaiseva = lapaiseva;
    }
    
    @Override
    public void teeLoitsu(Taistelija loitsija){
        Taistelu taistelu = loitsija.getTaistelu();
        Pelaaja pelaaja = taistelu.getPelaaja();
        int y = pelaaja.getY() + dy;
        int x = pelaaja.getX() + dx;
        while(taistelu.taistelukentanSisalla(x, y)){            
            Hirvio hirvio = taistelu.hirvioRuudussa(x, y);
            if(hirvio != null){
                hirvio.vahingoitu(vahinko);
                if(!lapaiseva) return;
            }
            x += dx;
            y += dy;
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

    @Override
    public void setSuunta(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
