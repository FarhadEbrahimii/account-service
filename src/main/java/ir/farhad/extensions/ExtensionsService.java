package ir.farhad.extensions;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path(value = "/extensions")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
public interface ExtensionsService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

    @GET
    CompletionStage<Set<Extension>> getByIdAsync(@QueryParam("id") String id);

    @GET
    Uni<Set<Extension>> getByIdAsUni(@QueryParam("id") String id);

  /*  @GET
    @Path("/stream/{stream}")
    Set<Extension> getByStream(@PathParam("stream") String stream, @QueryParam("id") String id);*/
}
