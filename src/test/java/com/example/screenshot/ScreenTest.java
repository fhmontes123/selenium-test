package com.example.screenshot;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private final static String USERNAME = "myuser01";
    private final static String PASSWORD = "Password*123";

    @BeforeClass
    public void setup() {
        //CONFIGURAR WEBDRIVER
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void registerTest() throws InterruptedException {
        driver.get("https://buggy.justtestit.org/");
        Thread.sleep(3000);
        WebElement linkRegister = driver.findElement(By.xpath("//a[contains(.,'Register')]"));
        linkRegister.click();
        Thread.sleep(3000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(USERNAME);
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("myfirstname");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("mylastname");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(PASSWORD);
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        confirmPassword.sendKeys(PASSWORD);
        WebElement btnRegister = driver.findElement(By.xpath("//button[@type='submit' and contains(.,'Register')]"));
        btnRegister.click();
        // BAJAR SCROLL AL FINAL DE LA PAGINA
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(3000);

        // CAPTURA DE PANTALLA CUANDO SE TIENE UN USUARIO REGISTRADO
        // VERIFICAMOS QUE EXISTA EL MENSAJE "Already exists"
        try {
            WebElement alreadyExistUser = wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.cssSelector("form > div.result.alert.alert-danger")));
            // VERIFICAR SI SE MUESTRA EL MENSAJE
            if (alreadyExistUser.isDisplayed()) {
                System.out.println("El texto 'User already exists' esta visible.");
                takeScreenShot("Already exists");
            }
        } catch (TimeoutException ex) {
            // SI NO APARECE EL MENSAJE
            System.out.println("El texto 'Already exists' no es visible");
        }

        //VERIFICAMOS QUE EL REGISTRO FUE EXITOSO
        WebElement message = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("form > div.result.alert.alert-success")));
        Assert.assertTrue(message.getText().contains("Registration is successful"), "Error Register");
    }

    @Test(dependsOnMethods = "registerTest")
    public void loginTest() throws InterruptedException {
        driver.get("https://buggy.justtestit.org/");
        Thread.sleep(3000);
        WebElement username = driver.findElement(By.name("login"));
        username.sendKeys(USERNAME);
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(PASSWORD);
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit' and contains(.,'Login')]"));
        btnLogin.click();
        Thread.sleep(5000);
        //VERIFICAMOS QUE EL LOGIN FUE EXITOSO
        WebElement dashboard = driver.findElement(By.cssSelector("span.nav-link.disabled"));
        Assert.assertTrue(dashboard.getText().contains("Hi,"), "Error Login");
    }

    @Test(dependsOnMethods = "loginTest")
    public void editProfileTest() throws InterruptedException {
        WebElement btnProfile = driver.findElement(By.xpath("//a[contains(.,'Profile')]"));
        btnProfile.click();
        Thread.sleep(3000);

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
        firstName.sendKeys("Maria");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.clear();
        lastName.sendKeys("Gonzales");

        WebElement gender = driver.findElement(By.id("gender"));
        gender.clear();
        gender.sendKeys("Female");
        WebElement age = driver.findElement(By.id("age"));
        age.clear();
        age.sendKeys("20");
        WebElement address = driver.findElement(By.id("address"));
        address.clear();
        address.sendKeys("Nueva Avenida 123");
        WebElement phone = driver.findElement(By.id("phone"));
        phone.clear();
        phone.sendKeys("654321");
        WebElement hobby = driver.findElement(By.id("hobby"));
        Select seleccionarLenguaje = new Select(hobby);
        seleccionarLenguaje.selectByContainsVisibleText("Movies");

        WebElement currentPassword = driver.findElement(By.id("currentPassword"));
        currentPassword.sendKeys(PASSWORD);
        WebElement newPassword = driver.findElement(By.id("newPassword"));
        newPassword.sendKeys("NewPassword*123");
        WebElement newPasswordConfirmation = driver.findElement(By.id("newPasswordConfirmation"));
        newPasswordConfirmation.sendKeys("PasswordNew*123");

        // CAPTURA DE PANTALLA CUANDO LOS PASSWORDS NO SON IGUALES
        // VERIFICAMOS QUE EXISTA EL MENSAJE "Passwords do not match"
        try {
            WebElement messageErrorPassword = wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//div[contains(.,'Passwords do not match')]")));
            // VERIFICAR SI SE MUESTRA EL MENSAJE
            if (messageErrorPassword.isDisplayed()) {
                System.out.println("El texto 'Passwords do not match' esta visible.");
                takeScreenShot("Passwords do not match");
            }
        } catch (TimeoutException ex) {
            // SI NO APARECE EL MENSAJE
            System.out.println("El texto 'Passwords do not match' no es visible");
        }

        WebElement btnSave = driver.findElement(By.xpath("//button[contains(.,'Save')]"));
        btnSave.click();
        Thread.sleep(3000);

        //VERIFICAMOS QUE EL CAMBIO DE DATOS FUE EXITOSO
        WebElement dashboard = driver.findElement(By.cssSelector("div.result.alert.alert-success"));
        Assert.assertTrue(dashboard.getText().contains("The profile has been saved successful"), "Error Edit Profile");
    }

    // METODO PARA SACAR CAPTURA DE PANTALLA
    private void takeScreenShot(String fileName) {
        try {
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // GENERAR NOMBRE PARA UNA CAPTURA UNICA
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenShotName = fileName + "_" + timestamp + ".png";
            // DEFINIR LA RUTA DONDE SE GUARDARA EL ARCHIVO O CAPTURA DE PANTALLA
            Path destination = Paths.get("screenshots", screenShotName);
            // CREAR EL DIRECTORIO SI NO EXISTE
            Files.createDirectories(destination.getParent());
            // COPIAMOS EN EL DESTINO
            Files.copy(screenShotFile.toPath(), destination);
            System.out.println("ScreenShot save " + destination.toAbsolutePath());
        } catch (IOException ex) {
            // ADMINISTRAR CUALQUIER ERROR QUE OCURRA
            System.err.println("Error al obtener la captura de pantalla " + ex.getMessage());
        }
    }

    @AfterClass
    public void cerrar() {
        driver.quit();
    }
}
