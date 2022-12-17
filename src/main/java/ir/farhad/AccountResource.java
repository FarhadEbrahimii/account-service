package ir.farhad;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.*;

@Path(value = "/accounts")
public class AccountResource {
    Set<Account> accounts = new HashSet<>();

    @Inject
    private EntityManager entityManager;

    @PostConstruct
    public void setup() {
        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(121212121L, 888777666L, "Mary Taylor",  new BigDecimal("560.03")));
        accounts.add(new Account(545454545L, 222444999L, "Diana Rigg",   new BigDecimal("422.00")));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts() {
        return accounts;
//        return Collections.emptySet();
    }

    // Active Record Approach
/*    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> allAccounts() {
        return entityManager.createNamedQuery("Accounts.findAll",Account.class)
                .getResultList();
//        return accounts;
//        return Collections.emptySet();
    }*/

    @Inject
    private AccountRepository accountRepository;

    // Active Record Approach
/*    @GET
    public List<Account> allAccounts() {
        return accountRepository.listAll();
    }*/

 /*   @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountNumber") Long  accountNumber) {
        Optional<Account> response = accounts.stream()
                .filter(acct -> acct.getAccountNumber().equals(accountNumber)).findFirst();
//        return response.orElseThrow(()-> new NotFoundException("Account with id of " + accountNumber + " does not exist."));
        return response.orElseThrow(()  -> new WebApplicationException("Account with id of "+  accountNumber + " does not exist.", 404));
    }*/

 /*   @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        if (Objects.isNull(account.getAccountNumber())) {
            throw new WebApplicationException("No Account number specified.", 400);
        }
        accounts.add(account);
        return Response.status(201).entity(account).build();
    }*/

    @GET
    @Path("/{acctNumber}")
    public Account getAccount(@PathParam("acctNumber") Long  accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account;
    }
    @POST
    @Transactional
    public Response createAccount(Account account) {
        accountRepository.persist(account);
        return Response.status(201).entity(account).build();
    }
    @PUT
    @Path("{accountNumber}/withdrawal")
    @Transactional
    public Account withdrawal(@PathParam("accountNumber") Long  accountNumber, String amount) {
        Account entity = accountRepository.findByAccountNumber(accountNumber);
        entity.withdrawFunds(new BigDecimal(amount));
        return entity;
    }
}
