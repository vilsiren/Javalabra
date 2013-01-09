
package hirvioluola.domain;

import hirvioluola.peli.Taistelu;

/**
 * Tekoalytaistelija on taistelija jolle on määritelty jonkinlainen toimintalogiikka.
 * Kaikki muut taistelijat paitsi pelaaja ovat tekoalytaistelijoita.
 * @author Ville
 */

public abstract class Tekoalytaistelija extends Taistelija {
    
    protected Taistelija kohde;

    public Tekoalytaistelija(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
    }
    
    @Override
    public void setTaistelu(Taistelu taistelu){
        super.setTaistelu(taistelu);
        if(taistelu == null){
            kohde = null;
        }
    }
    
    public void setKohde(Taistelija kohde){
        this.kohde = kohde;
    }
    
    /**
     * Kertoo missä suunnassa kohde on x-akselilla tekoalytaistelijaan suhteutettuna
     * @return 
     */
    private int suuntaX(){
        if(kohde.x == this.x){
            return 0;
        }
        if(kohde.x  < this.x){
            return -1;
        }
        else{
            return 1;
        }
    }
    
    /**
     * Kertoo missä suunnassa kohde on y-akselilla tekoalytaistelijaan suhteutettuna
     * @return 
     */
    private int suuntaY(){
        if(kohde.y == this.y){
            return 0;
        }
        if(kohde.y < this.y){
            return -1;
        }
        else{
            return 1;
        }
    }
    
    /**
     * Lähestyy kohdetta molemmilla akseleilla jos se on mahdollista, jos ei
     * niin lähestyy ensisijaisesti y-akselilla, toissijaisesti x-akselilla.
     * @return 
     */
    protected boolean lahestyKohdetta(){
        if(suuntaY() == 0){
            return lahestyKohdettaXakselilla();            
        }
        
        else if(suuntaX() == 0 ){
            return lahestyKohdettaYakselilla();
        }
        else if( suuntaanVoiLiikkua(suuntaX(), suuntaY()) ){
            liiku(suuntaX(), suuntaY());
            return true;

        }        
        else if(lahestyKohdettaYakselilla()){
            return true;
        }
        else if(lahestyKohdettaXakselilla()){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean lahestyKohdettaXakselilla(){        
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
    
    private boolean lahestyKohdettaYakselilla(){        
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
    
    /**
     * Selvittää onko valitussa suunnassa oleva ruutu tyhja ja taistelukentan sisalla
     * @param dx
     * @param dy
     * @return 
     */
    private boolean suuntaanVoiLiikkua(int dx, int dy){
        if(!taistelu.taistelukentanSisalla(x + dx, y + dy)){
            return false;
        }
        if(taistelu.olioRuudussa(x + dx,y + dy) == null){
           return true; 
        }
        else{
            return false;
        }
    }
    
    public abstract void toimi();                
    
}
