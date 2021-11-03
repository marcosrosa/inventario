package br.jus.jfes.sisgepi.inventario.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.jus.jfes.sisgepi.inventario.data.BemGepatRepository;
import br.jus.jfes.sisgepi.inventario.modelo.BemGepat;

@Path("/bem")
@RequestScoped
public class InventarioResource {
	
	@Inject 
	private BemGepatRepository bemRepos;
	
	@GET
	@Path("/listasered")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BemGepat> listTodosBens() {
        return bemRepos.getBemGepatPorCodSetor(311);
    }
	
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public BemGepat getBemPorPatrimonio(@PathParam("id") long id) {
        BemGepat  b = bemRepos.getBemPorPatrimonio(id);
        if (b == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return b;
    }

	
}
