package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

public class Parannus extends Loitsu{
    
    private int parannus;
    private int kuluttaaMPta;
    
    public Parannus(int kuluttaaMPta, int parannus){
        this.kuluttaaMPta = kuluttaaMPta;
        this.parannus = parannus;
    }

    @Override
    public void teeLoitsu(Taistelija loitsija) {
        loitsija.parannu(parannus);
    }
    
    @Override
    public String toString(){
        return "Parannus, parantaa: " + parannus + " hp, kuluttaa: " + kuluttaaMPta() + "mp";
    }

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }
}
