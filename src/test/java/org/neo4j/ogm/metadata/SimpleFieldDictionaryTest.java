package org.neo4j.ogm.metadata;

import org.junit.Test;
import org.neo4j.ogm.mapper.domain.bike.Bike;
import org.neo4j.ogm.mapper.domain.canonical.Mappable;
import org.neo4j.ogm.metadata.info.DomainInfo;
import org.neo4j.ogm.strategy.simple.SimpleFieldDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleFieldDictionaryTest {

    private final DomainInfo domainInfo = new DomainInfo("org.neo4j.ogm.mapper.domain.canonical");
    private final SimpleFieldDictionary fieldDictionary = new SimpleFieldDictionary(domainInfo);

    @Test
    public void testPrimitive() throws Exception {
        Field f = fieldDictionary.findField("primitiveInt", 0, new Mappable());
        assertEquals("primitiveInt", f.getName());
    }


    @Test
    public void testPrimitiveArray() throws Exception {
        Field f = fieldDictionary.findField("primitiveLongArray", new Long[]{1L, 2L}, new Mappable());
        assertEquals("primitiveLongArray", f.getName());
    }


    @Test
    public void testObject() throws Exception {
        Field f = fieldDictionary.findField("objectString", "Hello World", new Mappable());
        assertEquals("objectString", f.getName());
    }

    @Test
    public void testObjectCollection() throws Exception {
        List<String> stringList = new ArrayList<>();

        stringList.add("Hello");
        stringList.add("World");

        Field f = fieldDictionary.findField("listOfAnything", stringList, new Mappable());
        assertEquals("listOfAnything", f.getName());
    }

    @Test
    public void testObjectArray() throws Exception {
        List<String> stringList = new ArrayList<>();

        stringList.add("Hello");
        stringList.add("World");

        Field f = fieldDictionary.findField("objectStringArray", stringList, new Mappable());
        assertEquals("objectStringArray", f.getName());
    }

    @Test
    public void shouldResolveTypeAttribuesAsGraphPropertiesOrRelationshipTypes() {
        String resolvedFrame = fieldDictionary.resolveTypeAttribute("frame", Bike.class);
        assertEquals("The relationship type wasn't resolved as expected", "HAS_FRAME", resolvedFrame);

        String resolvedPrimitive = fieldDictionary.resolveTypeAttribute("id", Bike.class);
        assertEquals("The property name wasn't resolved as expected", "id", resolvedPrimitive);
    }

}
