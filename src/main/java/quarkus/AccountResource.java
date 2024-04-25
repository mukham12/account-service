package quarkus;

import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

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

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountNumber") Long accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Account with ID of " + accountNumber + " not found"));
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            int code = 500;

            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            JsonObjectBuilder entity = Json.createObjectBuilder()
                    .add("exception", exception.getClass().getName())
                    .add("code", code);

            return Response.status(code).entity(entity.build()).build();
        }
    }
}
