package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class S08Checkbox {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://www.w3schools.com/html/html_forms.asp");
        // 3. Maximizar la ventana del navegador
        driver.manage().window().maximize();

        // Localizar los checkbox en una coleccion de elementos
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        // Iterar para seleccionar las casillas
        for (WebElement checkbox : checkboxes) {
            // Obtener el valor de value
            String value = checkbox.getDomProperty("value");
            // Preguntamos si es Bike o Boat para luego seleccionarlo
            if ("Bike".equals(value) || "Boat".equals(value)) {
                // Verificar si la casilla no esta seleccionada
                if (!checkbox.isSelected()) {
                    // Hacer clic para seleccionarla
                    checkbox.click();
                }
            }
        }
    }
}
