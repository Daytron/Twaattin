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

import com.github.daytron.twaattin.service.TwitterService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Table;
import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author Ryan Gilera
 */
@SuppressWarnings("serial")
public class TweetRefresherBehaviour implements HasComponents.ComponentAttachListener {

    @Override
    public void componentAttachedToContainer(HasComponents.ComponentAttachEvent event) {
        Component component = event.getAttachedComponent();
        
        if (component instanceof Table) {
            Table table = (Table) component;
            
            try {
                List<Status> statuses = 
                        TwitterService.get().getTwitter().getUserTimeline();
                
                BeanItemContainer<Status> container = 
                        new BeanItemContainer<>(Status.class);
                
                container.addAll(statuses);
                table.setContainerDataSource(container);
            } catch (TwitterException | IllegalArgumentException e) {
                throw new RuntimeException("TweetRefresher error: " + e.getMessage());
            }
        }
    }
    
}
