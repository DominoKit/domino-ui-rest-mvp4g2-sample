package com.sample.dominouimvprestsample.client.ui.shell;

import com.github.mvp4g.mvp4g2.core.ui.LazyReverseView;
import elemental2.dom.CSSProperties;
import elemental2.dom.Element;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.utils.ElementUtil;

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
public class ShellView extends LazyReverseView<IShellView.Presenter> implements IShellView {

    private Layout layout;

    public ShellView() {
        super();
    }

    @Override
    public void show() {
        layout.show();
    }

    @Override
    public void createView() {
        layout = Layout.create("SAMPLE");
    }

    @Override
    public void setContent(Element content) {
        ElementUtil.clear(layout.getContentPanel());
        layout.getContentPanel().appendChild(content);
    }

    @Override
    public void setHeader(Element widget) {
        ElementUtil.clear(layout.getNavigationBar().asElement());
        layout.getNavigationBar().asElement().appendChild(widget);
    }

    @Override
    public void setNavigation(Element widget) {
        ElementUtil.clear(layout.getLeftPanel());
        layout.getLeftPanel().appendChild(widget);
        layout.fixLeftPanelPosition();
    }

    @Override
    public void setStatusBar(Element widget) {
        layout.showFooter();
        layout.getFooter().asElement().style.minHeight = CSSProperties.MinHeightUnionType.of("20px");
        layout.getFooter().appendChild(widget);
    }
}
