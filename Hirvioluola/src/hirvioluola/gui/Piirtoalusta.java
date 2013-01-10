
package hirvioluola.gui;

import hirvioluola.domain.Este;
import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.TeleportHirvio;
import hirvioluola.domain.Vihollinen;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {
    
    private Taistelu taistelu;
    private Pelaaja pelaaja;
    
    public void setTaistelu(Taistelu taistelu){
        this.taistelu = taistelu;
        this.pelaaja = taistelu.getPelaaja();
    }
    
    public Taistelu getTaistelu(){
        return taistelu;
    }
    
    public void piirraHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY) {
        
    }

    public void piirraLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY) {
        
    }    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int rl = super.getWidth() / taistelu.getLeveys();
        int rk = super.getHeight() / taistelu.getKorkeus();
        g.setColor(Color.WHITE);        
        g.fillOval(pelaaja.getX()*rl,pelaaja.getY()*rk,rl,rk);
        g.setColor(Color.RED);
        for(Vihollinen v : taistelu.getViholliset()){
            g.fillOval(v.getX()*rl,v.getY()*rk,rl,rk);
        }
        g.setColor(Color.BLUE);
        for(Liittolainen l : taistelu.getLiittolaiset()){
            g.fillOval(l.getX()*rl, l.getY()*rk,rl,rk);
        }
        
        g.setColor(Color.LIGHT_GRAY);
        for(Este e : taistelu.getEsteet()){
            g.fillOval(e.getX()*rl,e.getY()*rk,rl,rk);
        }
    }    
}
