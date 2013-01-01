package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;

public class Hirvio extends Taistelija{
    
    protected Pelaaja pelaaja;

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
        }
        
        else if(suuntaX() == 0 ){
            lahestyPelaajaaYakselilla();
        }
        else if( suuntaanVoiLiikkua(suuntaX(), suuntaY()) ){
            liiku(suuntaX(), suuntaY());

        }        
        else if(!lahestyPelaajaaYakselilla()){
            lahestyPelaajaaXakselilla();
        }
    }
    private boolean lahestyPelaajaaXakselilla(){        
        if(suuntaanVoiLiikkua(suuntaX(), 0) ){
            liiku(suuntaX(), 0);
            return true;
        }
        else if(suuntaanVoiLiikkua(suuntaX(), 1)){
            liiku(suuntaX(), 1);
            return true;
        }
        else if(suuntaanVoiLiikkua(suuntaX(), -1)){
            liiku(suuntaX(), -1);
            return true;
        }
        else{
            return false;
        }
    }       
    
    private boolean lahestyPelaajaaYakselilla(){        
        if(suuntaanVoiLiikkua( 0, suuntaY() ) ){
            liiku(0, suuntaY() );
            return true;
        }
        else if(suuntaanVoiLiikkua( 1, suuntaY() )){
            liiku(1, suuntaY());
            return true;
        }
        else if(suuntaanVoiLiikkua( -1, suuntaY() )){
            liiku(-1, suuntaY());
            return true;
        }
        else{
            return false;
        }
    }    
    
    private boolean suuntaanVoiLiikkua(int dx, int dy){
        if(!taistelu.taistelukentanSisalla(x + dx, y + dy)){
            return false;
        }
        if(taistelu.hirvioRuudussa(x + dx,y + dy) == null){
           return true; 
        }
        else{
            return false;
        }
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

    @Override
    public char merkki() {
        return 'h';
    }
    
}
