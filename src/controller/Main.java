package controller;

import static view.Print.println;

public class Main {
    public static void main(String[] args) {
        try {
            Controller.menuInicial();
        } catch (Exception e) {
            println("Ha hagut una problema, disculpa les molèsties. :(");
        }
    }
}