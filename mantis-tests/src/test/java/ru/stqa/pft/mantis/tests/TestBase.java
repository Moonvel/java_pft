package ru.stqa.pft.mantis.tests;


import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME);
    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

}