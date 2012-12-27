package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;

public class Hirvio extends Taistelija{
    
    private Pelaaja pelaaja;

    public Hirvio(int x, int y, int voima, int hpMax, int mpMax) {
        super(x, y, voima, hpMax, mpMax);
    }
    
    @Override
    public void setTaistelu(Taistelu taistelu){
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
    
    private void lahestyPelaajaa(){
        if(suuntaY() == 0){
            lahestyPelaajaaXakselilla();
            return;
        }
        
        if(suuntaX() == 0 ){
            lahestyPelaajaaYakselilla();
            return;
        }
        
        boolean liikkuu = liiku( suuntaX(), suuntaY() );
        
        if(!liikkuu){
            liikkuu = lahestyPelaajaaYakselilla();
            if(!liikkuu){
                lahestyPelaajaaXakselilla();
            }
        }                
    }
    
    private boolean lahestyPelaajaaXakselilla(){
        boolean liikkuu = liiku( suuntaX(), 0 );
        if(!liikkuu){
            if(taistelu.taistelukentanSisalla(x + suuntaX() , y + 1) ){
                liikkuu = liiku(suuntaX(), 1);
            }
            if(!liikkuu && taistelu.taistelukentanSisalla(x + suuntaX(), y - 1) ){
                liikkuu = liiku(suuntaX(), -1);
            }
        }
        return liikkuu;
    }
    
    private boolean lahestyPelaajaaYakselilla(){        
        boolean liikkuu = liiku( 0, suuntaY() );
        if(!liikkuu){
            if(taistelu.taistelukentanSisalla(x + 1 , y + suuntaY() ) ){
                liikkuu = liiku(1, suuntaY());
            }
            if(!liikkuu && taistelu.taistelukentanSisalla(x - 1, y + suuntaY() ) ){
                liikkuu = liiku(-1, suuntaY());
            }
        }
        return liikkuu;
    }
    
    public void toimi() {
        if(hyokkaysalueella(pelaaja)) { 
            hyokkaa(pelaaja);
            return;
        }
        
        lahestyPelaajaa();
        
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x*20, y*20, 20, 20);
    }
    
}
