package hirvioluola.domain;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;
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

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public void setMpMax(int mpMax) {
        this.mpMax = mpMax;
    }
    
    public List<Loitsu> getLoitsut() {
        return loitsut;
    }
    
    public void lisaaLoitsu(Loitsu loitsu) {
        loitsut.add(loitsu);
    }

    @Override
    public void toimi() {
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x*20, y*20, 20, 20);        
    }

    
}
