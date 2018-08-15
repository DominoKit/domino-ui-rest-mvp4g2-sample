package com.sample.dominouimvprestsample.client.ui.statusbar;

import com.github.mvp4g.mvp4g2.core.ui.LazyReverseView;
import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;

/**
 * Copyright (C) 2018 Frank Hossfeld <frank.hossfeld@googlemail.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class StatusBarView extends LazyReverseView<IStatusBarView.Presenter> implements IStatusBarView {
    private HTMLElement container;

    private HTMLElement label;

    public StatusBarView() {
        super();
    }

    @Override
    public Element asWidget() {
        return container;
    }

    @Override
    public void edit(String message) {
        label.textContent = message;
    }

    @Override
    public void createView() {
        label = Elements.label().asElement();
        container = Elements.header().style("padding: 15px").add(label).asElement();
    }
}
