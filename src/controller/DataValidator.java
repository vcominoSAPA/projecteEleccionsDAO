package controller;

/**
 * Classe que conté els validadors de dades genèrics.
 */
public class DataValidator {

    /**
     * Comprova si un String és un int.
     * @param str String a comprovar.
     * @return True si és un int, false si no ho és.
     */
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Comprova si un String és un long.
     * @param str String a comprovar.
     * @return True si és un long, false si no ho és.
     */
    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Comprova si un String és un VARCHAR(30).
     * @param str String a comprovar.
     * @return True si és un VARCHAR(30), false si no ho és.
     */
    public static boolean isVarchar30(String str) {
        return str.length() <= 30;
    }

    /**
     * Comprova si un String és un VARCHAR(45).
     * @param str String a comprovar.
     * @return True si és un VARCHAR(45), false si no ho és.
     */
    public static boolean isVarchar45(String str) {
        return str.length() <= 45;
    }

    /**
     * Comprova si un String és un VARCHAR(50).
     * @param str String a comprovar.
     * @return True si és un VARCHAR(50), false si no ho és.
     */
    public static boolean isVarchar50(String str) {
        return str.length() <= 50;
    }

    /**
     * Comprova si un String és un VARCHAR(150).
     * @param str String a comprovar.
     * @return True si és un VARCHAR(150), false si no ho és.
     */
    public static boolean isVarchar150(String str) {
        return str.length() <= 150;
    }

    /**
     * Comprova si un String és un CHAR(2).
     * @param str String a comprovar.
     * @return True si és un CHAR(2), false si no ho és.
     */
    public static boolean isChar2(String str) {
        return str.length() == 2;
    }

    /**
     * Comprova si un String és un CHAR(6).
     * @param str String a comprovar.
     * @return True si és un CHAR(6), false si no ho és.
     */
    public static boolean isChar6(String str) {
        return str.length() == 6;
    }

    /**
     * Comprova si un String és un CHAR(8).
     * @param str String a comprovar.
     * @return True si és un CHAR(8), false si no ho és.
     */
    public static boolean isChar8(String str) {
        return str.length() == 8;
    }

    /**
     * Valida una data comprovant que tingui el format dd/mm/aaaa.
     * @param data Data a validar.
     * @return True si la data té el format dd/mm/aaaa, false si no.
     */
    public static boolean isDate(String data) {
        return data.matches("([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/(19|20)[0-9]{2}");
    }
}
