package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Teleportaatio implements Loitsu, ToimintoJolleValitaanRuutu{
    
    private int x, y;
    private int kuluttaaMPta;

    public Teleportaatio(int kuluttaaMPta) {
        this.kuluttaaMPta = kuluttaaMPta;
    }
    
    @Override
    public void suorita(Loitsija loitsija){
        loitsija.setX(x);
        loitsija.setY(y);                        
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        if(!taistelu.taistelukentanSisalla(x, y)){
            return false;
        }
        Pelaaja pelaaja = taistelu.getPelaaja();
        if( x == pelaaja.getX() && y == pelaaja.getY()){
            return false;
        }
        for(Taistelija hirvio : taistelu.getHirviot()){
            if(x == hirvio.getX() && y == hirvio.getY()){
                return false;
            }
        }
        this.x = x;
        this.y = y;
        return true;
    }
    @Override
    public String toString(){
        return "Teleportaatio, kuluttaa: " + kuluttaaMPta() + " mp";
    }    

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }
}
