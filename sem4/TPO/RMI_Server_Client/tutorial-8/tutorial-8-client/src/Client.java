import com.jrom.tpo.rmi.*;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            IEcho echo = (IEcho) Naming.lookup(Utils.ECHO_REMOTE_URI);
            EchoRequest echoRequest = new EchoRequest("hello world!");
            EchoResponse echoResponse = echo.echo(echoRequest);
            System.out.printf("request: %s,response %s\n",echoRequest.message, echoResponse.message );

            IAddition addition = (IAddition) Naming.lookup(Utils.ADDITION_REMOTE_URI);
            BigInteger addend1 = new BigInteger("123456789");
            BigInteger addend2 = new BigInteger("987654321");
            AddRequest addRequest = new AddRequest(addend1,addend2);
            AddResponse addResponse = addition.add(addRequest);
            System.out.printf("addend1: %d, addend2: %d, sum: %d\n",addRequest.addend1,addRequest.addend2,addResponse.sum);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
