/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.CheckAlgorithms;

/**
 *
 * @author helena
 */
public class AlgorithmFactory {
    
        public Algorithm createAlgorithm(String algoritam) {
        switch (algoritam) {
            case "AlgorithmSequential":
                return new AlgorithmSequential();
            case "AlgorithmRandom":
                return new AlgorithmRandom();
            case "AlgorithmAlphabet":
                return new AlgorithmAlphabet();
            default:
                return null;
        }
    } 
}
