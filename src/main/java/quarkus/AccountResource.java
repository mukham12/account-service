package quarkus;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Path("/accounts")
public class AccountResource {
    Set<Account> accounts = new HashSet<>();

    @PostConstruct
    public void setup() {
        accounts.add(new Account(1L, 9L, "George Baird", new BigDecimal("100.00")));
        accounts.add(new Account(2L, 8L, "Mary Jane", new BigDecimal("500.00")));
        accounts.add(new Account(3L, 7L, "Diane Thurgood", new BigDecimal("250.00")));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts() {
        return accounts;
    }

    @Path("/account")
    public void newPath() {
        System.out.println();
    }
}
