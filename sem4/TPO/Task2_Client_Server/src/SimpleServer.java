import protocol.IProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class SimpleServer {

    private ByteBuffer byteBuffer;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private IProtocol protocol;
    private SelectionKey key;
    private final int BUFFER_SIZE = 100;


    private SimpleServer(IProtocol protocol) {
        try {
            byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            serverSocketChannel = ServerSocketChannel.open();
            SocketAddress socketAddress = new InetSocketAddress("localhost",TaskUtils.PORT);
            serverSocketChannel.socket().bind(socketAddress);
            selector = Selector.open();
            key = configureNonBlocking(serverSocketChannel, SelectionKey.OP_ACCEPT);
            this.protocol = protocol;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleTraffic() throws RuntimeException {
        try {
            while (true) {
                selector.select();
                for (SelectionKey selectionKey : selector.selectedKeys()) {
                    if (selectionKey == key && selectionKey.isAcceptable()) {
                        handleAccept();
                    } else if (selectionKey != key && selectionKey.isReadable()) {
                        handleRead(selectionKey);
                    } else if (selectionKey != key && selectionKey.isWritable()) {
                        System.out.println("writable");
                    }
                }
            }
        } catch (Throwable e) {
           e.printStackTrace();
        }
    }

    private void handleRead(SelectionKey selectionKey) throws Exception {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        String request = new String(byteBuffer.array(), 0, byteBuffer.limit());
        String response = protocol.processRequest(request);
        byteBuffer.clear();
        byteBuffer.put(response.getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }

    private void handleAccept() throws IOException {
        SocketChannel clientChannel = serverSocketChannel.accept();
        if (clientChannel != null) {
            configureNonBlocking(clientChannel, SelectionKey.OP_READ);
        }
    }

    private SelectionKey configureNonBlocking(SelectableChannel channel, int operation) {
        try {
            channel.configureBlocking(false);
            return channel.register(selector, operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

            try {
                String mode = args[0];
                IProtocol protocol = TaskUtils.getProtocol(mode);
                SimpleServer simpleServer = new SimpleServer(protocol);
                simpleServer.handleTraffic();
            }catch (Throwable e){
                e.printStackTrace();
            }


    }
}
