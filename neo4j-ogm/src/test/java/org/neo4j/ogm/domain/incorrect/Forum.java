/*
 * Copyright (c) 2014-2015 "GraphAware"
 *
 * GraphAware Ltd
 *
 * This file is part of Neo4j-OGM.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.neo4j.ogm.domain.incorrect;

import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Represents a forum that contains a number of topics.
 */
public class Forum {

    private Long id;

    @Relationship(type="HAS_TOPIC")
    private List<ForumTopicLink> topicsInForum;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ForumTopicLink> getTopicsInForum() {
        return topicsInForum;
    }

    public void setTopicsInForum(List<ForumTopicLink> topicsInForum) {
        this.topicsInForum = topicsInForum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
