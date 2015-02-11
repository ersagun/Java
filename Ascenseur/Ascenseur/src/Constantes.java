public class Constantes{
    private static boolean parfait;

    public static boolean isModeParfait() {
        return parfait;
    }

    public static void setModeParfait(boolean p) {
        parfait = p;
    }

    static final long tempsPourEntrerOuSortirDeLaCabine = 4;

    static final long tempsPourOuvrirOuFermerLesPortes = 5;

    static final long tempsPourBougerLaCabineDUnEtage = 14;

    static final int nombreDePlacesDansLaCabine = 3;
}
