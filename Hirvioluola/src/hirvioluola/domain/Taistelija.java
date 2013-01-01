
package hirvioluola.domain;

import hirvioluola.loitsut.Loitsu;
import hirvioluola.peli.Taistelu;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Taistelija {
    
    protected int x;
    protected int y;
    protected int hp;
    protected int hpMax;
    protected int voima;
    protected int mp;
    protected int mpMax;
    protected List<Loitsu> loitsut;    
    protected Taistelu taistelu;
    

    public int getHpMax() {
        return hpMax;
    }

    public int getVoima() {
        return voima;
    }

    public Taistelija(int x, int y, int voima, int hpMax, int mpMax) {
        this.x = x;
        this.y = y;
        this.voima = voima;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.mpMax = mpMax;
        this.mp = mpMax;
        loitsut = new ArrayList<>();        
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }    

    public int getHp() {
        return hp;
    }
    
    public Taistelu getTaistelu() {
        return this.taistelu;
    }
    
    public void setTaistelu(Taistelu taistelu){
        this.taistelu = taistelu;
    }
    
    public void vahingoitu(int vahinko){
        if(this.hp < vahinko){
            this.hp = 0;
        }
        else{
            this.hp -= vahinko;
        }    
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
    
    public abstract char merkki();
    
    public void liiku(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    public boolean hyokkaysalueella(Taistelija kohde){
        if(Math.abs(this.x - kohde.getX()) <= 1 && Math.abs(this.y - kohde.getY()) <= 1) return true;
        else return false;
    }
    
    public void hyokkaa(Taistelija kohde){
        kohde.vahingoitu(this.voima);
    }
    
    
}
