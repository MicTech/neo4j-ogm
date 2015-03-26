package org.neo4j.ogm.metadata.info;

import org.neo4j.ogm.metadata.AnnotationsException;

import java.util.*;

public class AnnotationValidator {

    private static final Collection<String[]> forbiddenCombinations = new ArrayList<String[]>() {{
        add(new String[]{"Transient", "NodeEntity"});
    }};

    private ClassInfo classInfo;

    public AnnotationValidator(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public void validate() throws AnnotationsException {
        List<String> annotations = aggregateAnnotations(classInfo.annotationsInfo(),
                                                        classInfo.fieldsInfo(),
                                                        classInfo.methodsInfo());

        boolean result = validateAnnotations(annotations);

        if (!result) {
            String message = String.format("Annotations of class %s are not valid", classInfo.name());
            throw new AnnotationsException(message);
        }
    }

    private List<String> aggregateAnnotations(AnnotationsInfo classAnnotations,
                                              FieldsInfo fieldsInfo,
                                              MethodsInfo methodsInfo) {
        List<String> annotationsList = new ArrayList<>();

        addAnnotations(annotationsList, classAnnotations.list());

        List<AnnotationInfo> fields = getFieldsAnnotations(fieldsInfo.fields());
        addAnnotations(annotationsList, fields);

        List<AnnotationInfo> methods = getMethodsAnnotations(methodsInfo.methods());
        addAnnotations(annotationsList, methods);

        return annotationsList;
    }

    private List<AnnotationInfo> getFieldsAnnotations(Collection<FieldInfo> fieldInfos) {
        List<AnnotationInfo> collection = new LinkedList<>();

        for (FieldInfo fieldInfo : fieldInfos) {
            collection.addAll(fieldInfo.getAnnotations().list());
        }

        return collection;
    }

    private List<AnnotationInfo> getMethodsAnnotations(Collection<MethodInfo> methodInfos) {
        List<AnnotationInfo> collection = new LinkedList<>();

        for (MethodInfo fieldInfo : methodInfos) {
            collection.addAll(fieldInfo.getAnnotations().list());
        }

        return collection;
    }

    private void addAnnotations(List<String> annotationsList, Collection<AnnotationInfo> all) {
        if (all.size() > 0) {
            for (AnnotationInfo info : all) {
                annotationsList.add(info.getName());
            }
        }
    }

    private boolean validateAnnotations(List<String> allAnnotations) {
        for (String[] combination : forbiddenCombinations) {
            boolean result = Arrays.asList(allAnnotations).contains(Arrays.asList(combination));

            if (!result) {
                return result;
            }
        }

        return true;
    }
}
