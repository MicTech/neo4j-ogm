package org.neo4j.ogm.domain.education;

public class Student extends DomainObject {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}