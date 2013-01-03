
package hirvioluola.domain;

import hirvioluola.loitsut.Teleportaatio;


public class TeleportHirvio extends Vihollinen{
    
    private Teleportaatio teleport;

    public TeleportHirvio(int voima, int hpMax, int mpMax) {
        super(voima, hpMax, mpMax);
        teleport = new Teleportaatio(4);
    }
    
    private boolean setTeleportRuutu(){
        for(int y0 = kohde.getY() - 1; y0 <= kohde.getY() + 1; y0++){
            for(int x0 = kohde.getX() - 1; x0 <= kohde.getX() + 1; x0++){
                if(teleport.setRuutu(x0, y0, taistelu) == true){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void toimi(){        
        if(hyokkaysalueella(kohde) || mp < teleport.kuluttaaMPta() || setTeleportRuutu() == false){
            super.toimi();
        }
        else{
            teleport.suorita(this);
        }        
    }
    
    @Override
    public char merkki(){
        return 't';
    }
    
}
