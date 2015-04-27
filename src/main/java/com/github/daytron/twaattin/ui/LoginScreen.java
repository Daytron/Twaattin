/*
 * The MIT License
 *
 * Copyright 2015 Ryan Gilera.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.daytron.twaattin.ui;

import com.github.daytron.twaattin.presenter.LoginBehaviour;
import com.github.daytron.twaattin.service.TwitterService;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.RuntimeErrorException;
import twitter4j.TwitterException;

/**
 *
 * @author Ryan Gilera
 */
public class LoginScreen extends FormLayout {

    private static final long serialVersionUID = 1L;

    private Link twitterLink;
    private final TextField pinField;
    private final Button submitButton;

    public LoginScreen() {

        this.twitterLink = new Link();
        this.pinField = new TextField();

        this.submitButton = new Button("Submit");

        setMargin(true);
        setSpacing(true);
        try {
            twitterLink.setCaption("Get PIN");
            twitterLink.setTargetName("twitterauth");
            twitterLink.setResource(new ExternalResource(
                    TwitterService.get().getAuthenticationUrl()));

            pinField.setInputPrompt("PIN");

            addComponent(twitterLink);
            addComponent(pinField);
            addComponent(submitButton);

            submitButton.addClickListener(
                    new LoginBehaviour(pinField));
        } catch (TwitterException ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            throw new InstantiationError();

        }
    }

}
