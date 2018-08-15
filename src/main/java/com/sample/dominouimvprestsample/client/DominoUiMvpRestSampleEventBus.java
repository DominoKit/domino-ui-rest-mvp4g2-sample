package com.sample.dominouimvprestsample.client;

import com.github.mvp4g.mvp4g2.core.eventbus.IsEventBus;
import com.github.mvp4g.mvp4g2.core.eventbus.annotation.Debug;
import com.github.mvp4g.mvp4g2.core.eventbus.annotation.Event;
import com.github.mvp4g.mvp4g2.core.eventbus.annotation.EventBus;
import com.github.mvp4g.mvp4g2.core.eventbus.annotation.Start;
import com.github.mvp4g.mvp4g2.core.history.annotation.InitHistory;
import com.github.mvp4g.mvp4g2.core.history.annotation.NotFoundHistory;
import com.sample.dominouimvprestsample.client.history.DefaultHistoryConverter;
import com.sample.dominouimvprestsample.client.ui.navigation.NavigationPresenter;
import com.sample.dominouimvprestsample.client.ui.shell.ShellPresenter;
import com.sample.dominouimvprestsample.client.ui.statusbar.StatusBarPresenter;
import elemental2.dom.Element;

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
@EventBus(
        shell = ShellPresenter.class
)
@Debug(
        logLevel = Debug.LogLevel.DETAILED
)
public interface DominoUiMvpRestSampleEventBus extends IsEventBus {
    /**
     * This event will be fire by the framework as first event
     * of the application.
     * <p>
     * We will use this event to initiate the setting of the
     * navigation in the west area of the shell by using the bind-attribute.
     * By using the start event to bind the navigation, we make sure
     * that the navigation will be updated before the content area is updated.
     */
    @Start
    @Event(
            bind = {NavigationPresenter.class, StatusBarPresenter.class}
    )
    void start();

    /**
     * This event will set the element (parameter) in the content
     * area of the shell. We will use this event to update the shell
     * with the current content area.
     *
     * @param widget the element of the widget, that will be
     *               displayed inside the content area of the shell.
     */
    @Event
    void setContent(Element widget);

    /**
     * This event will set the element (parameter) in the west
     * area of the shell.
     *
     * @param widget the element of the widget, that will be
     *               displayed inside the west area of the shell.
     */
    @Event
    void setNavigation(Element widget);

    /**
     * This event will set the element (parameter) in the south
     * area of the shell.
     *
     * @param widget the element of the widget, that will be
     *               displayed inside the south area of the shell.
     */
    @Event
    void setStatusBar(Element widget);

    /**
     * This event will update the status in the south
     * area of the shell.
     *
     * @param statusMessage to display.
     */
    @Event
    void updateStatus(String statusMessage);

    /**
     * This event will be used in case:
     * <p>
     * * there is not history-token
     * * the token is not valid
     */
    @InitHistory
    @NotFoundHistory
    @Event
    void initHistory();

    /**
     * This event will display the detail screen inside the content of
     * the shell. The given id will be used to get the person from server
     * and display the view with the data, read rom the server.
     * <p>
     * We use the DetailHistoryConverter to convert the event to
     * the token which the framework will display after the url.
     * <p>
     * We will use the String representated by HistoryName.DETAIL
     * instead the event name inside the token.
     * <p>
     * This event will change the screen displayed inside the
     * content area. From the mvp4g2 point of view, it is a
     * navigation event. If there is a confirm-presenter defined,
     * this presenter will be asked before the view changed.
     */
    @Event(
            historyName = "create",
            historyConverter = DefaultHistoryConverter.class
    )
    void createPerson();


    /**
     * This event will display the detail screen inside the content of
     * the shell. The given id will be used to get the person from server
     * and display the view with the data, read rom the server.
     * <p>
     * We use the DetailHistoryConverter to convert the event to
     * the token which the framework will display after the url.
     * <p>
     * We will use the String representated by HistoryName.DETAIL
     * instead the event name inside the token.
     * <p>
     * This event will change the screen displayed inside the
     * content area. From the mvp4g2 point of view, it is a
     * navigation event. If there is a confirm-presenter defined,
     * this presenter will be asked before the view changed.
     */
    @Event(
            historyName = "edit",
            historyConverter = DefaultHistoryConverter.class
    )
    void editPerson();
}
