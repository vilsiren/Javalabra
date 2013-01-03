package hirvioluola.loitsut;

import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class KutsuLiittolainen extends Loitsu implements ToimintoJolleValitaanRuutu{
    
    private int x,y, kuluttaaMPta, voima, hpMax;
    
    public KutsuLiittolainen(int kuluttaaMPta, int voima, int hpMax){
        this.kuluttaaMPta = kuluttaaMPta;
        this.voima = voima;
        this.hpMax = hpMax;
    }

    @Override
    public int kuluttaaMPta() {
        return this.kuluttaaMPta;
    }

    @Override
    protected void teeLoitsu(Taistelija loitsija) {
        Liittolainen kutsuttava = new Liittolainen(voima,hpMax,0);
        loitsija.getTaistelu().lisaaOlio(kutsuttava, x, y);
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
        return "Kutsu Liittolainen, voima: " + voima + ", hp: "
                + hpMax + ", kuluttaa: " + kuluttaaMPta() + " mp";
    }
    
}
