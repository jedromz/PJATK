package protocol;

public interface IProtocol  {

    String processRequest(String serializedRequest) throws Exception;
    IResponse deserializeResponse(String serializedResponse) throws  Exception;
}
