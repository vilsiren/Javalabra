
package hirvioluola.peli;

import hirvioluola.domain.Ruutuolio;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Ruutuloitsu;
import hirvioluola.loitsut.Suuntaloitsu;
import java.util.Scanner;

public class Tekstitaistelukayttis implements Taistelukayttis{
    
    private Scanner lukija;
    private Taistelu taistelu;
    
    public Tekstitaistelukayttis(){
        this.lukija = new Scanner(System.in);
    }
    
    public void setTaistelu(Taistelu taistelu){
        this.taistelu = taistelu;
        taistelu.setKayttis(this);
    }
    
    public char[][] kartta() {
        char[][] kartta = new char[taistelu.getLeveys()][taistelu.getKorkeus()];
        
        for (int y = 0; y < taistelu.getKorkeus(); y++) {
            for (int x = 0; x < taistelu.getLeveys(); x++) {
                kartta[x][y] = '.';
            }
        }        
        
        for(Ruutuolio olio : taistelu.ruutuOliot()){
            kartta[olio.getX()][olio.getY()] = olio.merkki();
        }
        
        return kartta;
    }    

    @Override
    public String odotaPelaajanKomentoa(){
        String komento = lukija.nextLine();
        if(komento.equals("")){
            return "ÄLÄ TEE MITÄÄN";
        }
        if(komento.equals("l")){
            System.out.println(taistelu.loitsulista());
        }
        String suunta = suunta(komento);
        if(suunta != null){
            return suunta;
        }
        return komento;
    }
    
    @Override
    public void paivita() {
        System.out.println(taistelu.pelaajanStatus());
        System.out.println();
        for (int y = 0; y < taistelu.getKorkeus(); y++) {
            for (int x = 0; x < taistelu.getLeveys(); x++) {
                System.out.print(kartta()[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    @Override
    public void valitseRuutu(Ruutuloitsu loitsu){
        while(true){
            try{
                int x, y;
                System.out.print("x: ");
                x = Integer.parseInt(lukija.nextLine());
                System.out.print("y: ");
                y = Integer.parseInt(lukija.nextLine());
                if(loitsu.setRuutu(x, y, taistelu) == false ){
                    throw new Exception();
                }
                break;
            }
            catch(Exception e){
            }
        }      
    }
    
    private String suunta(String komento){
        if(komento.equals("w")) return "YLÖS";
        else if(komento.equals("e")) return "YLÄOIKEA";
        else if(komento.equals("d")) return "OIKEA";
        else if(komento.equals("c")) return "ALAOIKEA";
        else if(komento.equals("x")) return "ALAS";
        else if(komento.equals("z")) return "ALAVASEN";
        else if(komento.equals("a")) return "VASEN";
        else if(komento.equals("q")) return "YLÄVASEN";
        else return null;
    }
    
    @Override
    public void valitseSuunta(Suuntaloitsu loitsu){
        while(true){
            try{
                String komento = lukija.nextLine();
                int[] suunta = taistelu.suunta(suunta(komento));
                if(suunta == null){
                    throw new Exception();               
                }
                loitsu.setSuunta(suunta[0], suunta[1]);
                break;
            }
            catch(Exception e){               
            }
        }
    }

    @Override
    public void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY) {
    }   

    @Override
    public void piirraHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY) {
    }
    
    public void run(){
        taistelu.suorita();
    }
}
