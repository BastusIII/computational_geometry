package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 26.05.2015.
 */
public abstract class SimpleErrorList implements ErrorList {

    private StringBuilder errors = new StringBuilder();

    @Override
    public void addError(String error) {
        this.errors.append(error).append(System.lineSeparator());
    }
    @Override
    public String getErrors() {
        return errors.toString();
    }
}
