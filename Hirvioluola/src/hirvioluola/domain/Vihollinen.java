package hirvioluola.domain;

import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Viholliset ovat tekoalytaistelijoita jotka yrittävät ensisijaisesti tuhota pelaajan
 * ja toissijaisesti pelaajan liittolaiset. 
 */

public class Vihollinen extends Tekoalytaistelija{
    
    public Vihollinen(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
    }
    
    /**
     * Lisätään taistelusta saataviin kokemuspisteisiin.
     * @return 
     */
    public int kokemuspisteita(){
        return (voima + hpMax) / 4;
    }
    
    @Override
    public void setTaistelu(Taistelu taistelu){
        super.setTaistelu(taistelu);
        if(taistelu != null && taistelu.getPelaaja() != null){
            super.kohde = taistelu.getPelaaja();
        }
    }
    
    /**
     * Vihollinen hyökkää pelaajan kimppuun jos pelaaja on hyökkäysalueella. Jos
     * pelaaja ei ole hyökkäysalueella ja alueella on pelaajan liittolaisia,
     * vihollinen hyökkää liittolaisen kimppuun. Jos hyökkäysalueella ei ole pelaajaa
     * eikä pelaajan liittolaisia, vihollinen yrittää liikkua kohti pelaajaa.
     * Jos tämäkään ei onnistu, vihollinen hyökkää hyökkäysalueella mahdollisesti
     * olevan esteen kimppuun.
     *
     */
    @Override
    public void toimi() {
        if(hyokkaysalueella(kohde)) { 
            hyokkaa(kohde);
            return;
        }
        for(Liittolainen l : taistelu.getLiittolaiset()){
            if( hyokkaysalueella(l) ){
                hyokkaa(l);
                return;
            }
        }
        
        boolean liikkuu = super.lahestyKohdetta();
        
        if(!liikkuu){
            for(Este e : taistelu.getEsteet()){
                if(hyokkaysalueella(e)){
                    hyokkaa(e);
                    return;
                }
            }
        }
        
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x*20, y*20, 20, 20);
    }

    @Override
    public char merkki() {
        return 'v';
    }
    
}
