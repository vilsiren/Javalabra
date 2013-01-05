
package hirvioluola.peli;

import hirvioluola.domain.Pelaaja;
import java.util.ArrayList;
import java.util.List;

public class Peli {
    
    private Pelaaja pelaaja;
    private ArrayList<Taistelu> taistelut;
    private Taistelukayttis kayttis;
    
    public Peli(){
        pelaaja = new Pelaaja(10,10,2);
        taistelut = new ArrayList<>();
    }
    
    public void lisaaTaistelu(Taistelu taistelu){
        taistelu.setPelaaja(pelaaja,0,0);
        taistelut.add(taistelu);
    }
    
    public List<Taistelu> getTaistelut(){
        return taistelut;
    }
    
    public void setKayttis(Taistelukayttis kayttis){
        this.kayttis = kayttis;
        for(Taistelu taistelu : taistelut){
            taistelu.setKayttis(kayttis);
        }
    }
    
    public void run(){
        for(Taistelu taistelu : taistelut){
            taistelu.suorita();
            if(pelaaja.getHp() <= 0){
                break;
            }
            else{
                pelaaja.setHpMpMax();
            }
        }
    }
}
