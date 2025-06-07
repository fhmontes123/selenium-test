package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class S09VentanasEmergentes {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://demoqa.com/alerts");
        // 3. Maximizar la ventana del navegador
        driver.manage().window().maximize();

        // Hacer clic sobre el boton
        WebElement btnAlert = driver.findElement(By.xpath("//*[@id=\"alertButton\"]"));
        btnAlert.click();

        // Localizar a la ventana de alert
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText()); // Imprimir texto del alert

        // Clic sobre el boton aceptar del alert
        alert.accept();
    }
}
