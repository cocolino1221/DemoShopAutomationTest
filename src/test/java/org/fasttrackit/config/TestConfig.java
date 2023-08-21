package org.fasttrackit.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;


public class TestConfig {

    public static final String SAMSUNG_G_23_ULTRA = "1440x3880";

    public TestConfig() {
        Configuration.browser = "Firefox";
        Configuration.browserSize = SAMSUNG_G_23_ULTRA;

    }
    public byte[] screenshot (){
        return Selenide.screenshot(OutputType.BYTES);
    }

}
