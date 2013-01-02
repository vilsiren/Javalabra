package hirvioluola.loitsut;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Teleportaatio extends Loitsu implements ToimintoJolleValitaanRuutu{
    
    private int x, y;
    private int kuluttaaMPta;

    public Teleportaatio(int kuluttaaMPta) {
        this.kuluttaaMPta = kuluttaaMPta;
    }
    
    @Override
    public void teeLoitsu(Taistelija loitsija){
        loitsija.setX(x);
        loitsija.setY(y);                        
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        if(!taistelu.taistelukentanSisalla(x, y)){
            return false;
        }
        if(taistelu.olioRuudussa(x, y) != null){
            return false;
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
