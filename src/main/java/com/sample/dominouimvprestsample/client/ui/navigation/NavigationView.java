package com.sample.dominouimvprestsample.client.ui.navigation;

import com.github.mvp4g.mvp4g2.core.ui.LazyReverseView;
import elemental2.dom.Element;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.menu.Menu;
import org.dominokit.domino.ui.menu.MenuItem;

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
public class NavigationView extends LazyReverseView<INavigationView.Presenter> implements INavigationView {

    private Menu menu;

    public NavigationView() {
        super();
    }

    @Override
    public Element asWidget() {
        return menu.asElement();
    }

    @Override
    public void createView() {
        menu = Menu.create("Sample");
        MenuItem createPersonMenuItem = menu.addMenuItem("Create Person", Icons.ALL.add());
        MenuItem editPersonMenuItem = menu.addMenuItem("Edit Person", Icons.ALL.edit());
        createPersonMenuItem.addClickListener(evt -> presenter.doNavigateTo("create"));
        editPersonMenuItem.addClickListener(evt -> presenter.doNavigateTo("edit"));
    }
}
