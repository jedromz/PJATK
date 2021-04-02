package protocol;

import java.math.BigInteger;



public class AddRequest implements IRequest {

    private final BigInteger addend1;
    private final BigInteger addend2;

    public AddRequest(BigInteger addend1, BigInteger addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
    }

    private BigInteger getAddend1() {
        return addend1;
    }

    private BigInteger getAddend2() {
        return addend2;
    }

    @Override
    public AddResponse response() {
        BigInteger result = addend1.add(addend2);
        return new AddResponse(result);
    }

    @Override
    public String toString() {
        return "ADD" + " " + getAddend1() + " " + getAddend2();
    }
}
