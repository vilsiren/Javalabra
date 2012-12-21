package hirvioluola.loitsut;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;

public class Parannus implements Loitsu{
    
    private int kuluttaaMPta;
    private int parannus;
    private Taistelija loitsija;
    
    public Parannus(int kuluttaaMPta, int parannus, Taistelija loitsija){
        this.loitsija = loitsija;
        this.kuluttaaMPta = kuluttaaMPta;
        this.parannus = parannus;
    }

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }

    @Override
    public void suorita() {
        loitsija.parannu(parannus);
    }
    
}
