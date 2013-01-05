package hirvioluola.loitsut;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Teleportaatio extends Ruutuloitsu{
    
    private int kuluttaaMPta;

    public Teleportaatio(int kuluttaaMPta) {
        this.kuluttaaMPta = kuluttaaMPta;
    }
    
    @Override
    public void teeLoitsu(Taistelija loitsija){
        loitsija.setX(super.x);
        loitsija.setY(super.y);                        
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        if(!taistelu.taistelukentanSisalla(x, y)){
            return false;
        }
        if(taistelu.olioRuudussa(x, y) != null){
            return false;
        }
        super.x = x;
        super.y = y;
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
