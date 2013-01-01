package hirvioluola.peli;

import hirvioluola.domain.Hirvio;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.gui.Piirtoalusta;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.ToimintoJolleValitaanRuutu;
import hirvioluola.loitsut.ToimintoJolleValitaanSuunta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JLabel;

public class Taistelu {
    
    private Pelaaja pelaaja;
    private List<Hirvio> hirviot;
    private int leveys;
    private int korkeus;
    private Scanner lukija;
    public final String suunnat = "qweadzxc";
    private Piirtoalusta alusta;
    private JLabel status;
    private String komento;

    public Taistelu(int leveys, int korkeus) {

        this.hirviot = new ArrayList<>();
        this.leveys = leveys;
        this.korkeus = korkeus;
        lukija = new Scanner(System.in);
        komento = "";
    }
    
    public void setPelaaja(Pelaaja pelaaja, int x, int y){
        this.pelaaja = pelaaja;
        pelaaja.setTaistelu(this);
        pelaaja.setX(x);
        pelaaja.setY(y);
    }
    
    public void setKomento(String komento){
        this.komento = komento;
    }
    
    public void setAlusta(Piirtoalusta alusta){
        this.alusta = alusta;
    }
    
    public void setStatus(JLabel status){
        this.status = status;
    }
    
    public void lisaaHirvio(Hirvio hirvio, int x, int y){
        if(x == pelaaja.getX() && y == pelaaja.getY()){
            return;
        }
        if( !taistelukentanSisalla( x, y ) ){
            return;
        }
        for(Hirvio hirvio2 : hirviot){
            if(x == hirvio2.getX() && y == hirvio2.getY()){
                return;
            }          
        }
        hirviot.add(hirvio);
        hirvio.setTaistelu(this);
        hirvio.setX(x);
        hirvio.setY(y);
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public List<Hirvio> getHirviot() {
        return hirviot;
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
    
    public Hirvio hirvioRuudussa(int x, int y){
        for(Hirvio hirvio : hirviot){
            if(hirvio.getX() == x && hirvio.getY() == y){
                return hirvio;
            }
        }
        return null;
    }
    
    public void paivitaHirviolista(){
        Iterator<Hirvio> iter = hirviot.iterator();
        while(iter.hasNext()){
            if(iter.next().getHp() <= 0){
                    iter.remove();
            }
        }
    }          
        
    public void hirviotToimii(){
        for(Hirvio hirvio : hirviot){            
            hirvio.toimi();
        }
    }
    
    public void tulostaKartta() {
        char[][] kartta = new char[leveys][korkeus];
        
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                kartta[x][y] = '.';
            }
        }        
        
            for (Hirvio h : hirviot) {
                kartta[h.getX()][h.getY()] = h.merkki();
            }        
        
        kartta[pelaaja.getX()][pelaaja.getY()] = pelaaja.merkki();
        
        System.out.println();
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                System.out.print(kartta[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private void tulostaPelaajanLoitsut(){
        for(Loitsu loitsu : pelaaja.getLoitsut()){
            System.out.println(pelaaja.getLoitsut().indexOf(loitsu) + " " + loitsu);
        }
    }    
    
    private void teeLoitsu(int loitsunNumero){
        if(loitsunNumero >= pelaaja.getLoitsut().size()){
            return;
        }
        Loitsu loitsu = pelaaja.getLoitsut().get(loitsunNumero);
        if(pelaaja.getMp() < loitsu.kuluttaaMPta() ){
            return;
        }
        
        if(loitsu instanceof ToimintoJolleValitaanRuutu){
            ToimintoJolleValitaanRuutu ruutuloitsu = (ToimintoJolleValitaanRuutu) loitsu;
            valitseRuutu(ruutuloitsu);
        }    
        
        else if(loitsu instanceof ToimintoJolleValitaanSuunta){
            ToimintoJolleValitaanSuunta suuntaloitsu = (ToimintoJolleValitaanSuunta) loitsu;
            valitseSuunta(suuntaloitsu);
        }
        
        loitsu.suorita(pelaaja);
    }
                       
    
    private void valitseRuutu(ToimintoJolleValitaanRuutu toiminto){
        while(true){
            try{
                int x, y;
                System.out.print("x: ");
                x = Integer.parseInt(lukija.nextLine());
                System.out.print("y: ");
                y = Integer.parseInt(lukija.nextLine());
                if(toiminto.setRuutu(x, y, this) == false ){
                    throw new Exception();
                }
                break;
            }
            catch(Exception e){
            }
        }      
    }
    
    private void valitseSuunta(ToimintoJolleValitaanSuunta toiminto){
        while(true){
            try{
                int[] suunta = suunta(lukija.nextLine());
                if(suunta == null){
                    throw new Exception();               
                }
                toiminto.setSuunta(suunta[0], suunta[1]);
                break;
            }
            catch(Exception e){               
            }
        }
    }
    
    private int[] suunta(String komento){
        int[] suunta = new int[2];
            if(komento.equals("w")){
                suunta[0] = 0;
                suunta[1] = -1;
            }
            else if(komento.equals("e")){
                suunta[0] = 1;
                suunta[1] = -1;
            }
            else if(komento.equals("d")){
                suunta[0] = 1;
                suunta[1] = 0;
            }
            else if(komento.equals("c")){
                suunta[0] = 1;
                suunta[1] = 1;
            }
            else if(komento.equals("x")){
                suunta[0] = 0;
                suunta[1] = 1;
            }
            else if(komento.equals("z")){
                suunta[0] = -1;
                suunta[1] = 1;
            }
            else if(komento.equals("a")){
                suunta[0] = -1;
                suunta[1] = 0;
            }
            else if(komento.equals("q")){
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
                Hirvio hirvio = hirvioRuudussa(x,y);
                if(hirvio != null){
                    pelaaja.hyokkaa(hirvio);
                }
                else{
                    pelaaja.liiku(dx, dy);
                }
            }        
        
    }
    
    public void suorita(){
        while(pelaaja.getHp() > 0 && !hirviot.isEmpty()){
            tulostaPelaajanLoitsut();
            System.out.println("HP: " + pelaaja.getHp() + " MP: " + pelaaja.getMp());
            tulostaKartta();
            
            String syote = lukija.nextLine();
            int suunta[] = suunta(syote);
            if(suunta != null){
                liikutaPelaajaa(suunta[0], suunta[1]);
            }
            
            else{
                try{
                    int loitsunnumero = Integer.parseInt(syote);
                    teeLoitsu(loitsunnumero);
                }
                catch(Exception e){                
                }
            }
            paivitaHirviolista();
            hirviotToimii();
        }
        if(pelaaja.getHp() <= 0){
            System.out.println("Hävisit!");
        }
        else{
            System.out.println("Voitit!");
        }
    }

    public void kierros() {;
        status.setText("HP: " + pelaaja.getHp() + " MP: " + pelaaja.getMp());
//        if(pelaaja.getHp() <= 0 || hirviot.isEmpty()){
//            return;
//        }
        if(!komento.equals("")){
            liikutaPelaajaa(suunta(komento)[0], suunta(komento)[1]);
        }
        paivitaHirviolista();
        hirviotToimii();
        alusta.repaint();
        komento = "";
    }    
    
}
