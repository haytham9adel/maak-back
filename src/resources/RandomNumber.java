package resources;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class RandomNumber {
    ArrayList numbersList = new ArrayList ();
    public RandomNumber(int length) {
        for(int x=1;x<=length;x++)
            numbersList.add(x);
        Collections.shuffle(numbersList);
    }
    public int generateNewRandom(int n) {
        return (Integer) numbersList.get(n);
    }
    
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
      return new BigInteger(50, random).toString(32);
    }
}