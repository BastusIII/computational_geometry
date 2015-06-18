package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

/**
 * A list errors can be appended to.
 */
public interface ErrorList {

    /**
     * Append an error to the list of errors
     *
     * @param error the error.
     */
    void addError(String error);

    /**
     * Get all errors as a string.
     *
     * @return the errors, each in a new line.
     */
    String getErrors();
}
