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

import com.github.daytron.twaattin.presenter.LogoutBehaviour;
import com.github.daytron.twaattin.presenter.TweetRefresherBehaviour;
import com.github.daytron.twaattin.ui.tabledecorator.NameColumnGenerator;
import com.github.daytron.twaattin.ui.tabledecorator.ProfileImageColumnGenerator;
import com.github.daytron.twaattin.ui.tabledecorator.ScreenNameColumnGenerator;
import com.github.daytron.twaattin.ui.tabledecorator.SourceColumnDecorator;
import com.github.daytron.twaattin.ui.tabledecorator.TweetColumnDecorator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.security.Principal;

/**
 *
 * @author Ryan Gilera
 */
public class TimelineScreen extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    public TimelineScreen() {
        setMargin(true);
        
        Label label = new Label(VaadinSession.getCurrent().getAttribute(Principal.class).getName());
        
        Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(new LogoutBehaviour());
        
        HorizontalLayout menuBar = new HorizontalLayout(label, logoutButton);
        menuBar.setWidth(100, Unit.PERCENTAGE);
        menuBar.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
        addComponent(menuBar);
        
        addComponentAttachListener(new TweetRefresherBehaviour());
        
        Table table = new Table();
        addComponent(table);
        
        table.addGeneratedColumn("source", new SourceColumnDecorator());
        table.addGeneratedColumn("screenName", new ScreenNameColumnGenerator());
        table.addGeneratedColumn("name", new NameColumnGenerator());
        table.addGeneratedColumn("profileImage", new ProfileImageColumnGenerator());
        table.addGeneratedColumn("text", new TweetColumnDecorator());
        
        table.setColumnHeader("source", "via");
        table.setColumnHeader("screenName", "Screen name");
        table.setColumnHeader("profileImage","");
        table.setColumnHeader("text","Tweet");
        
        table.setVisibleColumns(new Object[]{"text","name","screenName",
            "profileImage","createdAt","source"});
        
        
    }
    
    
    
}
