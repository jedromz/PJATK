package protocol;

public class EchoProtocol implements IProtocol {
    @Override
    public String processRequest(String serializedRequest) {
        return  serializedRequest;
    }

    @Override
    public IResponse deserializeResponse(String serializedResponse) {
        return new EchoResponse(serializedResponse);
    }
}
