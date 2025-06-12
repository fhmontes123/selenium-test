package com.example.datadriving;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DDExcel {
    public static void main(String[] args) {
        // CONFIGURAR PREFERENCIAS DE CHROME
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // CONFIGURAR WEBDRIVER
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com");
        // LEER LOS DATOS DESDE EL EXCEL
        String excelFilePath = "files/Usuarios.xlsx"; // RUTA AL ARCHIVO EXCEL QUE CONTIENE LOS DATOS
        try (
                // CREAR UN FILE INPUTSTRAM PARA LEER EL ARCHIVO
                FileInputStream fileInputStream = new FileInputStream(excelFilePath);
                // CREAR UN WORKBOOK PARA MANEJAR EL ARCHIVO EXCEL
                Workbook workbook = new XSSFWorkbook(fileInputStream);
        ) {
            // LEER LA PRIMERA HOJA DEL EXCEL
            Sheet sheet = workbook.getSheetAt(0);
            // ITERAR SOBRE CADA FILA DE LA HOJA
            for (Row row : sheet) {
                // OMITIR LA PRIMERA FILA DE LOS ENCABEZADOS
                if (row.getRowNum() == 0) {
                    continue; // SALTAR LA PRIMERA FILA
                }
                // LEER EL VALOR DE LA PRIMERA COLUMNA NOMBRE DE USUARIO
                String username = row.getCell(0).getStringCellValue();
                // LEER EL VALOR DE LA SEGUNDA COLUMNA PASSWORD
                String password = row.getCell(1).getStringCellValue();

                // EJECUTAR LA PRUEBA PARA CADA USUARIO
                // LLAMAR AL METODO CON LAS CREDENCIALES DE USUARIO
                performLogin(driver, wait, username, password);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // CERRAR EL NAVEGADOR Y FINALIZAR LA SESION
            driver.quit();
        }
    }

    private static void performLogin(WebDriver driver, WebDriverWait wait, String username, String password) {
        driver.get("https://www.saucedemo.com");
        // INICIAR SESION
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.click();
        passwordField.sendKeys(password);
        loginButton.click();

        // VALIDAR INICIO DE SESION
        try {
            WebElement productTitle = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//span[contains(.,'Products')]")));
            System.out.println("Acceso " + username + " " + productTitle.isDisplayed());
        } catch (Exception ex) {
            // CAPTURAR EL MENSAJE DE USUARIO BLOQUEADO
            // ENCONTRAR EL MENSAJE DE ERROR
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
            if (errorMessage.getText().contains("Epic sadface: Sorry, this user has been locked out.")) {
                System.out.println("El usuario esta bloqueado " + username);
                takeScreenShot(driver, username);
            } else {
                // IMPRIMIR OTRO TIPO DE ERROR
                System.out.println("error " + username + ex.getMessage());
            }
        }
    }

    // METODO PARA SACAR CAPTURA DE PANTALLA
    private static void takeScreenShot(WebDriver driver, String username) {
        try {
            // TOMAR LA CAPTURA
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // GENERAR NOMBRE UNICIO PARA LA CAPTURA
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenShotName = "bloqueado" + username + "_" + timestamp + ".png";
            // DEFINIR LA RUTA DONDE SE GUARDARA EL ARCHIVO O CAPTURA DE PANTALLA
            Path destination = Paths.get("screenshots", screenShotName);
            // CREAR EL DIRECTORIO SI NO EXISTE
            Files.createDirectories(destination.getParent());
            // COPIAMOS EN EL DESTINO
            Files.copy(screenShotFile.toPath(), destination);
            System.out.println("ScreenShot save " + destination.toAbsolutePath());
        } catch (IOException ex) {
            // MANEJAMOS CUALQUIER ERROR QUE OCURRE
            System.err.println("Error al sacar la captura de pantalla " + ex.getMessage());
        }
    }
}