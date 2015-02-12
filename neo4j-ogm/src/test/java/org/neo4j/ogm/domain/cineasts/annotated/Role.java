package org.neo4j.ogm.domain.cineasts.annotated;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity
public class Role {
    Long id;
    @EndNode Movie movie;
    @StartNode Actor actor;
    String role;

    public Role(Movie movie, Actor actor, String role) {
        this.movie = movie;
        this.actor = actor;
        this.role = role;
    }


}
