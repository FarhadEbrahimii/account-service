package ir.farhad;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NamedQuery(name = "Accounts.findAll",  query = "SELECT a FROM Account a ORDER BY a.accountNumber")
@NamedQuery(name = "Accounts.findByAccountNumber", query = "SELECT a FROM Account a WHERE a.accountNumber =:accountNumber ORDER BY a.accountNumber")
public class Account extends PanacheEntity {

/*    @Id
    @SequenceGenerator(name = "accountsSequence", sequenceName =
            "accounts_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountsSequence")
    private Long id;*/
    private Long accountNumber;
    private Long customerNumber;
    private String customerName;
    private BigDecimal balance;
    private AccountStatus accountStatus = AccountStatus.OPEN;


    public static long totalAccountsForCustomer(Long customerNumber)
    {
        return find("customerNumber", customerNumber).count();
    }

    public static Account findByAccountNumber(Long accountNumber) {
        return find("accountNumber", accountNumber).firstResult();
    }

    public Account() {
    }

    public Account(Long accountNumber, Long customerNumber, String customerName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!getAccountNumber().equals(account.getAccountNumber())) return false;
        return getCustomerNumber().equals(account.getCustomerNumber());
    }

    @Override
    public int hashCode() {
        int result = getAccountNumber().hashCode();
        result = 31 * result + getCustomerNumber().hashCode();
        return result;
    }

    public void withdrawFunds(BigDecimal bigDecimal) {

    }
}
