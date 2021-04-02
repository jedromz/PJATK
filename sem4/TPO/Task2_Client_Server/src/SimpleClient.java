import protocol.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public final class SimpleClient {
    private static final String HOST_NAME = "localhost";

    private ByteBuffer byteBuffer;
    private SocketChannel socketChannel;
    private IProtocol protocol;

    private SimpleClient(String host, IProtocol protocol, int port) throws RuntimeException {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            SocketAddress address = new InetSocketAddress(host, port);
            socketChannel.connect(address);
            this.protocol = protocol;
            byteBuffer = ByteBuffer.allocate(100);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private IResponse doConversation(IRequest iRequest) throws Exception {
        try {
            byteBuffer.clear();
            byteBuffer.put(iRequest.toString().getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            String input = new String(byteBuffer.array(), 0, byteBuffer.limit());
            return protocol.deserializeResponse(input);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static IRequest request(String mode, String... args) {
        try {
            IRequest request = null;
            if (mode.equals(TaskUtils.MODE_ADD)) {
                BigInteger first = new BigInteger(args[0]);
                BigInteger second = new BigInteger(args[1]);

                request = new AddRequest(first, second);


            } else if (mode.equals(TaskUtils.MODE_ECHO)) {
                StringBuilder sb = new StringBuilder();
                for (String argument : args) {
                    sb.append(argument).append(" ");
                }
                request = new EchoRequest(sb.toString());
            }
            return request;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


    private void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) throws Exception {

        try {
            String mode = args[0];
            IProtocol protocol = TaskUtils.getProtocol(mode);


            String[] arguments = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                if(mode.equals(TaskUtils.MODE_ADD)){
                    if(isNumeric(args[i])){
                        arguments[i - 1] = args[i];
                    }else {
                        throw new RuntimeException("Argument must be an integer");
                    }
                }else {
                    arguments[i - 1] = args[i];
                }

            }
            SimpleClient client = new SimpleClient(HOST_NAME, protocol, TaskUtils.PORT);
            IRequest request = request(mode, arguments);

            IResponse response = client.doConversation(request);
            System.out.println("response: " + response);
            client.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}

