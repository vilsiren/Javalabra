package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;

public class Orkki extends Taistelija{
    
    private Pelaaja pelaaja;

    public Orkki(int x, int y, int voima, int hpMax, Taistelu taistelu) {
        super(x, y, voima, hpMax);
        super.taistelu = taistelu;
        pelaaja = taistelu.getPelaaja();
    }
    
    private int suuntaX(){
        if(pelaaja.x == this.x){
            return 0;
        }
        if(pelaaja.x  < this.x){
            return -1;
        }
        else{
            return 1;
        }
    }

    private int suuntaY(){
        if(pelaaja.y == this.y){
            return 0;
        }
        if(pelaaja.y < this.y){
            return -1;
        }
        else{
            return 1;
        }
    }    
    
    @Override
    public void toimi() {
        if(hyokkaysalueella(pelaaja)) { 
            hyokkaa(pelaaja);
            return;
        }
        
        if(suuntaX() == 0 || suuntaY() == 0){
            liiku( suuntaX(), suuntaY() );
            return;
        }
        
        boolean liikkuu = liiku( 0, suuntaY() );
        
        if(!liikkuu){
            liiku(suuntaX(), 0);
        }
        
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x*20, y*20, 20, 20);
    }
    
}
