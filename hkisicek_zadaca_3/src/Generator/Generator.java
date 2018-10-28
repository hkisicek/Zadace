/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import java.util.Random;

/**
 *
 * @author helena
 */
public class Generator {

    private static Random random = new Random();
    private static int sjeme;
    private static Generator instance;

    private Generator() {
    }

    public static Generator getInstance() {
        if (instance == null) {
            instance = new Generator();
        }
        return instance;
    }

    //generiraj integer
    public int dajSlucajniBroj(int odBroja, int doBroja) {
        int broj = odBroja + random.nextInt(doBroja - odBroja + 1);

        return broj;
    }

    //generiraj float
    public float dajSlucajniBroj(float odBroja, float doBroja) {
        float broj = random.nextFloat() * (doBroja - odBroja) + odBroja;

        return broj;
    }

    public void setSjeme(int sjeme) {
        Generator.sjeme = sjeme;
    }
}
