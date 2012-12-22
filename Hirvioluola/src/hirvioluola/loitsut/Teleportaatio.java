package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;

public class Teleportaatio extends LoitsuJolleValitaanRuutu{
    
    private int x, y;

    public Teleportaatio(int kuluttaaMPta, Loitsija loitsija) {
        super(kuluttaaMPta, loitsija);
    }
    
    @Override
    public void suorita(){
        super.suorita();
        super.loitsija.setX(x);
        super.loitsija.setY(y);                        
    }

    @Override
    public boolean setRuutu(int x, int y) {
        if(!super.loitsija.getTaistelu().taistelukentanSisalla(x, y)){
            return false;
        }
        Pelaaja pelaaja = super.loitsija.getTaistelu().getPelaaja();
        if(!(super.loitsija instanceof Pelaaja) && super.loitsija.getX() == pelaaja.getX()
                && super.loitsija.getY() == pelaaja.getY()){
            return false;
        }
        for(Taistelija hirvio : super.loitsija.getTaistelu().getHirviot()){
            if(x == hirvio.getX() && y == hirvio.getY()){
                return false;
            }
        }
        this.x = x;
        this.y = y;
        return true;
    }
    
}
