package hirvioluola.domain;

import hirvioluola.loitsut.Loitsu;
import java.util.ArrayList;
import java.util.List;

public abstract class Loitsija extends Taistelija {
    
    protected int mp;
    protected int mpMax;
    protected List<Loitsu> loitsut;

    public Loitsija(int x, int y, int voima, int hpMax, int mpMax) {
        super(x, y, voima, hpMax);
        this.mpMax = mpMax;
        this.mp = mpMax;
        loitsut = new ArrayList<>();
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        if(mp < 0 || mp > mpMax){
            return;
        }
        this.mp = mp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public void setMpMax(int mpMax) {
        if(mpMax < 0){
            return;
        }
        this.mpMax = mpMax;
    }

    public List<Loitsu> getLoitsut() {
        return loitsut;
    }
    
    public void lisaaLoitsu(Loitsu loitsu) {
        loitsut.add(loitsu);
    }
    
    public boolean teeLoitsu(Loitsu loitsu){
        if(mp < loitsu.kuluttaaMPta()){
            return false;
        }        
        else{
            mp -= loitsu.kuluttaaMPta();
            return true;
        }
    }    
}
