
package hirvioluola.gui;

import hirvioluola.domain.Este;
import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.TeleportHirvio;
import hirvioluola.domain.Vihollinen;
import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {
    
    private Taistelu taistelu;
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    private List<Este> esteet;
    private List<Liittolainen> liittolaiset;
    
    public Piirtoalusta() {
        super.setBackground(Color.BLACK);
        Taistelu tappelu = new Taistelu(10,10);
        Pelaaja p = new Pelaaja(2,10,10);
        tappelu.setPelaaja(p,0,0);
        this.taistelu = tappelu;
        this.pelaaja = taistelu.getPelaaja();
        this.viholliset = taistelu.getViholliset();
        this.esteet = taistelu.getEsteet();
        this.liittolaiset = taistelu.getLiittolaiset();
        taistelu.lisaaOlio(new Vihollinen(1,3,0),3,3);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),3,2);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),2,4);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),1,4);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),6,8);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),7,8);
        taistelu.lisaaOlio(new Vihollinen(1,3,0),8,8);
        taistelu.lisaaOlio(new TeleportHirvio(4,10,10),9,3);
        taistelu.lisaaOlio(new Este(5,'e'),0,1);
        taistelu.lisaaOlio(new Este(5,'e'),1,0);
        taistelu.lisaaOlio(new Este(5,'e'),1,1);        
    }
    
    public void setTaistelu(Taistelu taistelu){
        this.taistelu = taistelu;
        this.pelaaja = taistelu.getPelaaja();
        this.viholliset = taistelu.getViholliset();
        this.esteet = taistelu.getEsteet();
        this.liittolaiset = taistelu.getLiittolaiset();
    }
    
    public Taistelu getTaistelu(){
        return taistelu;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int rsp = super.getWidth() / taistelu.getLeveys();
        g.setColor(Color.WHITE);        
        g.fillOval(pelaaja.getX()*rsp,pelaaja.getY()*rsp,rsp,rsp);
        g.setColor(Color.RED);
        for(Vihollinen v : viholliset){
            g.fillOval(v.getX()*rsp,v.getY()*rsp,rsp,rsp);
        }
        g.setColor(Color.BLUE);
        for(Liittolainen l : liittolaiset){
            g.fillOval(l.getX()*rsp, l.getY()*rsp,rsp,rsp);
        }
        
        g.setColor(Color.LIGHT_GRAY);
        for(Este e : esteet){
            g.fillOval(e.getX()*rsp,e.getY()*rsp,rsp,rsp);
        }
    }    
}
