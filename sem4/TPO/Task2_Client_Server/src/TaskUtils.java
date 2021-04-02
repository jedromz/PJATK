import protocol.AddProtocol;
import protocol.EchoProtocol;
import protocol.IProtocol;

public class TaskUtils {
    public static final int BUFFER_SIZE = 200;
    public static final String MODE_ADD = "ADD";
    public static final String MODE_ECHO = "ECHO";
    public static final int PORT = 8888;

    public static IProtocol getProtocol(String mode) {
        switch (mode) {
            case "ECHO":
                return new EchoProtocol();

            case "ADD":
                return new AddProtocol();

            default:
                throw new RuntimeException("uknown protocol...");

        }
    }
}
