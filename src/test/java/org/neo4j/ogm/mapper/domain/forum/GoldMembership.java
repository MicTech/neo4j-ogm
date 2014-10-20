package org.neo4j.ogm.mapper.domain.forum;

import org.neo4j.ogm.annotation.Label;

@Label(name="Gold")
public class GoldMembership extends Membership {

    @Override
    public boolean getCanPost() {
        return true;
    }

    @Override
    public boolean getCanComment() {
        return true;
    }

    @Override
    public boolean getCanFollow() {
        return true;
    }

    @Override
    public IMembership[] getUpgrades() {
        return new IMembership[] {};
    }
}
