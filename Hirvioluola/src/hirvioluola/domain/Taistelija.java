
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
    
    public abstract void toimi();
    
    public boolean liiku(int dx, int dy){
        for(Taistelija hirvio : taistelu.getHirviot()){
            if(hirvio.x == this.x + dx && hirvio.y == this.y + dy){
                return false;
            }
        }
        this.x += dx;
        this.y += dy;
        return true;
    }
    
    public boolean hyokkaysalueella(Taistelija kohde){
        if(kohde.x == this.x && Math.abs(this.y - kohde.y) == 1) return true;
        if(kohde.y == this.y && Math.abs(this.x - kohde.x) == 1) return true;
        else return false;
    }
    
    public void hyokkaa(Taistelija kohde){
        kohde.hp -= this.voima;
    }
    
}
