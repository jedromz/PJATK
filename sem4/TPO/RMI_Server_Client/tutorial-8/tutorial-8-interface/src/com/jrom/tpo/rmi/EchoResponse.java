package com.jrom.tpo.rmi;

import java.io.Serializable;

public class EchoResponse implements Serializable {
    private static final long serialVersionUID = -6462668062091403205L;

    public final String message;

    public EchoResponse(String message) {
        this.message = message;
    }
}
