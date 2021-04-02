package protocol;

public class EchoRequest implements IRequest {

    private String content;
    public EchoRequest(String s) {
        content = s;
    }

    @Override
    public IResponse response() {
        return new EchoResponse(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
