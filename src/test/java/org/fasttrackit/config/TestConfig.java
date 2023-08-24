package org.fasttrackit.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
<<<<<<< HEAD
import org.fasttrackit.Page;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
=======
import org.openqa.selenium.OutputType;
>>>>>>> origin/master


public class TestConfig {

    public static final String SAMSUNG_G_23_ULTRA = "1440x3880";

    public TestConfig() {
        Configuration.browser = "Firefox";
<<<<<<< HEAD
        Selenide.open(Page.HOMEPAGE_URL);
=======
>>>>>>> origin/master
        Configuration.browserSize = SAMSUNG_G_23_ULTRA;

    }
    public byte[] screenshot (){
        return Selenide.screenshot(OutputType.BYTES);
    }

}
