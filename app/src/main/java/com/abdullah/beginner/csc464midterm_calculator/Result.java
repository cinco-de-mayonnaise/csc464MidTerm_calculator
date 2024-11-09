package com.abdullah.beginner.csc464midterm_calculator;

public class Result {
    double fp_result;
    long int_result;
    // implement support for big numbers later
    Class<?> type;

    public Result(double val)
    {
        this.fp_result = val;
        this.type = Double.class;
    }

    public Result(long val)
    {
        this.int_result = val;
        this.type = Long.class;
    }

    public Class<?> getType()
    {
        return this.type;
    }

    public Number getValue()
    {
        if (this.type == Long.class)
            return this.int_result;
        else if (this.type == Double.class)
            return this.fp_result;

        return null;
    }
}