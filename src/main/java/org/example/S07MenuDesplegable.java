package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class S07MenuDesplegable {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://www.wikipedia.org/");
        // 3. Maximizar la ventana del navegador
        driver.manage().window().maximize();

        // Localizar el menu por ID
        WebElement menuDesplegable = driver.findElement(By.id("searchLanguage"));
        // Crear objeto select para interactuar
        Select seleccionarLenguaje = new Select(menuDesplegable);
        // Seleccionar una opcion
        seleccionarLenguaje.selectByValue("es");
        // Verificar la opcion seleccionada
        String seleccionOp = seleccionarLenguaje.getFirstSelectedOption().getText();
        System.out.println("Idioma seleccionado: " + seleccionOp);
    }
}
