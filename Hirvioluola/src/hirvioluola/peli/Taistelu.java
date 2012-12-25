package hirvioluola.peli;

import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.gui.Piirtoalusta;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.ToimintoJolleValitaanRuutu;
import hirvioluola.loitsut.ToimintoJolleValitaanSuunta;
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
    private String komento;

    public Taistelu(Pelaaja pelaaja, int leveys, int korkeus) {
        super(1000, null);
        this.pelaaja = pelaaja;        
        pelaaja.setTaistelu(this);
        this.hirviot = new ArrayList<>();
        this.leveys = leveys;
        this.korkeus = korkeus;
        lukija = new Scanner(System.in);
        komento = "";
        addActionListener(this);
        setInitialDelay(2000);
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
        hirvio.setTaistelu(this);
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
    
    public void paivitaHirviolista(){
        Iterator<Taistelija> iter = hirviot.iterator();
        while(iter.hasNext()){
            if(iter.next().getHp() <= 0){
                    iter.remove();
            }
        }
    }          
        
    public void hirviotToimii(){
        for(Taistelija hirvio : hirviot){
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
        if(pelaaja.teeLoitsu(loitsu) == false){
            return;
        }
        
        if(loitsu instanceof ToimintoJolleValitaanRuutu){
            int[] koordinaatit = kysyKoordinaatit();
            if(koordinaatit != null){
                ToimintoJolleValitaanRuutu ruutuloitsu = (ToimintoJolleValitaanRuutu) loitsu;
                if(ruutuloitsu.setRuutu(koordinaatit[0],koordinaatit[1], this) == false){
                    return;
                }
            }
        }    
        
        else if(loitsu instanceof ToimintoJolleValitaanSuunta){
            int[] suunta = suunta(lukija.nextLine());
            if(suunta == null) return;
            ToimintoJolleValitaanSuunta suuntaloitsu = (ToimintoJolleValitaanSuunta) loitsu;
            suuntaloitsu.setSuunta(suunta[0], suunta[1]);
        }
        
        loitsu.suorita(pelaaja);
    }
    
    private int[] kysyKoordinaatit(){
        try{
            int[] koordinaatit = new int[2];
            System.out.print("x: ");
            koordinaatit[0] = Integer.parseInt(lukija.nextLine());
            System.out.print("y: ");
            koordinaatit[1] = Integer.parseInt(lukija.nextLine());
            return koordinaatit;           
        }
        catch(Exception e){
            return null;
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
             
            if(taistelukentanSisalla(pelaaja.getX() + dx, pelaaja.getY() + dy)){
                boolean liikkuu = pelaaja.liiku(dx, dy);
                if(!liikkuu){
                    for(Taistelija hirvio : hirviot){
                        if(hirvio.getX() == pelaaja.getX() + dx && hirvio.getY() == pelaaja.getY() + dy){
                            pelaaja.hyokkaa(hirvio);
                        }
                    }             
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        status.setText("HP: " + pelaaja.getHp() + " MP: " + pelaaja.getMp());
        if(pelaaja.getHp() <= 0 || hirviot.isEmpty()){
            return;
        }
        if(!komento.equals("")){
            liikutaPelaajaa(suunta(komento)[0], suunta(komento)[1]);
        }
        paivitaHirviolista();
        hirviotToimii();
        alusta.repaint();
        komento = "";
    }
    
    
}
