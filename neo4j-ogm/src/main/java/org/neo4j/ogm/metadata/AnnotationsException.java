package org.neo4j.ogm.metadata;

public class AnnotationsException extends Exception {

    private static final long serialVersionUID = -9160906479192232033L;

    /**
     * Constructs a new {@link MappingException} with the given message.
     *
     * @param message A message describing the reason for this exception
     */
    public AnnotationsException(String message) {
        super(message);
    }
}
