package sample.model;

public class Difficulty {
    public enum value {
        EASY,
        MEDIUM,
        HARD,
        EXTREME,
        INVALID
    }

    public static class Convert {
        public static int toInt(value d) {
            switch (d) {
                case EASY:
                    return 20;
                case MEDIUM:
                    return 30;
                case HARD:
                    return 43;
                case EXTREME:
                    return 55;
                default:
                    return -1;
            }
        }

        public static value toVal(int i) {
            if (i <= 20) {
                return value.EASY;
            } else if (i <= 30) {
                return value.EASY;
            } else if (i <= 43) {
                return value.HARD;
            } else if (i <= 55) {
                return value.EXTREME;
            } else {
                return value.INVALID;
            }
        }
    }
}

