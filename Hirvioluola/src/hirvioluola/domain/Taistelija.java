
package hirvioluola.domain;

import hirvioluola.peli.Taistelu;

public abstract class Taistelija {
    
    protected int x;
    protected int y;
    protected int hp;
    protected int hpMax;
    protected int voima;
    protected Taistelu taistelu;

    public int getHpMax() {
        return hpMax;
    }

    public int getVoima() {
        return voima;
    }

    public Taistelija(int x, int y, int voima, int hpMax) {
        this.x = x;
        this.y = y;
        this.voima = voima;
        this.hpMax = hpMax;
        this.hp = hpMax;
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
    
    public void vahingoitu(int vahinko){
        this.hp -= vahinko;
    }
    
    public void parannu(int parannus){
        if(this.hp + parannus >= this.hpMax){
            this.hp = hpMax;
        }
        else{
            this.hp += parannus;
        }
    }
    
    public abstract void toimi();
    
    public boolean liiku(int dx, int dy){
        for(Taistelija hirvio : taistelu.getHirviot()){
            if(hirvio.getX() == this.x + dx && hirvio.getY() == this.y + dy){
                return false;
            }
        }
        this.x += dx;
        this.y += dy;
        return true;
    }
    
    public boolean hyokkaysalueella(Taistelija kohde){
        if(kohde.getX() == this.x && Math.abs(this.y - kohde.getY()) == 1) return true;
        if(kohde.getY() == this.y && Math.abs(this.x - kohde.getX()) == 1) return true;
        else return false;
    }
    
    public void hyokkaa(Taistelija kohde){
        kohde.vahingoitu(this.voima);
    }
    
}
