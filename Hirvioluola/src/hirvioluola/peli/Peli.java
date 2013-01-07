
package hirvioluola.peli;

import hirvioluola.domain.Pelaaja;
import hirvioluola.loitsut.Loitsu;
import java.util.ArrayList;
import java.util.List;

/**
 * Peli on sarja taisteluita. Jos pelaaja häviää yhdenkin taistelun, peli päättyy.
 * Pelaaja voittaa voittaessaan kaikki taistelut. Taisteluiden välissä pelaaja saa
 * tilaisuuden käyttää taisteluista saatuja kokemuspisteitään voimaan, hp:een, mp:een
 * tai uuteen loitsuun.
 * @author Ville
 */

public class Peli {
    
    private Pelaaja pelaaja;
    private ArrayList<Taistelu> taistelut;
    private List<Loitsu> loitsut; 
    private Hirvioluolakayttis kayttis;
    
    public Peli(int alkuVoima, int alkuHp, int alkuMp){
        pelaaja = new Pelaaja(alkuVoima,alkuHp, alkuMp);
        taistelut = new ArrayList<>();
        loitsut = new ArrayList<>();
    }
    
    public void lisaaTaistelu(Taistelu taistelu){
        taistelut.add(taistelu);
    }
    
    public void lisaaLoitsu(Loitsu loitsu){
        loitsut.add(loitsu);
    }
    
    public String loitsuluettelo(){
        StringBuilder sb = new StringBuilder();
        for(Loitsu loitsu : loitsut){
            sb.append("\n");
            sb.append(loitsut.indexOf(loitsu));
            sb.append(" ");
            sb.append(loitsu);
            sb.append(", vaatii kokemuspisteitä: ");
            sb.append(loitsu.vaatiiKokemuspisteita());
        }
        return sb.toString();
    }
    
    public Pelaaja getPelaaja(){
        return pelaaja;
    }
    
    public List<Taistelu> getTaistelut(){
        return taistelut;
    }
    
    public void setKayttis(Hirvioluolakayttis kayttis){
        this.kayttis = kayttis;
        kayttis.setPeli(this);
        for(Taistelu taistelu : taistelut){
            taistelu.setKayttis(kayttis);
        }
    }
    
    private void kaytaKokemuspisteita(){
        while(pelaaja.getKokemuspisteet() > 0){
            String mihinKaytetaan = kayttis.valitseKokemuspisteidenKaytto();
            
            if(mihinKaytetaan.equals("VOIMA")){
                pelaaja.kaytaKokemuspisteita(1);
                pelaaja.kasvataVoimaa(1);                
            }
            else if(mihinKaytetaan.equals("HP")){
                pelaaja.kaytaKokemuspisteita(1);
                pelaaja.kasvataHpMaxia(2);
            }
            else if(mihinKaytetaan.equals("MP")){
                pelaaja.kaytaKokemuspisteita(1);
                pelaaja.kasvataMpMaxia(3);
            }
            else if(mihinKaytetaan.equals("LOITSU")){
                opiUusiLoitsu();
            }
            else{
                break;
            }
        }
    }
    
    private void opiUusiLoitsu(){
        int loitsuNro = kayttis.valitseOpittavaLoitsu();
        if(loitsuNro >= loitsut.size() || loitsuNro < 0){
            return;
        }
        Loitsu opittavaLoitsu = loitsut.get(loitsuNro);
        if(opittavaLoitsu.vaatiiKokemuspisteita() > pelaaja.getKokemuspisteet()){
            opiUusiLoitsu();
        }
        pelaaja.kaytaKokemuspisteita(opittavaLoitsu.vaatiiKokemuspisteita());
        pelaaja.lisaaLoitsu(opittavaLoitsu);
        loitsut.remove(opittavaLoitsu);
    }
    
    public void run(){
        for(Taistelu taistelu : taistelut){
            taistelu.setPelaaja(pelaaja,0,0);
            kayttis.setTaistelu(taistelu);
            taistelu.suorita();
            if(pelaaja.getHp() <= 0){
                break;
            }
            else{
                pelaaja.lisaaKokemuspisteita(taistelu.kokemuspisteita());
                kaytaKokemuspisteita();
                pelaaja.setHpMpToMax();
            }
        }
    }
}
