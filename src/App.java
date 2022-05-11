package src;

import src.views.AppFrame;

public class App {
    public static void main(String[] args) {
        new Thread(new AppFrame()).start();
    }
}