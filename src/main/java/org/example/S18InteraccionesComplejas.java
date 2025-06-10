package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class S18InteraccionesComplejas {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 1. Abrir la pagina
        driver.get("https://the-internet.herokuapp.com/context_menu");

        // 2. Seleccionar el cuadro mediante id
        WebElement cuadro = driver.findElement(By.id("hot-spot"));

        // 3. Instanciar la clase Actions de Selenium
        Actions actions = new Actions(driver);

        // 4. Hacer clic derecho
        actions.contextClick(cuadro).perform();

//        actions.doubleClick(cuadro).perform();
    }
}
