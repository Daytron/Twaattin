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

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ryan Gilera
 */
@SuppressWarnings("serial")
public class SourceColumnDecorator implements Table.ColumnGenerator {

    /**
     * @return Source of the tweet as a {@link Link} component.
     */
    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {

        Item item = source.getItem(itemId);

        @SuppressWarnings("unchecked")
        Property<String> property = item.getItemProperty(columnId);

        Document document = Jsoup.parseBodyFragment(property.getValue());

        Elements elements = document.getElementsByTag("a");

        if (elements.size() > 0) {

            Element element = elements.get(0);

            String text = element.text();

            String url = element.absUrl("href");

            ExternalResource resource = new ExternalResource(url);

            Link link = new Link(text, resource);

            link.setTargetName("source");

            return link;
        }

        return null;
    }

}
