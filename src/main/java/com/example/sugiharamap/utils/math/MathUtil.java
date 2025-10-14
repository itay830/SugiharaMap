package com.example.sugiharamap.utils.math;

public abstract class MathUtil {
    public static double linearInterpolation(double sx, double sy, double ex, double ey, double value)
    {
        return sy + ((value - sx) / (ex - sx)) * (ey - sy);
    }

    public static double scaleRange(double old_min, double old_max, double new_min, double new_max, double old_value)
    {
        return (((old_value - old_min) * (new_max - new_min)) / (old_max - old_min)) + new_min;
    }
}
