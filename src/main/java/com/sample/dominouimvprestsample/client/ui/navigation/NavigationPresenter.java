package com.sample.dominouimvprestsample.client.ui.navigation;

import com.github.mvp4g.mvp4g2.core.ui.AbstractPresenter;
import com.github.mvp4g.mvp4g2.core.ui.annotation.Presenter;
import com.sample.dominouimvprestsample.client.DominoUiMvpRestSampleEventBus;

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
@Presenter(
        viewClass = NavigationView.class,
        viewInterface = INavigationView.class
)
public class NavigationPresenter extends AbstractPresenter<DominoUiMvpRestSampleEventBus, INavigationView> implements INavigationView.Presenter {
    public NavigationPresenter() {
    }

    @Override
    public void onBeforeEvent(String eventName) {
        // This method will be call in case the presenter will handle a event and before the event handling
    }

    @Override
    public void bind() {
        eventBus.setNavigation(view.asWidget());
    }

    public void doNavigateTo(String target) {
        switch (target) {
            case "create":
                eventBus.createPerson();
                break;
            case "edit":
                eventBus.editPerson();
                break;
        }
    }
}
