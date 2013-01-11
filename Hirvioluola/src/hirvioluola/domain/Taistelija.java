
package hirvioluola.domain;

import java.awt.Graphics;

/**
 * Taistelijoita ovat kaikki taisteluun osallistuvat, liikkuvat ja toimivat oliot
 * @author Ville
 */

public abstract class Taistelija extends Ruutuolio {
    
    protected int voima;
    protected int mp;
    protected int mpMax;       



    public int getVoima() {
        return voima;
    }

    public Taistelija(int voima, int hpMax, int mpMax) {
        super(hpMax);
        this.voima = voima;        
        this.mpMax = mpMax;
        this.mp = mpMax;      
    }

    public void setHpMpToMax(){
        this.hp = hpMax;
        this.mp = mpMax;
    }
    
    public void parannu(int parannus){
        if(this.hp + parannus >= this.hpMax){
            this.hp = hpMax;
        }
        else{
            this.hp += parannus;
        }
    }
    
    public int getMp() {
        return mp;
    }

    public int getMpMax() {
        return mpMax;
    }    
    
    public void vahennaMPta(int vahennys){
        if(vahennys > this.mp){
            this.mp = 0;
        }
        else{
            this.mp -= vahennys;
        }
    }    
    
    public void liiku(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    public boolean hyokkaysalueella(Ruutuolio kohde){
        if(Math.abs(this.x - kohde.getX()) <= 1 && Math.abs(this.y - kohde.getY()) <= 1) return true;
        else return false;
    }
    
    public void hyokkaa(Ruutuolio kohde){
        kohde.vahingoitu(this.voima);
        if(taistelu != null && taistelu.getKayttis() != null){
            taistelu.getKayttis().piirraHyokkays(this.x, this.y, kohde.getX(), kohde.getY());            
        }
    }
    
    
}
