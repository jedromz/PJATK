package myInvoices;

public class Customer {
    private String name;
    private String nip;
    private String address;
    private String country;

    public Customer(){}

    public Customer(String name, String nip, String address, String country) {
        this.name = name;
        this.nip = nip;
        this.address = address;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
