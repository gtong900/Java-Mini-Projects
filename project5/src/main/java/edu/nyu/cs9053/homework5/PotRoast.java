package edu.nyu.cs9053.homework5;

public class PotRoast implements Recipe {

    private Double remainingSecondsUntilDone = Double.MAX_VALUE;
    private Double remaingWork = 1.0;
    private final Oven oven;
    private final Timer timer = new Timer() {
        @Override public void update(Time unit, int value, int ovenTemperature) {
            adjust(unit, value, ovenTemperature);
        }
    };

    public PotRoast(Oven oven) {
        this.oven = oven;
    }


    @Override public void initializeFromOven(Oven oven) {
        remainingSecondsUntilDone = (oven.getSetTemperature() / 10.0) * 60;
        oven.cook(this, timer, true);
    }

    @Override public int getVolumeCubicInches() {
        return 10000;
    }

    @Override public Double getRemainingSecondsUntilDone() {
        return remainingSecondsUntilDone;
    }

    @Override public void adjust(Time unit, int amount, int ovenTemperature) {
        if (unit == Time.Minutes) {
            amount *= 60;
        }
        remaingWork -= (1 / remainingSecondsUntilDone) * amount;
        remainingSecondsUntilDone = remaingWork / (1 / ((ovenTemperature / 10.0) * 60));
        if (isRecipeDone()) {
            oven.takeOut(this);
        } else {
            oven.cook(this, timer, false);
        }
    }

    @Override public boolean isRecipeDone() {
        return remaingWork <= 0;
    }
}