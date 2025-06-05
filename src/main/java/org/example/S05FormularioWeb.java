package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class S05FormularioWeb {
    public static void main(String[] args) {
        // 0. Establecer la ruta del archivo chromedriver.exe
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // 1. Crear un objeto o instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();
        // 2. Abrir el navegador en una ruta
        driver.get("https://auth.wikimedia.org/eswiki/wiki/Especial:Crear_una_cuenta");
        // 3. Maximizar la ventana del navegador
        // driver.manage().window().maximize();

        // Localizar el input de usuario
        WebElement inputUsuario = driver.findElement(By.id("wpName2"));
        inputUsuario.sendKeys("juangonzales784181"); // Ingresar texto

        // Localizar el input de password
        WebElement inputPassword = driver.findElement(By.id("wpPassword2"));
        inputPassword.sendKeys("Prueba*123"); // Ingresar texto

        // Localizar el input de confirmar password
        WebElement inputConfPassword = driver.findElement(By.id("wpRetype"));
        inputConfPassword.sendKeys("Prueba*123"); // Ingresar texto

        // Localizar el input email
        WebElement inputEmail = driver.findElement(By.id("wpEmail"));
        inputEmail.sendKeys("juanperez@gmail.com"); // Ingresar texto

        // Localizar el input de captcha
        WebElement inputCaptcha = driver.findElement(By.id("mw-input-captchaWord"));
        inputCaptcha.sendKeys("demotest"); // Ingresar texto

        // Localizar el boton Crear Cuenta
        WebElement btnCrearCuenta = driver.findElement(By.id("wpCreateaccount"));
        btnCrearCuenta.click();
    }

}
