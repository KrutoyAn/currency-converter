package com.practicum.currencyconverter.di;

import com.practicum.currencyconverter.data.CurrenciesConverter;
import com.practicum.currencyconverter.data.cache.CurrencyCourseDataStore;
import com.practicum.currencyconverter.data.network.ApiProvider;
import com.practicum.currencyconverter.data.network.ExchangerRateService;
import com.practicum.currencyconverter.data.network.ExchangerRateUrlProvider;

public class DI {

    private static ExchangerRateService exchangerRateService;
    private static CurrencyCourseDataStore currencyCourseDataStore;

    public static ExchangerRateService exchangerRateService() {
        if (exchangerRateService == null) {
            exchangerRateService = createExchangerRateService();
        }

        return exchangerRateService;
    }

    public static CurrencyCourseDataStore currencyCourseDataStore() {
        if (currencyCourseDataStore == null) {
            currencyCourseDataStore = new CurrencyCourseDataStore(exchangerRateService());
        }

        return currencyCourseDataStore;
    }

    public static CurrenciesConverter currenciesConverter(){
        return new CurrenciesConverter();
    }

    private static ExchangerRateService createExchangerRateService() {
        final ExchangerRateUrlProvider urlProvider = new ExchangerRateUrlProvider();
        final ApiProvider apiProvider = new ApiProvider(urlProvider);

        return apiProvider.getExchangerRateService();
    }
}
