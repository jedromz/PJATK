package com.jrom.tpo.rmi;

import java.io.Serializable;

public class EchoRequest implements Serializable {

    private static final long serialVersionUID = -5017048927043695047L;

    public final String message;

    public EchoRequest(String message) {
        this.message = message;
    }
}
