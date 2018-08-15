package com.sample.dominouimvprestsample.client.history;

import com.github.mvp4g.mvp4g2.core.history.IsHistoryConverter;
import com.github.mvp4g.mvp4g2.core.history.annotation.History;
import com.sample.dominouimvprestsample.client.DominoUiMvpRestSampleEventBus;

@History(
        type = History.HistoryConverterType.SIMPLE
)
public class DefaultHistoryConverter implements IsHistoryConverter<DominoUiMvpRestSampleEventBus> {
    public DefaultHistoryConverter() {
    }

    @Override
    public void convertFromToken(String historyName, String param,
                                 DominoUiMvpRestSampleEventBus eventBus) {
        switch (historyName) {
            case "createPerson":
                eventBus.createPerson();
                break;
            case "editPerson":
                eventBus.editPerson();
                break;
        }
    }

    @Override
    public boolean isCrawlable() {
        return false;
    }

    public String convertToToken(String historyName) {
        return "";
    }
}
