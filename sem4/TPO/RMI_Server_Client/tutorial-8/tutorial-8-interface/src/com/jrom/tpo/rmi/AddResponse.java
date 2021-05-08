package com.jrom.tpo.rmi;

import java.io.Serializable;
import java.math.BigInteger;

public class AddResponse implements Serializable {
    private static final long serialVersionUID = -5535784384795467964L;

    public AddResponse(BigInteger addend1, BigInteger addend2) {
        this.sum = add(addend1,addend2);
    }

    private static BigInteger add(BigInteger addend1, BigInteger addend2){
        if(addend1 != null && addend2 != null){
            return addend1.add(addend2);
        }
        if(addend1 != null){
            return addend1;
        }
        return addend2;
    }

    public final BigInteger sum;
}
