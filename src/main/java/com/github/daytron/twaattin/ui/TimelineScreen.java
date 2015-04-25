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
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.security.Principal;

/**
 *
 * @author Ryan Gilera
 */
public class TimelineScreen extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    public TimelineScreen() {
        
        Label label = new Label(VaadinSession.getCurrent().getAttribute(Principal.class).getName());
        
        Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(new LogoutBehaviour());
        
        HorizontalLayout menuBar = new HorizontalLayout(label, logoutButton);
        
        addComponent(menuBar);
        setMargin(true);
    }
    
    public void fillTweets() {
        for(int i=0; i<10; i++) {
            Label label = new Label();
            
            // Placeholder for now
            label.setValue("Lorem ipsum dolor sit amet, consectetur "
                    + "adipiscing elit. Suspendisse efficitur massa a"
                    + " ex ultrices luctus. Ut laoreet lectus sem, "
                    + "vitae interdum justo vestibulum nec");
            
            addComponent(label);
        }
    }
    
    
}
