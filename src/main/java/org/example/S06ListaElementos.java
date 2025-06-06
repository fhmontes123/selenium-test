package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class S06ListaElementos {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        // 3. Maximizar la ventana del navegador
        driver.manage().window().maximize();

        // Localizar la lista de articulos destacados
        List<WebElement> articulos = driver.findElements(By.cssSelector("#mp-upper .mp-h2"));
        // Imprimir los titulos de los articulos
        for (WebElement articulo : articulos) {
            System.out.println("Articulo destacado: " + articulo.getText());
        }
    }
}
