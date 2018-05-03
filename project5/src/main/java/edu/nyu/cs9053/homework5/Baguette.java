package edu.nyu.cs9053.homework5;

public class Baguette implements Recipe {

    private Double remainingSecondsUntilDone = Double.MAX_VALUE;
    private Double remaingWork = 1.0;
    private final Oven oven;
    private final double rate;
    private final Timer timer = new Timer() {
        @Override public void update(Time unit, int value, int ovenTemperature) {
            adjust(unit, value, ovenTemperature);
        }
    };

    public Baguette(Oven oven, double rate) {
        this.oven = oven;
        this.rate = rate;
    }


    @Override public void initializeFromOven(Oven oven) {
        remainingSecondsUntilDone = calculateTime(oven.getSetTemperature());
        oven.cook(this, timer, true);
    }

    @Override public int getVolumeCubicInches() {
        return 2000;
    }

    @Override public Double getRemainingSecondsUntilDone() {
        return remainingSecondsUntilDone;
    }

    @Override public void adjust(Time unit, int amount, int ovenTemperature) {
        if (unit == Time.Minutes) {
            amount *= 60;
        }
        remaingWork -= (1 / remainingSecondsUntilDone) * amount;
        remainingSecondsUntilDone = remaingWork / (1 / calculateTime(ovenTemperature));
        if (isRecipeDone()) {
            oven.takeOut(this);
        } else {
            oven.cook(this, timer, false);
        }
    }

    @Override public boolean isRecipeDone() {
        return remaingWork <= 0;
    }

    public Double calculateTime(int temperature) {
        double expectedTime = 0.0;
        while ((temperature * Math.pow(Math.E,(-rate * expectedTime))) >= 0.02) {
            expectedTime++;
        }
        return expectedTime * 60;
    }
}