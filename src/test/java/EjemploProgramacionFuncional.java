import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class EjemploProgramacionFuncional {
    WebDriver driver;
    @Test
    public void pruebaNoFuncional(){
        driver.get("https://amazon.in");
        List<WebElement> list = driver.findElements(By.xpath("//a"));
        List<String> listText = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i).getText();
            if(!temp.isBlank()){
                if (temp.startsWith("C") || temp.startsWith("D")) {
                    listText.add(temp);
                }
            }
        }
        List<String> listAfterRemoveDuplicates = new ArrayList<>(new HashSet<String>(listText));
        Collections.sort(listAfterRemoveDuplicates);
        listAfterRemoveDuplicates.forEach(t -> System.out.println(t));
    }

    @Test
    public void pruebaFuncional(){
        driver.get("https://amazon.in");
        driver.findElements(By.xpath("//a"))
                .stream()
                .map(e -> e.getText())
                .distinct()
                .sorted()
                .filter(s -> s.startsWith("C") || s.startsWith("D"))
                .forEach(t -> System.out.println(t));
    }

    @BeforeClass
    public void antes(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }
    @AfterClass
    public void finalizar(){
        if(driver!=null){
            driver.quit();
        }
    }
}
