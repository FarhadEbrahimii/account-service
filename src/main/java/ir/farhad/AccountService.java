package ir.farhad;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.math.BigDecimal;

@Path(value = "/accounts")

public interface AccountService {

    @GET
    @Path("/{acctNumber}/balance")
    BigDecimal getBalance(@PathParam("acctNumber") Long accountNumber);
    @POST
    @Path("{accountNumber}/transaction")
    void transact(@PathParam("accountNumber") Long accountNumber, BigDecimal amount);

}
