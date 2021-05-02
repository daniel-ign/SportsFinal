package com.finalproject.rest;

import com.finalproject.entity.League;
import com.finalproject.entity.Team;
import com.finalproject.service.LeagueService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/leagues")
public class LeagueResource {

    @EJB
    private LeagueService leagueService;

    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service LEAGUE is working").build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createLeague(League league){
        leagueService.addLeague(league);
        return Response.status(Response.Status.CREATED).entity(league).build();
    }

    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllLeagues(){
        return Response.ok().entity(leagueService.getAllLeagues()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response addTeamToLeague(@PathParam("id") int leagueId, Team team){
        leagueService.addTeamToLeague(leagueId, team);
        return Response.ok().entity("{\n" +
                "\t\"Team added\"\n" +
                "}").build();
    }
}
