package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;

public class Parannus extends Loitsu{
    
    private int parannus;
    
    public Parannus(int kuluttaaMPta, int parannus){
        super(kuluttaaMPta);
        this.parannus = parannus;
    }

    @Override
    public void suorita(Loitsija loitsija) {
        super.suorita(loitsija);
        loitsija.parannu(parannus);
    }
    
}
