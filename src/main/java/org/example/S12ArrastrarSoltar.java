package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class S12ArrastrarSoltar {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 2. Abrir la pagina principal
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml5_draganddrop");

        // 3. Cambiar al iframe
        driver.switchTo().frame("iframeResult");

        // 4. Localizar los elementos
        WebElement div = driver.findElement(By.id("div1"));
        WebElement img = driver.findElement(By.id("img1"));

        // 5. Realizar la accion de arrastrar y soltar
        Actions actions = new Actions(driver);
        actions.dragAndDrop(img, div).perform(); // (ElementoAArrastrar, ElementoDondeSoltar)

    }
}
