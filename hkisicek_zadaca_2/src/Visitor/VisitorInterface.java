/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import Datoteke.IzlazDatoteka;

/**
 *
 * @author helena
 */
public interface VisitorInterface {
    
    public void visit(IzlazDatoteka datoteka);
}
