package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class S16ExpresionesAvanzadasXPath {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 1. Abrir la pagina login
        driver.get("https://www.saucedemo.com/");

        // 2. Construimos nuestro propio XPATH tomando en cuenta las propiedades del input username
        // Seleccionar todos los input con id='user-name'
        // WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        // Seleccionar todos los input con atributo data-test='username'
        // WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        // Seleccionar todos los input que en el valor de id contengan la palabra 'user'
        // WebElement username = driver.findElement(By.xpath("//input[contains(@id, 'user')]"));
        // Seleccionar todos los input cuyo valor en name comienzen con 'user'
        WebElement username = driver.findElement(By.xpath("//input[starts-with(@id, 'user')]"));

        // 4. Ingresar los datos
        username.sendKeys("nuevouser");

    }
}
