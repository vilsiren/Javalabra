package hirvioluola.peli;

import hirvioluola.domain.Este;
import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.loitsut.KutsuLiittolainen;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Parannus;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Salama;
import hirvioluola.loitsut.Suuntaloitsu;
import hirvioluola.loitsut.Taikanuoli;
import hirvioluola.loitsut.Teleportaatio;
import hirvioluola.loitsut.Tulikeha;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Pelaajan on taistelun voittaekseen tuhottava taistelun hirviot. Pelaaja häviää
 * taistelun jos pelaajan hp:t menevät nollille. Taisteluun osallistuu mahdollisesti
 * myös pelaajan liittolaiset ja taistelukentällä voi olla myös esteitä.
 * @author Ville
 */

public class Taistelu {
    
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    private List<Liittolainen> liittolaiset;
    private List<Este> esteet;
    private int leveys;
    private int korkeus;
    private int kokemuspisteita;
    private Hirvioluolakayttis kayttis;

    public Taistelu(int leveys, int korkeus) {
        
        this.kokemuspisteita = 0;
        this.viholliset = new ArrayList<>();
        this.esteet = new ArrayList<>();
        this.liittolaiset = new ArrayList<>();
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    public void setPelaaja(Pelaaja pelaaja, int x, int y){
        if( olioRuudussa(x,y) != null || !taistelukentanSisalla(x,y)){
            return;
        }
        this.pelaaja = pelaaja;
        pelaaja.setTaistelu(this);
        pelaaja.setX(x);
        pelaaja.setY(y);
        for(Vihollinen v : viholliset){
            v.setKohde(pelaaja);
        }
    }
    
    public void setKayttis(Hirvioluolakayttis kayttis){
        this.kayttis = kayttis;
    } 
    
    public Hirvioluolakayttis getKayttis(){
        return this.kayttis;
    }
    
    /**
     * Lisää ruutuolion taistelukentälle ja asettaa koordinaatit
     * @param olio
     * @param x
     * @param y 
     */
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
            kokemuspisteita += v.kokemuspisteita();
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
            setPelaaja(p,x,y);
            return;
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
    
    /**
     * Palauttaa sen määrän kokemuspisteitä, jonka pelaaja saa voitettuaan taistelun
     * @return 
     */
    public int kokemuspisteita(){
        return kokemuspisteita;
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
    
    /**
     * Ruutuoliolistoista poistetaan oliot, jotka on tuhottu.
     */
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
        
    private void tekoalyTaistelijatToimii(){
        for(Liittolainen l : liittolaiset){
            l.toimi();
            kayttis.paivita();
        }
        paivitaListat();
        kayttis.paivita();
        for(Vihollinen vihollinen : viholliset){            
            vihollinen.toimi();
            kayttis.paivita();
        }
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
    
    /**
     * Liikuttaa pelaajaa valittuun suuntaan, mikäli suunnassa oleva ruutu
     * on tyhja. Jos ruudussa on olio, hyökkää olion kimpuuun.
     * @param dx
     * @param dy 
     */
    private void liikuTaiHyokkaa(int dx, int dy){       
             
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
    
    private boolean riittaakoPelaajanMptYhteenkaanLoitsuun(){
        for(Loitsu loitsu : pelaaja.getLoitsut()){
            if(loitsu.kuluttaaMPta() <= pelaaja.getMp()){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Odotetaan että käyttöliittymä ilmoittaa pelaajan komennot ja totetuttaa
     * komennon mikäli mahdollista.
     */
    private void toteutaPelaajanKomento(){
        while(true){
            String komento = kayttis.odotaPelaajanKomentoa();
            if(komento.equals("ÄLÄ TEE MITÄÄN")) break;
            if(komento.equals("HUIJAUSSALASANA")){
                pelaaja.huijaus();
            }
            int suunta[] = suunta(komento);
            if(suunta != null){
                liikuTaiHyokkaa(suunta[0], suunta[1]);
                break;
            }
            
            else {
                if(riittaakoPelaajanMptYhteenkaanLoitsuun() == false) continue;
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
                catch(NumberFormatException e){
                    break;
                }
                catch(Exception e){
                }
            }                        
        }
    }
    
    /**
     * Aloittaa taistelun. Jokaisella kierroksella pelaaja valitsee mitä tekee,
     * jonka jälkeen tekoalytaistelijat toimivat. Taistelu loppuu kun pelaaja kuolee tai
     * kaikki viholliset kuolevat
     */
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
