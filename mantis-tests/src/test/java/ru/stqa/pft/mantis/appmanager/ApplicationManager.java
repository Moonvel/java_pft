package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {
    private Browser browser;
    private WebDriver wd;
    private final Properties properties;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(Browser browser)  {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (Objects.equals(browser, Browser.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "C://Windows//System32//0409//chromedriver.exe");
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, Browser.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "C://Windows//System32//0409//geckodriver.exe");
                System.setProperty("webdriver.firefox.bin", "C://Program Files//Mozilla Firefox//firefox.exe");
                wd = new FirefoxDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            //wd.get("http://localhost/addressbook/");
            wd.get(properties.getProperty("web.baseUrl"));
            //sessionHelper.login("admin", "secret");
        }
        return wd;
    }
    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null ) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}