package hirvioluola.peli;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taistelu {
    
    private Pelaaja pelaaja;
    private List<Taistelija> hirviot;
    private int leveys;
    private int korkeus;
    private Scanner lukija;

    public Taistelu(Pelaaja pelaaja, int leveys, int korkeus) {
        this.pelaaja = pelaaja;
        pelaaja.setTaistelu(this);
        this.hirviot = new ArrayList<>();
        this.leveys = leveys;
        this.korkeus = korkeus;
        lukija = new Scanner(System.in);
    }
    
    public void lisaaHirvio(Taistelija hirvio){
        if(hirvio.getX() == pelaaja.getX() && hirvio.getY() == pelaaja.getY()){
            return;
        }
        if( !taistelukentanSisalla( hirvio.getX(), hirvio.getY() ) ){
            return;
        }
        for(Taistelija hirvio2 : hirviot){
            if(hirvio.getX() == hirvio2.getX() && hirvio.getY() == hirvio2.getY()){
                return;
            }          
        }
        hirviot.add(hirvio);
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public List<Taistelija> getHirviot() {
        return hirviot;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    private boolean taistelukentanSisalla(int x, int y){
        if(x < 0 || x >= leveys){
            return false;
        }
        if(y < 0 || y >= korkeus){
            return false;
        }
        return true;        
    }
    
    public void tulostaKartta() {
        char[][] kartta = new char[leveys][korkeus];
        
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                kartta[x][y] = '.';
            }
        }
 
        for (Taistelija h : hirviot) {
            kartta[h.getX()][h.getY()] = 'h';
        }
 
        kartta[pelaaja.getX()][pelaaja.getY()] = '@';
        
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                System.out.print(kartta[x][y]);
            }
            System.out.println();
        }        
    }
    
    public void suorita(){
        while(pelaaja.getHp() > 0 && !hirviot.isEmpty()){
            tulostaKartta();
            
            String syote = lukija.nextLine();
            char komento;
            if(syote.equals("")){
                komento = '\u0000';
            }
            else{
                komento = syote.charAt(0);
            }
            
            int dx = 0;
            int dy = 0;
            if(komento == 'w'){
                dx = 0;
                dy = -1;
            }
            if(komento == 'd'){
                dx = 1;
                dy = 0;
            }
            if(komento == 's'){
                dx = 0;
                dy = 1;
            }
            if(komento == 'a'){
                dx = -1;
                dy = 0;
            }            
            
            if(taistelukentanSisalla(pelaaja.getX() + dx, pelaaja.getY() + dy)
                    && !(dx == 0 && dy == 0)){
                boolean liikkuu = pelaaja.liiku(dx, dy);
                if(!liikkuu){
                    Taistelija kohde = null;
                    for(Taistelija hirvio : hirviot){
                        if(hirvio.getX() == pelaaja.getX() + dx && hirvio.getY() == pelaaja.getY() + dy){
                            kohde = hirvio;
                        }
                    }
                    pelaaja.hyokkaa(kohde);
                    if(kohde.getHp() <= 0){
                        hirviot.remove(kohde);
                    }                    
                }
            }
            
            for(Taistelija hirvio : hirviot){
                hirvio.toimi();
            }
        }
        if(pelaaja.getHp() <= 0){
            System.out.println("Hävisit!");
        }
        else{
            System.out.println("Voitit!");
        }
    }
    
    
}
