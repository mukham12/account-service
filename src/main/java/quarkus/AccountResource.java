package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.Set;

@Path("/accounts")
public class AccountResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts() {
        return Collections.emptySet();
    }

    @Path("/account")
    public void newPath() {
        System.out.printLn("");
    }
}
