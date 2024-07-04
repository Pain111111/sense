package com.hack_attack.sense.enums;

public enum RipenessColor {
    DARK_GREEN(0.0, 20.0),
    GREEN(20.01, 40.0),
    YELLOW(40.01, 60.0),
    ORANGE(60.01, 80.0),
    DARK_BROWN(80.01, 100.0);

    private final double lowerRange;
    private final double upperRange;

    RipenessColor(double lowerRange, double upperRange) {
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
    }

    public double getLowerRange() {
        return lowerRange;
    }

    public double getUpperRange() {
        return upperRange;
    }

    public static RipenessColor fromRipenessValue(double ripenessValue) {
        for (RipenessColor color : values()) {
            if (ripenessValue >= color.lowerRange && ripenessValue <= color.upperRange) {
                return color;
            }
        }
        throw new IllegalArgumentException("Invalid ripeness value: " + ripenessValue);
    }
}
