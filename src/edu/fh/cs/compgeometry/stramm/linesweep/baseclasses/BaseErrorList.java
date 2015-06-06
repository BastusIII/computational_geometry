package edu.fh.cs.compgeometry.stramm.linesweep.baseclasses;

import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.ErrorList;

/**
 * Created by Basti on 26.05.2015.
 */
public abstract class BaseErrorList implements ErrorList {

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
