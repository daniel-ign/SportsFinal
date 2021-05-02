package com.finalproject.rest;

import com.finalproject.entity.Player;
import com.finalproject.entity.Team;
import com.finalproject.entity.TeamUpdateDto;
import com.finalproject.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/teams")
public class TeamResource {

    @EJB
    private TeamService teamService;

    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service TEAM is working").build();
    }

    //CREATES A TEAM
    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createTeam(Team team){
        teamService.addTeamToList(team);
        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    //RETURNS ALL THE TEAMS
    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllTeams(){
        return Response.ok().entity(teamService.getAllTeams()).build();
    }

    //REMOVES A TEAM FROM THE LIST
    @DELETE
    @Path("/{id}")
    public Response deletePlayer(@PathParam("id") int id) {
        teamService.removeTeamFromList(id);
        return Response.ok().entity("Team deleted").build();
    }

    //UPDATES THE INFORMATION OF A TEAM
    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updateTeam(TeamUpdateDto teamUpdateDto){
        if(teamUpdateDto.getId() <= 0){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"Error\": \"Incorrect Id\"\n" +
                            "}").build();
        }
        Team teamToUpdate = teamService.getById(teamUpdateDto.getId());
        if (teamToUpdate == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"Error\": \"Team does not exist\"\n" +
                            "}").build();
        }
        return Response.ok().entity(teamService.updateTeamName(teamUpdateDto, teamToUpdate)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response addPlayerToTeam(@PathParam("id") int teamId, Player player){
        teamService.addPlayerToTeam(teamId, player);
        return Response.ok().entity("{\n" +
                "\t\"Player added\"\n" +
                "}").build();
    }
}
