package com.sample.dominouimvprestsample.client.ui.personscreen;

import com.github.mvp4g.mvp4g2.core.ui.AbstractPresenter;
import com.github.mvp4g.mvp4g2.core.ui.IsViewCreator;
import com.github.mvp4g.mvp4g2.core.ui.annotation.EventHandler;
import com.github.mvp4g.mvp4g2.core.ui.annotation.Presenter;
import com.google.gwt.core.client.GWT;
import com.sample.dominouimvprestsample.client.DominoUiMvpRestSampleEventBus;
import com.sample.dominouimvprestsample.shared.model.Person;
import org.dominokit.rest.shared.RestfulRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        viewClass = PersonView.class,
        viewInterface = IPersonView.class,
        viewCreator = Presenter.VIEW_CREATION_METHOD.PRESENTER
)
public class PersonPresenter extends AbstractPresenter<DominoUiMvpRestSampleEventBus, IPersonView> implements IPersonView.Presenter, IsViewCreator<IPersonView> {
    public static final int DONE = 200;

    private static final Logger LOGGER = Logger.getLogger(PersonPresenter.class.getCanonicalName());

    public PersonPresenter() {
    }

    @Override
    public void onBeforeEvent(String eventName) {
        // This method will be call in case the presenter will handle a event and before the event handling
    }

    @EventHandler
    public void onEditPerson() {
        // First we get the information from the server through restful call

        RestfulRequest.get(GWT.getHostPageBaseURL() + "rest/person")
                .onSuccess(response -> {
                    if (response.getStatusCode() == DONE) {
                        Person person = Person.MAPPER.read(response.getBodyAsString());
                        // ok, now place our view into the content area of the viewport!
                        eventBus.setContent(view.asWidget());
                        // yet we are visible!
                        //
                        // now, move the data out of the model into the widgets - that's what we do next
                        view.edit(person);
                        // update the statusbar at the buttom of the screen
                        eventBus.updateStatus("active screen: >>edit person screen<<");
                    } else {
                        LOGGER.log(Level.SEVERE, "Error while loading person info" + response.getStatusText());
                    }
                })
                .onError(throwable -> {
                    LOGGER.log(Level.SEVERE, "Error while loading person info", throwable);
                })
                .send();
    }

    @EventHandler
    public void onCreatePerson() {
        // ok, now place our view into the content area of the viewport!
        eventBus.setContent(view.asWidget());
        // yet we are visible!
        view.create();
        //
        // update the statusbar at the buttom of the screen
        eventBus.updateStatus("active screen: >>create person screen<<");
    }

    @EventHandler
    public void onInitHistory() {
        eventBus.createPerson();
    }

    /**
     * Because we have told mvp4g2, that this presenter will create it's view
     * (viewCreator = Presenter.VIEW_CREATION_METHOD.PRESENTER), we have to
     * implement this method.
     * <p>
     * This enables use, to use GWT.create or something else instead of new (what the framework is doing!)
     * Because this implementation does not know 'GWT.create()' we will do a simple new ...
     *
     * @return a new instance of the view.
     */
    @Override
    public IPersonView createView() {
        return new PersonView();
    }
}
