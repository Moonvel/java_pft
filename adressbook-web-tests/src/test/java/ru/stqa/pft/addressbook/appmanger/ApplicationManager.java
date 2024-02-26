package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {


    private final Properties properties;
    WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private final String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target )));

        dbHelper = new DbHelper();

        switch (browser) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\0409\\chromedriver.exe");
                wd = new ChromeDriver();
                break;
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", "C:\\Windows\\System32\\0409\\geckodriver.exe");
                wd = new FirefoxDriver();
                break;
            case BrowserType.IE:
                System.setProperty("webdriver.ie.driver", "C:\\Windows\\System32\\0409\\IEDriverServer32.exe");
                wd = new InternetExplorerDriver();
                break;
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
        contactHelper = new ContactHelper(wd);
    }



    public void getLogout() {
        wd.findElement(By.linkText("Logout")).click();
    }
    public void stop() {
        wd.quit();
    }
    public GroupHelper group() {
        return groupHelper;
    }
    public NavigationHelper goTo() {
        return navigationHelper;
    }
    public ContactHelper contact() {
        return contactHelper;
    }
    public DbHelper db(){return dbHelper;}
}

