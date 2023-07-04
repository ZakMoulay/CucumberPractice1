package steps;

import pages.DashboardPage;
import pages.LoginPage;

public class PageInitializer {
    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;



    public static void  initializePageObjects() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

}
