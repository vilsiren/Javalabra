package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;

public class Vihollinen extends Tekoalytaistelija{
    

    public Vihollinen(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
    }
    
    @Override
    public void setTaistelu(Taistelu taistelu){
        super.setTaistelu(taistelu);
        if(taistelu != null){
            super.kohde = taistelu.getPelaaja();
        }
    }
    

    @Override
    public void toimi() {
        if(hyokkaysalueella(kohde)) { 
            hyokkaa(kohde);
            return;
        }
        for(Liittolainen l : taistelu.getLiittolaiset()){
            if( hyokkaysalueella(l) ){
                hyokkaa(l);
                return;
            }
        }
        
        if(!lahestyKohdetta()){
            for(Este e : taistelu.getEsteet()){
                if(hyokkaysalueella(e)){
                    hyokkaa(e);
                    return;
                }
            }
        }
        
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x*20, y*20, 20, 20);
    }

    @Override
    public char merkki() {
        return 'v';
    }
    
}
