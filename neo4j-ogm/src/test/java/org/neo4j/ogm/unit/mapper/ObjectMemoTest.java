/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j-OGM.
 *
 * Neo4j-OGM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.neo4j.ogm.unit.mapper;

import org.junit.Test;
import org.neo4j.ogm.domain.education.School;
import org.neo4j.ogm.domain.education.Teacher;
import org.neo4j.ogm.mapper.EntityMemo;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.metadata.info.ClassInfo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObjectMemoTest {

    private static final MetaData metaData = new MetaData("org.neo4j.ogm.domain.education");
    private static final EntityMemo objectMemo = new EntityMemo();

    @Test
    public void testUnchangedObjectDetected() {

        ClassInfo classInfo = metaData.classInfo(Teacher.class.getName());
        Teacher mrsJones = new Teacher();

        objectMemo.remember(mrsJones, classInfo);

        mrsJones.setId(115L); // the id field must not be part of the memoised property list

        assertTrue(objectMemo.remembered(mrsJones, classInfo));

    }

    @Test
    public void testChangedPropertyDetected() {

        ClassInfo classInfo = metaData.classInfo(Teacher.class.getName());
        Teacher teacher = new Teacher("Miss White");

        objectMemo.remember(teacher, classInfo);

        teacher.setId(115L); // the id field must not be part of the memoised property list
        teacher.setName("Mrs Jones"); // the teacher's name property has changed.

        assertFalse(objectMemo.remembered(teacher, classInfo));
    }

    @Test
    public void testRelatedObjectChangeDoesNotAffectNodeMemoisation() {

        ClassInfo classInfo = metaData.classInfo(Teacher.class.getName());
        Teacher teacher = new Teacher("Miss White");

        objectMemo.remember(teacher, classInfo);

        teacher.setId(115L); // the id field must not be part of the memoised property list
        teacher.setSchool(new School("Roedean")); // a related object does not affect the property list.

        assertTrue(objectMemo.remembered(teacher, classInfo));
    }



}
