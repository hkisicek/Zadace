/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfRes;

import Uređaji.Senzor;
import java.util.List;

/**
 *
 * @author Helena
 */
public interface Izvrsitelj {
    
    void postaviSljedeceg(Izvrsitelj izvrsitelj);
    void popraviUređaj(Senzor senzor);
    List<String> ispisiPoruku();
}
