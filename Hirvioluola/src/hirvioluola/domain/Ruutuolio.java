
package hirvioluola.domain;

import hirvioluola.peli.Taistelu;

public abstract class Ruutuolio {
    
    protected int x, y, hp, hpMax;
    protected Taistelu taistelu;
    
    public Ruutuolio(int hpMax){
        this.hpMax = hpMax;
        this.hp = hpMax;
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
    
    public abstract char merkki();
}
