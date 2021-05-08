package com.jrom.tpo.rmi;

import java.io.Serializable;
import java.math.BigInteger;

public class AddRequest implements Serializable {
    private static final long serialVersionUID = 4560367380491708319L;

    public final BigInteger addend1;
    public final BigInteger addend2;

    public AddRequest(BigInteger addend1, BigInteger addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
    }
}
