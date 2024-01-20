package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;

    private GroupHelper groupHelper;

    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\0409\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        login("admin", "secret");
        groupHelper = new GroupHelper(wd);
    }

    private void login(String username, String password) {
        groupHelper.wd.get("http://localhost/addressbook/group.php");
        groupHelper.wd.findElement(By.name("user")).click();
        groupHelper.wd.findElement(By.name("user")).clear();
        groupHelper.wd.findElement(By.name("user")).sendKeys(username);
        groupHelper.wd.findElement(By.name("pass")).click();
        groupHelper.wd.findElement(By.name("pass")).clear();
        groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
        groupHelper.wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void getLogout() {
        groupHelper.wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        groupHelper.wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
}
