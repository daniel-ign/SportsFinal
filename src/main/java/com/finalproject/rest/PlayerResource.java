package com.finalproject.rest;

import com.finalproject.entity.Player;
import com.finalproject.service.PlayerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/players")
public class PlayerResource {
    @EJB
    private PlayerService playerService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service PLAYER is working").build();
    }

    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers() {
        return Response.ok().entity(playerService.getPlayerList()).build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        playerService.addToList(player);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deletePlayer(@PathParam("id") int id) {
        playerService.removeFromList(id);
        return Response.ok().entity("Player Deleted").build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Path("/update/{id}")
    public Response updatePlayer(Player player, @PathParam("id") int id){
        playerService.updateData(player, id);
        return  Response.ok().entity("Player Updated").build();
    }
}
