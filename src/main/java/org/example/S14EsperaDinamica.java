package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class S14EsperaDinamica {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 1. Abrir la pagina del formulario
        driver.get("https://www.saucedemo.com");

        // 2. Configurar una espera dinamica de 10 seg
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 3. Esperamos que el boton "Login" sea clickable
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.name("login-button")));

        // 4. Hacemos clic sobre el boton
        login.click();
    }
}
