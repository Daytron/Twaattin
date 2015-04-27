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
package com.github.daytron.twaattin.ui.tabledecorator;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import twitter4j.User;

/**
 *
 * @author Ryan Gilera
 */
@SuppressWarnings("serial")
public class ScreenNameColumnGenerator extends AbstractUserColumnGenerator {

    private static final String TWITTER_USER_URL = "https://twitter.com/";

    /**
     * @param source
     * @param itemId
     * @param columnId
     * @return Screen name of the underlying {@link User} as a {@link Link}
     * component.
     */
    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {

        User user = getUser(source, itemId);

        ExternalResource resource = new ExternalResource(TWITTER_USER_URL
                + user.getScreenName());

        Link link = new Link('@' + user.getScreenName(), resource);

        link.setTargetName("screenname");

        return link;
    }
    
    
}
