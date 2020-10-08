package io.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/config")
public class ConfigResource {

    private final static Logger LOGGER = Logger.getLogger(ConfigResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response config() throws JsonProcessingException {

        String response = new ObjectMapper().writeValueAsString(ConfigProvider.getConfig());
        LOGGER.infof("Response size: %s",response.length());
        return Response.ok().entity(response).build();
    }
}
