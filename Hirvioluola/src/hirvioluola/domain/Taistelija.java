
package hirvioluola.domain;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.peli.Taistelu;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Taistelija extends Ruutuolio {
    
    protected int voima;
    protected int mp;
    protected int mpMax;
    protected List<Loitsu> loitsut;    
    

    public int getHpMax() {
        return hpMax;
    }

    public int getVoima() {
        return voima;
    }

    public Taistelija(int voima, int hpMax, int mpMax) {
        super(hpMax);
        this.voima = voima;        
        this.mpMax = mpMax;
        this.mp = mpMax;
        loitsut = new ArrayList<>();        
    }

    public void setHpMpMax(){
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

    public void setMp(int mp) {
        if(mp < 0 || mp > mpMax){
            return;
        }
        this.mp = mp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public void setMpMax(int mpMax) {
        if(mpMax < 0){
            return;
        }
        this.mpMax = mpMax;
    }

    public List<Loitsu> getLoitsut() {
        return loitsut;
    }
    
    public void lisaaLoitsu(Loitsu loitsu) {
        loitsut.add(loitsu);
    }    
    
    public void vahennaMPta(int vahennys){
        if(vahennys > this.mp){
            this.mp = 0;
        }
        else{
            this.mp -= vahennys;
        }
    }
    
    public abstract void piirra(Graphics g);
    
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
