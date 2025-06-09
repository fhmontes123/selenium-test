package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class S13EsperaExplicita {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 2. Abrir la pagina del formulario
        driver.get("https://www.saucedemo.com");

        // 3. Configurar una espera explicita de 10 seg
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Los 10 seg es un limite maximo

        // 4. Aplicar la espera hasta que el campo para ingresar el email sea visible
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        // 5. Añadir un nombre de usuario
        emailField.sendKeys("nuevousuario");
    }
}
