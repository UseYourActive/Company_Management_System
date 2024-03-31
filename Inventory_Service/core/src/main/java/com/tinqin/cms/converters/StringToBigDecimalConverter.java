package com.tinqin.cms.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@Qualifier("StringToBigDecimalConverter")
public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {
    @Override
    public BigDecimal convert(String source) {
        BigDecimal result = new BigDecimal(source);
        log.debug("Converted '{}' to BigDecimal: {}", source, result);

        return result;
    }
}
