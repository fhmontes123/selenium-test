package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P02EventoClick {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://www.google.com/");

        // Localizar boton iniciar sesion de google
        WebElement btnIniciarSesion = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[3]/a"));

        // Hacer clic sobre el boton
        btnIniciarSesion.click();

        // Cerrar el navegador
        driver.quit();
    }
}
