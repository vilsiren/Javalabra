package hirvioluola.peli;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.gui.Piirtoalusta;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.LoitsuJolleValitaanKohde;
import hirvioluola.loitsut.Parannus;
import hirvioluola.loitsut.Salama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Taistelu extends Timer implements ActionListener {
    
    private Pelaaja pelaaja;
    private List<Taistelija> hirviot;
    private int leveys;
    private int korkeus;
    private Scanner lukija;
    private final String suunnat = "wdsa";
    private Piirtoalusta alusta;
    private JLabel status;
    private char komento;

    public Taistelu(Pelaaja pelaaja, int leveys, int korkeus) {
        super(1000, null);
        this.pelaaja = pelaaja;        
        pelaaja.setTaistelu(this);
        this.hirviot = new ArrayList<>();
        this.leveys = leveys;
        this.korkeus = korkeus;
        lukija = new Scanner(System.in);
        komento = ' ';
        addActionListener(this);
        setInitialDelay(2000);
    }
    
    public void setKomento(char komento){
        this.komento = komento;
    }
    
    public void setAlusta(Piirtoalusta alusta){
        this.alusta = alusta;
    }
    
    public void setStatus(JLabel status){
        this.status = status;
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
    
    public boolean taistelukentanSisalla(int x, int y){
        if(x < 0 || x >= leveys){
            return false;
        }
        if(y < 0 || y >= korkeus){
            return false;
        }
        return true;        
    }
    
    public void tulostaKartta(boolean hirviotNumeroitu) {
        char[][] kartta = new char[leveys][korkeus];
        
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                kartta[x][y] = '.';
            }
        }
        
        
        if(!hirviotNumeroitu){
            for (Taistelija h : hirviot) {
                kartta[h.getX()][h.getY()] = 'h';
            }
        }
        if(hirviotNumeroitu){
            for (Taistelija h : hirviot) {
                int monesHirvio = hirviot.indexOf(h);
                char c = (char) ('0' + monesHirvio);
                kartta[h.getX()][h.getY()] = c;
            }
        }
        
        kartta[pelaaja.getX()][pelaaja.getY()] = '@';
        
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                System.out.print(kartta[x][y]);
            }
            System.out.println();
        }        
    }
    
    private void teeLoitsu(int loitsunNumero){
        if(loitsunNumero >= pelaaja.getLoitsut().size()){
            return;
        }
        Loitsu loitsu = pelaaja.getLoitsut().get(loitsunNumero);
        if(loitsu.kuluttaaMPta() > pelaaja.getMp()){
            return;
        }
        if(loitsu instanceof LoitsuJolleValitaanKohde){
            tulostaKartta(true);
            try{
                int monesHirvio = Integer.parseInt(lukija.nextLine());
                Taistelija kohde = hirviot.get(monesHirvio);
                LoitsuJolleValitaanKohde valintaloitsu = (LoitsuJolleValitaanKohde) loitsu;
                valintaloitsu.setKohde(kohde);
                valintaloitsu.suorita();               
            }
            catch(Exception e){}
        }
        else{
            loitsu.suorita();
        }
        pelaaja.setMp(pelaaja.getMp() - loitsu.kuluttaaMPta());
    } 
    
    private void liikutaPelaajaa(char suunta){
            
            int dx = 0;
            int dy = 0;
            if(suunta == 'w'){
                dx = 0;
                dy = -1;
            }
            if(suunta == 'd'){
                dx = 1;
                dy = 0;
            }
            if(suunta == 's'){
                dx = 0;
                dy = 1;
            }
            if(suunta == 'a'){
                dx = -1;
                dy = 0;
            }            
            
            if(taistelukentanSisalla(pelaaja.getX() + dx, pelaaja.getY() + dy)){
                boolean liikkuu = pelaaja.liiku(dx, dy);
                if(!liikkuu){
                    Taistelija kohde = null;
                    for(Taistelija hirvio : hirviot){
                        if(hirvio.getX() == pelaaja.getX() + dx && hirvio.getY() == pelaaja.getY() + dy){
                            kohde = hirvio;
                        }
                    }
                    pelaaja.hyokkaa(kohde);             
                }
            }        
        
    }
    
    public void suorita(){
        while(pelaaja.getHp() > 0 && !hirviot.isEmpty()){
            System.out.println("HP: " + pelaaja.getHp() + " MP: " + pelaaja.getMp());
            tulostaKartta(false);
            
            String syote = lukija.nextLine();
            if(!syote.equals("") && suunnat.contains(syote)){
                liikutaPelaajaa(syote.charAt(0));
            }
            
            else{
                try{
                    int loitsunnumero = Integer.parseInt(syote);
                    teeLoitsu(loitsunnumero);
                }
                catch(Exception e){                
                }
            }
            Iterator<Taistelija> iter = hirviot.iterator();
            while(iter.hasNext()){
                Taistelija h = iter.next();
                if(h.getHp() <= 0){
                    iter.remove();
                }
                else{
                    h.toimi();
                }
            }           
        }
        if(pelaaja.getHp() <= 0){
            System.out.println("Hävisit!");
        }
        else{
            System.out.println("Voitit!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        status.setText("HP: " + pelaaja.getHp() + " MP: " + pelaaja.getMp());
        if(pelaaja.getHp() <= 0 || hirviot.isEmpty()){
            return;
        }
        if(komento != ' '){
            liikutaPelaajaa(komento);
        }
        Iterator<Taistelija> iter = hirviot.iterator();
        while(iter.hasNext()){
            Taistelija h = iter.next();
            if(h.getHp() <= 0){
                iter.remove();
            }
            else{
                h.toimi();
            }
        } 
        alusta.repaint();
        komento = ' ';
    }
    
    
}
