package fileio;

public final class CredentialsInputData {
    private String name;
    private String password;
    private String accountType;
    private  String country;
    private String balance;

    public CredentialsInputData(final String name, final String password, final String accountType,
                                final String country, final String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public CredentialsInputData(final CredentialsInputData Credentials) {
        this.name = Credentials.getName();
        this.password = Credentials.getPassword();
        this.accountType = Credentials.getAccountType();
        this.country = Credentials.getCountry();
        this.balance = Credentials.getBalance();

    }
}
