package io.github.phantamanta44.spaceres.util;

public class MathUtil {

    public static int clamp(int n, int l, int u) {
         return Math.max(Math.min(n, u), l);
    }

    public static float clamp(float n, float l, float u) {
         return Math.max(Math.min(n, u), l);
    }

    public static double clamp(double n, double l, double u) {
         return Math.max(Math.min(n, u), l);
    }

    public static boolean isInBounds(int n, int l, int u) {
        return n >= l && n < u;
    }

    public static boolean isInBounds(float n, float l, float u) {
        return n >= l && n < u;
    }

    public static boolean isInBounds(double n, double l, double u) {
        return n >= l && n < u;
    }

}