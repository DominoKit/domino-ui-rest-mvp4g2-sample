package com.sample.dominouimvprestsample.client.ui.personscreen;

import com.github.mvp4g.mvp4g2.core.ui.LazyReverseView;
import com.sample.dominouimvprestsample.shared.model.Person;
import elemental2.dom.Element;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.column.Column;
import org.dominokit.domino.ui.forms.FieldsGrouping;
import org.dominokit.domino.ui.forms.Radio;
import org.dominokit.domino.ui.forms.RadioGroup;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.row.Row;
import org.dominokit.domino.ui.style.Style;
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
public class PersonView extends LazyReverseView<IPersonView.Presenter> implements IPersonView {

    private Card container;

    private FieldsGrouping fieldsGrouping = FieldsGrouping.create();
    private TextBox firstNameTextBox;
    private TextBox lastNameTextBox;
    private TextBox phoneNumberTextBox;
    private RadioGroup genderRadioGroup;
    private Button button;

    public PersonView() {
        super();
    }

    @Override
    public Element asWidget() {
        return container.asElement();
    }

    @Override
    public void edit(Person person) {
        // that's a good place to move your data out of the model into wth widgets
        //
        // Using GWT 2.x you can use the editor framewok and in this case
        // it is a good idea to edit and flush ths data inside the presenter.
        firstNameTextBox.setValue(person.getFirstName());
        lastNameTextBox.setValue(person.getLastName());
        phoneNumberTextBox.setValue(person.getPhoneNumber());
        genderRadioGroup.setValue(person.getGender());
        button.setContent("Save");
    }

    @Override
    public void create() {
        fieldsGrouping.clear();
        button.setContent("Create");
    }

    @Override
    public void createView() {
        container = Card.create("Sample content");
        firstNameTextBox = TextBox.create("First name").groupBy(fieldsGrouping);
        lastNameTextBox = TextBox.create("Last name").groupBy(fieldsGrouping);
        phoneNumberTextBox = TextBox.create("Phone number").groupBy(fieldsGrouping);
        genderRadioGroup = Style.of(RadioGroup.create("gender").setLabel("Gender").groupBy(fieldsGrouping))
                .setMarginLeft("10px")
                .get();
        button = Button.createPrimary("Create");
        container
                .appendContent(Row.create()
                        .addColumn(Column.span12()
                                .addElement(firstNameTextBox.setLeftAddon(Icons.ALL.label())))
                )
                .appendContent(Row.create()
                        .addColumn(Column.span12()
                                .addElement(lastNameTextBox.setLeftAddon(Icons.ALL.label())))
                )
                .appendContent(Row.create()
                        .addColumn(Column.span12()
                                .addElement(ElementUtil.numbersOnly(phoneNumberTextBox.setLeftAddon(Icons.ALL.phone()))))
                )
                .appendContent(Row.create()
                        .addColumn(Column.span12()
                                .addElement(genderRadioGroup
                                        .addRadio(Radio.create("male", "Male"))
                                        .addRadio(Radio.create("female", "Female"))
                                        .horizontal()
                                ))
                )
                .appendContent(Row.create()
                        .addColumn(Column.span12()
                                .addElement(button
                                        .addClickListener(evt -> Notification.createInfo("Done").show())))
                );
    }
}
