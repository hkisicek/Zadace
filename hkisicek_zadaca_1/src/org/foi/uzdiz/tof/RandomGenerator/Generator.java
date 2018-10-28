/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.RandomGenerator;

import java.util.Random;

/**
 *
 * @author helena
 */
public class Generator {
    
    private static long seed;
    private static Random random;
    
    public Generator() {
    }
    
    public static void set(long seed){
        Generator.setSeed(seed);
        random = new Random(seed);
    }

    public static long getSjeme() {
        return seed;
    }

    public static void setSeed(long seed) {
        Generator.seed = seed;
    }

    public static int getRandomNumber(int max) {
        return random.nextInt(max);
    }

}
