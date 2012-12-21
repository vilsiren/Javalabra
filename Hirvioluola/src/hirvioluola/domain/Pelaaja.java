package hirvioluola.domain;


import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Pelaaja extends Loitsija {
    
    private int kokemuspisteet;
    
    public Pelaaja(int x, int y, int voima, int hpMax, int mpMax) {
        super(x, y, voima, hpMax, mpMax);
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

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x*20, y*20, 20, 20);        
    }

    
}
