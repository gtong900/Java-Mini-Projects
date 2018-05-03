package edu.nyu.cs9053.homework8;
import java.util.List;

public enum ValidTextKeyPress {
    Two {
        @Override public char getIndex() {
            return '0';
        }
    },
    Three {
        @Override public char getIndex() {
            return '1';
        }
    },
    Four {
        @Override public char getIndex() {
            return '2';
        }
    },
    Five {
        @Override public char getIndex() {
            return '3';
        }
    },
    Six {
        @Override public char getIndex() {
            return '4';
        }
    },
    Seven {
        @Override public char getIndex() {
            return '5';
        }
    },
    Eight {
        @Override public char getIndex() {
            return '6';
        }
    },
    Nine {
        @Override public char getIndex() {
            return '7';
        }
    };

    public abstract char getIndex();

    public static String getValidTextKeyPress(List<ValidTextKeyPress> prefixes) {
        String result ="";
        for (ValidTextKeyPress var : prefixes) {
            result += var.getIndex();
        }
        return result;
    }
}