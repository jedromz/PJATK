package myInvoices;

public class Company {
    private String name;
    private String nip;
    private String address;
    private String country;
    private String bankAcc;

    public Company(){}

    public Company(String name, String nip, String address, String country, String bankAcc) {
        this.name = name;
        this.nip = nip;
        this.address = address;
        this.country = country;
        this.bankAcc = bankAcc;
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

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }
}
