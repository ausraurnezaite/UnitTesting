package com.coherensolutions.traning.automation.java.web.urnezaite.parser;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.Cart;

import java.io.File;

public interface Parser {

    void writeToFile(Cart cart);
    Cart readFromFile(File file);
}
