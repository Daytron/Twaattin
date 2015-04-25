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
package com.github.daytron.twaattin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.github.daytron.twaattin.MyAppWidgetset")
public class TwaattinUI extends UI {
    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalSplitPanel panel = new HorizontalSplitPanel();
        
        panel.setFirstComponent(new LoginScreen());
        
        TimelineScreen aTimelineScreen = new TimelineScreen();
        panel.setSecondComponent(aTimelineScreen);
        aTimelineScreen.fillTweets();
        
        panel.setSplitPosition(300,Unit.PIXELS);
        
        setContent(panel);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = TwaattinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
