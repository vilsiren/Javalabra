
package hirvioluola.gui;

import hirvioluola.domain.Este;
import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Vihollinen;
import hirvioluola.loitsut.Loitsu;
import hirvioluola.loitsut.Salama;
import hirvioluola.loitsut.Taikanuoli;
import hirvioluola.loitsut.Tulikeha;
import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {
    
    private Taistelu taistelu;
    private Pelaaja pelaaja;
    private int rl,rk;
    private int hyokkaajaX, hyokkaajaY, kohdeX, kohdeY, loitsijaX, loitsijaY;
    private Loitsu loitsu;
    private boolean piirraHyokkays;
    private boolean piirraLoitsu;
    private ImageIcon pelaajankuva;
    private ImageIcon vihollisenkuva;
    private ImageIcon esteenkuva;
    private ImageIcon liittolaisenkuva;
    private ImageIcon liekkikuva;
    private ImageIcon salamakuva;
    
    public Piirtoalusta(){
        super.setBackground(Color.DARK_GRAY);
        pelaajankuva = new ImageIcon("src/kuvat/pelaaja.jpg");
        vihollisenkuva = new ImageIcon("src/kuvat/vihollinen.gif");
        liittolaisenkuva = new ImageIcon("src/kuvat/liittolainen.png");
        esteenkuva = new ImageIcon("src/kuvat/tynnyri.jpg");
        liekkikuva = new ImageIcon("src/kuvat/liekki.png");
        salamakuva = new ImageIcon("src/kuvat/salama.png");
        piirraHyokkays = false;
        piirraLoitsu = false;
    }
    
    public void setTaistelu(Taistelu taistelu){
        this.taistelu = taistelu;
        this.pelaaja = taistelu.getPelaaja();
    }
    
    public Taistelu getTaistelu(){
        return taistelu;
    }
    
    public void setHyokkays(int hyokkaajaX, int hyokkaajaY, int kohdeX, int kohdeY) {
        this.hyokkaajaX = hyokkaajaX;
        this.hyokkaajaY = hyokkaajaY;
        this.kohdeX = kohdeX;
        this.kohdeY = kohdeY;
        piirraHyokkays = true;
    }

    public void setLoitsu(Loitsu loitsu, int loitsijaX, int loitsijaY) {
        this.loitsu = loitsu;
        this.loitsijaX = loitsijaX;
        this.loitsijaY = loitsijaY;
        piirraLoitsu = true;        
    }    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        rl = super.getWidth() / taistelu.getLeveys();
        rk = super.getHeight() / taistelu.getKorkeus();
        g.drawImage(pelaajankuva.getImage(),pelaaja.getX()*rl,pelaaja.getY()*rk,rl,rk,pelaajankuva.getImageObserver() );
        g.setColor(Color.RED);
        for(Vihollinen v : taistelu.getViholliset()){
            g.drawImage(vihollisenkuva.getImage(),v.getX()*rl,v.getY()*rk,rl,rk,vihollisenkuva.getImageObserver() );
        }
        g.setColor(Color.CYAN);
        for(Liittolainen l : taistelu.getLiittolaiset()){
            g.drawImage(liittolaisenkuva.getImage(),l.getX()*rl,l.getY()*rk,rl,rk,liittolaisenkuva.getImageObserver() );
        }
        
        g.setColor(Color.LIGHT_GRAY);
        for(Este e : taistelu.getEsteet()){
            g.drawImage(esteenkuva.getImage(),e.getX()*rl,e.getY()*rk,rl,rk,esteenkuva.getImageObserver() );
        }
        if(piirraHyokkays){
            piirraHyokkays(g);
        }
        if(piirraLoitsu){
            piirraLoitsu(g);
        }
    }
    
    private void piirraHyokkays(Graphics g){
            g.setColor(Color.RED);
            int hkpx = hyokkaajaX*rl + rl / 2;
            int hkpy = hyokkaajaY*rk + rk / 2;
            int kkpx = kohdeX*rl + rl/2;
            int kkpy = kohdeY*rk + rk/2;
            g.drawLine(hkpx, hkpy, kkpx, kkpy);
            int a = rl / 4;
            int b = rk / 4;
            if(hyokkaajaX == kohdeX && kohdeY > hyokkaajaY ){                
                g.drawLine(hkpx - a, hkpy + b, hkpx + a, hkpy + b);
            }
            if(hyokkaajaX == kohdeX && kohdeY < hyokkaajaY){
                g.drawLine(hkpx - a, hkpy - b, hkpx + a, hkpy - b);
            }
            if(hyokkaajaY == kohdeY && kohdeX > hyokkaajaX){
                g.drawLine(hkpx + a, hkpy + b, hkpx + a, hkpy - b);
            }
            if(hyokkaajaY == kohdeY && kohdeX < hyokkaajaX){
                g.drawLine(hkpx - a, hkpy - b, hkpx - a, hkpy + b);
            }
            g.drawLine(hkpx, hkpy + (kkpy - hkpy) / 2, hkpx + (kkpx - hkpx) / 2, hkpy);
            piirraHyokkays = false;                
    }
    
    private void piirraLoitsu(Graphics g){
        if(loitsu instanceof Tulikeha){
            piirraTulikeha(g);
        }
        if(loitsu instanceof Salama){
            piirraSalama(g);
        }
        if(loitsu instanceof Taikanuoli){
            piirraTaikanuoli(g);
        }
        piirraLoitsu = false;
    }
    
    private void piirraTulikeha(Graphics g){
            Tulikeha tk = (Tulikeha) loitsu;
            g.setColor(Color.YELLOW);
            for(int y = loitsijaY - tk.getSade(); y <= loitsijaY + tk.getSade(); y++){
                for(int x = loitsijaX - tk.getSade(); x <= loitsijaX + tk.getSade(); x++){
                    if(!(x == loitsijaX && y == loitsijaY)){
                        g.drawImage(liekkikuva.getImage(), x*rl, y*rk, rl, rk, liekkikuva.getImageObserver() );                        
                    }
                }
            }        
    }
    
    private void piirraSalama(Graphics g){
        Salama s = (Salama) loitsu;
        g.setColor(Color.BLUE);
        for(int y = 0; y <= s.getY(); y++){
            g.drawImage(salamakuva.getImage(), s.getX()*rl, y*rk, rl, rk, salamakuva.getImageObserver());
        }        
    }
    
    private void piirraTaikanuoli(Graphics g){
        Taikanuoli nuoli = (Taikanuoli) loitsu;
        g.setColor(Color.PINK);
        int lkpx = loitsijaX*rl + rl / 2;
        int lkpy = loitsijaY*rk + rk / 2;
        int vkpx = nuoli.getVikaX()*rl + rl/2;
        int vkpy = nuoli.getVikaY()*rk + rk/2;
        g.drawLine(lkpx,lkpy,vkpx,vkpy);
        
    }
}
