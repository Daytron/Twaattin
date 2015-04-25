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
package com.github.daytron.twaattin.presenter;

import com.github.daytron.twaattin.authentication.AuthenticationException;
import com.github.daytron.twaattin.authentication.SimpleUserPasswordAuthenticationStrategy;
import com.github.daytron.twaattin.ui.TimelineScreen;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.security.Principal;

/**
 *
 * @author Ryan Gilera
 */
public class LoginBehaviour implements Button.ClickListener {
    private final static long serialVersionUID = 1L;
    
    private final TextField loginField;
    private final PasswordField passwordField;

    public LoginBehaviour(TextField loginField, PasswordField passwordField) {
        this.loginField = loginField;
        this.passwordField = passwordField;
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {
            String login = loginField.getValue();
            String password = passwordField.getValue();
            
            Principal user = new SimpleUserPasswordAuthenticationStrategy()
                    .authenticate(login, password);
            
            
            VaadinSession.getCurrent().setAttribute(Principal.class, user);
            
            TimelineScreen aTimelineScreen = new TimelineScreen();
            UI.getCurrent().setContent(aTimelineScreen);
            aTimelineScreen.fillTweets();
            
            Notification.show("You're now authenticated to Twaattin!");
            
            
        } catch (AuthenticationException e) {
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }
    
}
