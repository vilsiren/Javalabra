package hirvioluola.domain;

import hirvioluola.loitsut.Loitsu;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Pelaaja extends Taistelija {
    
    private int kokemuspisteet;
    private List<Loitsu> loitsut;
    
    public Pelaaja(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
        this.mpMax = mpMax;
        this.mp = mpMax;
        this.loitsut = new ArrayList<>();
        this.kokemuspisteet = 0;
    }
    
    public int getKokemuspisteet(){
        return kokemuspisteet;
    }
    
    public void lisaaKokemuspisteita(int lisays){
        kokemuspisteet += lisays;
    }
    
    public void kaytaKokemuspisteita(int vahennys){
        kokemuspisteet -= vahennys;
    }
    
    public void kasvataVoimaa(int lisays){
        super.voima += lisays;
    }
    
    public void kasvataHpMaxia(int lisays){
        super.hpMax += lisays;
    }
    
    public void kasvataMpMaxia(int lisays){
        super.mpMax += lisays;
    }
    
    public List<Loitsu> getLoitsut() {
        return loitsut;
    }
    
    public void lisaaLoitsu(Loitsu loitsu) {
        loitsut.add(loitsu);
    }    
    

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x*20, y*20, 20, 20);        
    }

    @Override
    public char merkki() {
        return '@';
    }

    public String status(){
        return  "Voima: " + voima + " HP: " + hp + "/" + hpMax 
                + " MP: "  + mp + "/" + mpMax;
    }

    public String loitsuluettelo(){
        StringBuilder sb = new StringBuilder();
        for(Loitsu loitsu : loitsut){
            sb.append("\n");
            sb.append(loitsut.indexOf(loitsu));
            sb.append(" ");
            sb.append(loitsu);
 
        }
        return sb.toString();
    }    
    
}
