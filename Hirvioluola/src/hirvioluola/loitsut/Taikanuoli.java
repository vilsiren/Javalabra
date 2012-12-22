
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Taikanuoli extends LoitsuJolleValitaanSuunta{
    
    private int vahinko;
    private boolean lapaiseva;

    public Taikanuoli(int kuluttaaMPta, int vahinko, boolean lapaiseva, Loitsija loitsija) {
        super(kuluttaaMPta, loitsija);
        this.vahinko = vahinko;
        this.lapaiseva = lapaiseva;
    }
    
    @Override
    public void suorita(){
        super.suorita();
        Taistelu taistelu = super.loitsija.getTaistelu();
        Pelaaja pelaaja = taistelu.getPelaaja();
        int y = pelaaja.getY() + super.dx;
        int x = pelaaja.getX() + super.dx;
        while(taistelu.taistelukentanSisalla(x, y)){            
            for(Taistelija hirvio : taistelu.getHirviot()){
                if(hirvio.getX() == x && hirvio.getY() == y){
                    hirvio.vahingoitu(vahinko);
                    if(!lapaiseva) return;
                }
            }
            x += super.dx;
            y += super.dy;
        }
    }
}
