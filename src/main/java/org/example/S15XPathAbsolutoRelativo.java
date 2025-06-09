package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class S15XPathAbsolutoRelativo {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 2. Abrir la pagina del login
        driver.get("https://www.saucedemo.com/");

        // 3. XPATH Absoluto
        WebElement absoluto = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[1]/input"));

        // 4. XPATH Relativo
        WebElement relativo = driver.findElement(By.xpath("//*[@id=\"password\"]"));

        // 4. Ingresar los datos
        absoluto.sendKeys("superuser");
        relativo.sendKeys("password");
    }

}
