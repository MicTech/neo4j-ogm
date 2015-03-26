package org.neo4j.ogm.unit.metadata;

import org.junit.Test;
import org.neo4j.ogm.metadata.AnnotationsException;
import org.neo4j.ogm.metadata.MappingException;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.metadata.info.AnnotationValidator;
import org.neo4j.ogm.metadata.info.ClassInfo;

import static org.junit.Assert.assertTrue;

public class AnnotationValidatorTest {

    @Test(expected=AnnotationsException.class)
    public void testClassInfo() {
        new MetaData("org.neo4j.ogm.domain.incorrect.persistence");
   }
}
