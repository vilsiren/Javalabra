package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;

public class Teleportaatio extends LoitsuJolleValitaanRuutu{
    
    private int x, y;

    public Teleportaatio(int kuluttaaMPta) {
        super(kuluttaaMPta);
    }
    
    @Override
    public void suorita(Loitsija loitsija){
        super.suorita(loitsija);
        loitsija.setX(x);
        loitsija.setY(y);                        
    }

    @Override
    public boolean setRuutu(int x, int y, Loitsija loitsija) {
        if(!loitsija.getTaistelu().taistelukentanSisalla(x, y)){
            return false;
        }
        Pelaaja pelaaja = loitsija.getTaistelu().getPelaaja();
        if(!(loitsija instanceof Pelaaja) && loitsija.getX() == pelaaja.getX()
                && loitsija.getY() == pelaaja.getY()){
            return false;
        }
        for(Taistelija hirvio : loitsija.getTaistelu().getHirviot()){
            if(x == hirvio.getX() && y == hirvio.getY()){
                return false;
            }
        }
        this.x = x;
        this.y = y;
        return true;
    }
    
}
