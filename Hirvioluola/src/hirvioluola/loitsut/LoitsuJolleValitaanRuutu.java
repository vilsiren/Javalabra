
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Taistelija;

public abstract class LoitsuJolleValitaanRuutu extends Loitsu {
    
    public LoitsuJolleValitaanRuutu(int kuluttaaMPta){
        super(kuluttaaMPta);
    }
    
    public abstract boolean setRuutu(int x, int y, Loitsija loitsija);
}
