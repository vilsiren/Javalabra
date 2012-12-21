package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;

public class Parannus extends Loitsu{
    
    private int parannus;
    
    public Parannus(int kuluttaaMPta, int parannus, Loitsija loitsija){
        super(kuluttaaMPta, loitsija);
        this.parannus = parannus;
    }

    @Override
    public void suorita() {
        super.suorita();
        loitsija.parannu(parannus);
    }
    
}
