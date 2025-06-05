package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class P03ManejoExcepciones {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://www.google.com/");

        try {
            // Localizar boton iniciar sesion de google
            WebElement btnIniciarSesion = driver.findElement(By.id("NO-EXISTE-ID"));

            // Hacer clic sobre el boton
            btnIniciarSesion.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Un elemento no fue encontrado, revisar el localizador: id, xpath, etc. " + ex);
        } catch (TimeoutException ex) {
            System.out.println("El elemento no fue encontrado.");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error");
        } finally {
            // Cerrar el navegador
            driver.quit();
        }
    }
}
