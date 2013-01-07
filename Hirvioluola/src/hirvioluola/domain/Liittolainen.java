package hirvioluola.domain;

import java.awt.Graphics;
import java.util.Random;

/**
 * Liittolainen taistelee pelaajan puolella vihollisia vastaan.
 * @author Ville
 */

public class Liittolainen extends Tekoalytaistelija{

    public Liittolainen(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
    }    

    @Override
    public void toimi() {
        if(super.taistelu.getViholliset().isEmpty()){
            super.kohde = null;
            return;
        }
                
        if(super.kohde == null || super.kohde.hp <= 0){
            int hirvioNro = new Random().nextInt(super.taistelu.getViholliset().size());
            super.kohde = super.taistelu.getViholliset().get(hirvioNro);
        }
        
        if(hyokkaysalueella(super.kohde)){
            hyokkaa(super.kohde);
            return;
        }
        
        for(Vihollinen h : super.taistelu.getViholliset()){
            if( hyokkaysalueella(h) ){
                hyokkaa(h);
                return;
            }
        }

        lahestyKohdetta();                
        
    }

    @Override
    public void piirra(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public char merkki() {
        return 'l';
    }
    
}
