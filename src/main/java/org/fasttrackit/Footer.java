package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Footer {
    private final SelenideElement undo = $(".fa-undo");
    public void reset(){
        undo.click();
    }
}
