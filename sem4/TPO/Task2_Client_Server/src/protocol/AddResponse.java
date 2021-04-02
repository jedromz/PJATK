package protocol;

import java.math.BigInteger;



public class AddResponse implements IResponse {
    private final BigInteger result;

    AddResponse(BigInteger result) {
        this.result = result;
    }

    private BigInteger getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ADD_RESPONSE" + " " + getResult();
    }
}
