package protocol;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProtocol implements IProtocol {

    private static final String ADD_REQUEST_PATTERN = "^ADD ([0-9]+) ([0-9]+)$";
    private static final String ADD_RESPONSE_PATTERN = "^ADD_RESPONSE ([0-9]+)$";
    private static final Pattern ADD_REQUEST_REGEX;
    private static final Pattern ADD_RESPONSE_REGEX;
    static{
        ADD_REQUEST_REGEX = Pattern.compile(ADD_REQUEST_PATTERN);
        ADD_RESPONSE_REGEX = Pattern.compile(ADD_RESPONSE_PATTERN);
    }


    @Override
    public String processRequest(String serializedRequest) throws RuntimeException {
        Matcher matcher = ADD_REQUEST_REGEX.matcher(serializedRequest);
        if(matcher.matches()){

            BigInteger addend1 = new BigInteger(matcher.group(1));
            BigInteger addend2 = new BigInteger(matcher.group(2));
            AddRequest request = new AddRequest(addend1,addend2);

            AddResponse response = request.response();
            return response.toString();
        }else {
            throw new RuntimeException("Invalid request");
        }
    }

    @Override
    public IResponse deserializeResponse(String serializedResponse) throws RuntimeException {
        Matcher matcher = ADD_RESPONSE_REGEX.matcher(serializedResponse);
        if(matcher.matches()){
            final int resultGroup = 1;
            BigInteger result = new BigInteger(matcher.group(resultGroup));
            return new AddResponse(result);
        }else {
            throw  new RuntimeException("Invalid response: " + serializedResponse);
        }
    }

}
