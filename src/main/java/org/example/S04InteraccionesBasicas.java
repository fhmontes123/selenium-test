package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class S04InteraccionesBasicas {

    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");
        // 3. Maximizar la ventana del navegador
        driver.manage().window().maximize();

        // Localizar input para ingresar un texto
        WebElement inputBuscar = driver.findElement(By.name("search"));
        // Ingrear texto
        inputBuscar.sendKeys("Bolivia");

        // Localizar boton para buscar
        // WebElement btnBuscar = driver.findElement(By.xpath("//*[@id=\"searchform\"]/div/button"));
        // Clic sobre el boton
        // btnBuscar.click();

        // Presionar ENTER
        inputBuscar.submit();
    }

}
