package com.tradex.orderservice;

import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.WireMockConfigurationCustomizer;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockCustomizer implements WireMockConfigurationCustomizer {

    @Override
    public void customize(WireMockConfiguration configuration, ConfigureWireMock options) {
        configuration.notifier();
    }
}