package hirvioluola.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Pelaaja extends Taistelija {
    
    private int kokemuspisteet;
    
    public Pelaaja(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
        this.mpMax = mpMax;
        this.mp = mpMax;
        this.loitsut = new ArrayList<>();
        this.kokemuspisteet = 0;
    }    

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x*20, y*20, 20, 20);        
    }

    @Override
    public char merkki() {
        return '@';
    }

    
}
