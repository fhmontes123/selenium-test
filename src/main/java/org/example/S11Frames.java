package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class S11Frames {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 2. Abrir el navegador en una ruta
        driver.get("https://www.w3schools.com/TAGS/tryit.asp?filename=tryhtml_iframe");

        // 3. Cambiar de frame
        driver.switchTo().frame("iframeResult");
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe")));

        // 4. Obtener la referencia del boton "Login" del iframe
        WebElement iframeElement = driver.findElement(By.xpath("//*[@id=\"tnb-login-btn\"]/span[1]"));
        // 5. Hacer clic en el boton
        iframeElement.click();
    }
}
