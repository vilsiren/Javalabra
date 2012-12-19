package hirvioluola.domain;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.peli.Taistelu;
import java.util.ArrayList;
import java.util.List;

public class Pelaaja extends Taistelija {
    
    private int mp;
    private int mpMax;
    private int kokemuspisteet;
    private List<Loitsu> loitsut;
    
    public Pelaaja(int x, int y, int voima, int hpMax, int mpMax) {
        super(x, y, voima, hpMax);
        this.mpMax = mpMax;
        this.mp = mpMax;
        this.loitsut = new ArrayList<>();
        this.kokemuspisteet = 0;
    }
    
    public void setTaistelu(Taistelu taistelu){
        super.taistelu = taistelu;
    }

    @Override
    public void toimi() {
    }

    
}
