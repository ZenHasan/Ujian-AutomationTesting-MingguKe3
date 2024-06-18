package com.juaracoding.ujianMingguKe3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ECommerceTesting {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\JUARA CODING\\MyTools\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Maximize Window");
        driver.get("https://www.saucedemo.com/");
        System.out.println("Open Browser");
        delay(1);

        // Scenario Positif Test Case
        driver.findElement(By.xpath("//input[contains(@id,'user')]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Login Button Clicked");
        delay(2);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");

        String txtSuccessLogin = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        System.out.println("Login Success Text: " + txtSuccessLogin);
        assertEquals(txtSuccessLogin, "Swag Labs");
        delay(2);

        // Add Product
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        delay(1);
        driver.findElement(By.xpath("//button[@id='add-to-cart']")).click();
        System.out.println("Produk Ditambahkan ke Keranjang");
        delay(1);
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        System.out.println("Success Menambahkan Produk");
        delay(1);

        // Logout
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        delay(2);
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        System.out.println("Logout Successful");
        delay(2);

        // Scenario Negative Test Case
        driver.findElement(By.xpath("//input[contains(@id,'user')]")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        delay(2);

        String txtLoginError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        System.out.println("Login Error Message: " + txtLoginError);
        assertEquals(txtLoginError, "Epic sadface: Username is required");

        delay(2);

        driver.quit();
    }

    static void delay(long detik){
        try {
            Thread.sleep(2000 * detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void assertEquals(String actual, String expected){
        if (actual.equals(expected)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed - Expected: " + expected + ", but got: " + actual);
        }
    }
}
