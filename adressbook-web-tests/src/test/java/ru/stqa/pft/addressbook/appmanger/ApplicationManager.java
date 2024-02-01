package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {


    WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private final String browser;
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
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
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
        contactHelper = new ContactHelper(wd);
    }



    public void getLogout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }


    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}

