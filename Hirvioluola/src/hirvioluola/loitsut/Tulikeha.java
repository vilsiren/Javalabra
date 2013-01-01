
package hirvioluola.loitsut;

import hirvioluola.domain.Hirvio;
import hirvioluola.domain.Taistelija;


public class Tulikeha extends Loitsu{
    
    private int sade;
    private int vahinko;
    
    public Tulikeha(int vahinko, int sade){
        this.vahinko = vahinko;
        this.sade = sade;
    }

    @Override
    public int kuluttaaMPta() {
        return vahinko*sade*sade;
    }

    @Override
    public void teeLoitsu(Taistelija loitsija) {
        for(Hirvio h : loitsija.getTaistelu().getHirviot()){
            if(Math.abs( h.getX() - loitsija.getX() ) <= sade && Math.abs( h.getY() - loitsija.getY()) <= sade){
                h.vahingoitu(vahinko);
            }
        }
    }
    
    @Override
    public String toString(){
        return "Tulikehä, vahingoittaa: " + vahinko + " hp, säteellä: " + 
                sade + ", kuluttaa: " + kuluttaaMPta() + " mp"; 
    }        
    
}
