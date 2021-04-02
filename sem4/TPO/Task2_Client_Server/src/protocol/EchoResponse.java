package protocol;

public class EchoResponse implements IResponse {

    private final String content;

    EchoResponse(String content) {

        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
