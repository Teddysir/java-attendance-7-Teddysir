package attendance;

import attendance.controller.Controller;

public class Application {
    public static void main(String[] args) {

        final Controller controller = new Controller();
        controller.start();

    }
}
