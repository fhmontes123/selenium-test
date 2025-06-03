package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P01Navegadores {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://www.google.com/");

        // Localicar el campo de texto para busca
        WebElement inputBuscar = driver.findElement(By.name("q")); // Localizar por NANE
        // Escribir un texto a buscar
        inputBuscar.sendKeys("Bolivia");
        // Iniciar busqueda presionando ENTER
        inputBuscar.submit();
    }
}
