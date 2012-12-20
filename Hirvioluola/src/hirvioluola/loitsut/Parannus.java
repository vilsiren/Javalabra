package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

public class Parannus implements Loitsu{
    
    private int kuluttaaMPta;
    private int parannus;
    
    public Parannus(int kuluttaaMPta, int parannus){
        this.kuluttaaMPta = kuluttaaMPta;
        this.parannus = parannus;
    }

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }

    @Override
    public void suorita(Taistelija kohde) {
        kohde.parannu(parannus);
    }
    
}
