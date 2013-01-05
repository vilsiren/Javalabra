package hirvioluola.peli;

import hirvioluola.domain.Este;
import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Suuntaloitsu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Taistelu {
    
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    private List<Liittolainen> liittolaiset;
    private List<Este> esteet;
    private int leveys;
    private int korkeus;
    private Taistelukayttis kayttis;

    public Taistelu(int leveys, int korkeus) {

        this.viholliset = new ArrayList<>();
        this.esteet = new ArrayList<>();
        this.liittolaiset = new ArrayList<>();
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    public void setPelaaja(Pelaaja pelaaja, int x, int y){
        if(olioRuudussa(x,y) != null || !taistelukentanSisalla(x,y)){
            return;
        }
        this.pelaaja = pelaaja;
        pelaaja.setTaistelu(this);
        pelaaja.setX(x);
        pelaaja.setY(y);
    }
    
    public void setKayttis(Taistelukayttis kayttis){
        this.kayttis = kayttis;
    } 
    
    public Taistelukayttis getKayttis(){
        return this.kayttis;
    }
    
    public void lisaaOlio(Ruutuolio olio, int x, int y){
        if( !taistelukentanSisalla( x, y ) ){
            return;
        }
        if(olioRuudussa(x,y) != null){
            return;
        }
        if(olio instanceof Vihollinen){
            Vihollinen v = (Vihollinen) olio;
            viholliset.add(v);
        }
        if(olio instanceof Este){
            Este e = (Este) olio;
            esteet.add(e);
        }
        if(olio instanceof Liittolainen){
            Liittolainen l = (Liittolainen) olio;
            liittolaiset.add(l);
        }
        if(olio instanceof Pelaaja){
            Pelaaja p = (Pelaaja) olio;
            this.pelaaja = p;
        }
                
        olio.setTaistelu(this);
        olio.setX(x);
        olio.setY(y);
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public List<Vihollinen> getViholliset() {
        return viholliset;
    }
    
    public List<Liittolainen> getLiittolaiset() {
        return liittolaiset;
    }
    
    public List<Este> getEsteet(){
        return esteet;
    }
    
    public List<Ruutuolio> ruutuOliot() {
        List<Ruutuolio> oliot = new ArrayList<>();
        oliot.addAll(viholliset);
        oliot.addAll(esteet);
        oliot.addAll(liittolaiset);
        if(pelaaja != null) oliot.add(pelaaja);
        return oliot;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    public boolean taistelukentanSisalla(int x, int y){
        if(x < 0 || x >= leveys){
            return false;
        }
        if(y < 0 || y >= korkeus){
            return false;
        }
        return true;        
    }
    
    public Ruutuolio olioRuudussa(int x, int y){
        for(Ruutuolio olio : ruutuOliot() ){
            if(olio.getX() == x && olio.getY() == y){
                return olio;
            }
        }
        return null;
    }
    
    public void paivitaListat(){
        Iterator<Vihollinen> iter = viholliset.iterator();
        while(iter.hasNext()){
            Vihollinen v = iter.next();
            if(v.getHp() <= 0){
                v.setTaistelu(null);
                iter.remove();
            }
        }
        Iterator<Este> iter2 = esteet.iterator();
        while(iter2.hasNext()){
            Este e = iter2.next();
            if(e.getHp() <= 0){
                e.setTaistelu(null);
                iter2.remove();
            }
        }
        Iterator<Liittolainen> iter3 = liittolaiset.iterator();
        while(iter3.hasNext()){
            Liittolainen l = iter3.next();
            if(l.getHp() <= 0){
                l.setTaistelu(null);
                iter3.remove();
            }
        }
    }
        
    public void tekoalyTaistelijatToimii(){
        for(Liittolainen l : liittolaiset){
            l.toimi();
            kayttis.paivita();
        }
        for(Vihollinen vihollinen : viholliset){            
            vihollinen.toimi();
            kayttis.paivita();
        }
    }        
    
    public String loitsulista(){
        StringBuilder sb = new StringBuilder();
        for(Loitsu loitsu : pelaaja.getLoitsut()){
            sb.append("\n");
            sb.append(pelaaja.getLoitsut().indexOf(loitsu));
            sb.append(" ");
            sb.append(loitsu);
 
        }
        return sb.toString();
    }
    
    public String pelaajanStatus(){
        return "HP: " + pelaaja.getHp() + "/" + pelaaja.getHpMax() + " MP: "
                + pelaaja.getMp() + "/" + pelaaja.getMpMax() + " Voima: "
                + pelaaja.getVoima();
    }
    
    private void teeLoitsu(Loitsu loitsu){
        
        if(loitsu instanceof Ruutuloitsu){
            Ruutuloitsu ruutuloitsu = (Ruutuloitsu) loitsu;
            kayttis.valitseRuutu(ruutuloitsu);
        }    
        
        else if(loitsu instanceof Suuntaloitsu){
            Suuntaloitsu suuntaloitsu = (Suuntaloitsu) loitsu;
            kayttis.valitseSuunta(suuntaloitsu);
        }
        
        loitsu.suorita(pelaaja);
    }                           
    
    public int[] suunta(String komento){
        int[] suunta = new int[2];
            if(komento.equals("YLÖS")){
                suunta[0] = 0;
                suunta[1] = -1;
            }
            else if(komento.equals("YLÄOIKEA")){
                suunta[0] = 1;
                suunta[1] = -1;
            }
            else if(komento.equals("OIKEA")){
                suunta[0] = 1;
                suunta[1] = 0;
            }
            else if(komento.equals("ALAOIKEA")){
                suunta[0] = 1;
                suunta[1] = 1;
            }
            else if(komento.equals("ALAS")){
                suunta[0] = 0;
                suunta[1] = 1;
            }
            else if(komento.equals("ALAVASEN")){
                suunta[0] = -1;
                suunta[1] = 1;
            }
            else if(komento.equals("VASEN")){
                suunta[0] = -1;
                suunta[1] = 0;
            }
            else if(komento.equals("YLÄVASEN")){
                suunta[0] = -1;
                suunta[1] = -1;
            }
            else{
                return null;
            }
        return suunta;
    }
    
    private void liikutaPelaajaa(int dx, int dy){       
             
            int x = pelaaja.getX() + dx;
            int y = pelaaja.getY() + dy;
            if(taistelukentanSisalla(x, y)){
                Ruutuolio olio = olioRuudussa(x,y);
                if(olio != null){
                    pelaaja.hyokkaa(olio);
                }
                else{
                    pelaaja.liiku(dx, dy);
                }
            }        
        
    }
    
    private void toteutaPelaajanKomento(){
        while(true){
            String komento = kayttis.odotaPelaajanKomentoa();
            if(komento.equals("ÄLÄ TEE MITÄÄN")) break;
            int suunta[] = suunta(komento);
            if(suunta != null){
                liikutaPelaajaa(suunta[0], suunta[1]);
                break;
            }
            
            else {
                try{
                    int loitsunnumero = Integer.parseInt(komento);
                    if(loitsunnumero >= pelaaja.getLoitsut().size()){
                        throw new Exception();
                    }
                    Loitsu loitsu = pelaaja.getLoitsut().get(loitsunnumero);
                    if(loitsu.kuluttaaMPta() > pelaaja.getMp()){
                        throw new Exception();
                    }
                    teeLoitsu(loitsu);
                    break;
                }
                catch(Exception e){                
                }
            }                        
        }
    }
    
    public void suorita(){
        kayttis.paivita();
        while(pelaaja.getHp() > 0 && !viholliset.isEmpty()){
            toteutaPelaajanKomento();
            paivitaListat();
            kayttis.paivita();
            tekoalyTaistelijatToimii();
            paivitaListat();
        }
    }

    
    
}
