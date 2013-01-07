
package hirvioluola.domain;

import hirvioluola.peli.Taistelu;

/**
 * Ruutuolioita ovat kaikki elolliset tai elottomat oliot, jotka voivat sijaita taistelukentällä. 
 */

public abstract class Ruutuolio {
    
    protected int x, y, hp, hpMax;
    protected Taistelu taistelu;
    
    public Ruutuolio(int hpMax){
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.x = -1;
        this.y = -1;
    }
    
   public void setX(int x) {
        if(taistelu == null){
            return;
        }
        else if(x >= 0 && x < taistelu.getLeveys()){
            this.x = x;
        }
    }

    public void setY(int y) {
        if(taistelu == null){
            return;
        }
        else if(y >= 0 && y < taistelu.getKorkeus()){
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }    

    public int getHpMax() {
        return hpMax;
    }

    public int getHp() {
        return hp;
    }
    
    public Taistelu getTaistelu() {
        return this.taistelu;
    }
    
    public void setTaistelu(Taistelu taistelu){
        this.taistelu = taistelu;
        if(taistelu == null){
            this.x = -1;
            this.y = -1;
        }
    }    
    
    public void vahingoitu(int vahinko){
        if(this.hp < vahinko){
            this.hp = 0;
        }
        else{
            this.hp -= vahinko;
        }    
    }
    
    public abstract char merkki();
}
