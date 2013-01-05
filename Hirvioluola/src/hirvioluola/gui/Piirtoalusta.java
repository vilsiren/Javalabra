/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hirvioluola.gui;

import hirvioluola.domain.Ruutuolio;
import hirvioluola.domain.Vihollinen;
import hirvioluola.peli.Taistelu;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel{
    
    private Taistelu taistelu;

    public Piirtoalusta(Taistelu taistelu) {
        super.setBackground(Color.DARK_GRAY);
        this.taistelu = taistelu;
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        taistelu.getPelaaja().piirra(graphics);
        for(Vihollinen v : taistelu.getViholliset()){
            v.piirra(graphics);
        }
    }        
    
}
