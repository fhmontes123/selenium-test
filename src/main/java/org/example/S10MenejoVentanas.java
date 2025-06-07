package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class S10MenejoVentanas {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        // 2. Abrir el navegador en una ruta
        driver.get("https://www.facebook.com/");
        String primerTab = driver.getWindowHandle(); // Obtener identificador de la primera pestaña

        // Aqui podemos interactuar con la pagina de inicio de sesion de facebook

        // 3. Abrir nueva pestaña
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.facebook.com/help");
        String segudoTab = driver.getWindowHandle();

        try{
            Thread.sleep(2000);
        }catch(InterruptedException ex){
            throw new RuntimeException();
        }

        // 4. Volver a la pestaña principal
        driver.switchTo().window(primerTab);

        // 5. Mostrar los identificadores de las pestañas del navegador
        System.out.println("Primer Tab: " + primerTab);
        System.out.println("Segundo Tab: " + segudoTab);
    }
}
