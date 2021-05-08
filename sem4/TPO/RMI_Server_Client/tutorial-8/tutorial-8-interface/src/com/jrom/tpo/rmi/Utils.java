package com.jrom.tpo.rmi;

public class Utils {
    public static final int RMI_PORT = 1099;
    public static final String ADDITION_NAME = "addition";
    public static final String ECHO_NAME = "echo";

    public static final String ADDITION_REMOTE_URI = rmiUri(RMI_PORT,ADDITION_NAME);
    public static final String ECHO_REMOTE_URI = rmiUri(RMI_PORT,ECHO_NAME);

    private static String rmiUri(int rmiPort, String name) {
        return String.format("rmi://localhost:%d/%s",rmiPort,name);
    }
}
