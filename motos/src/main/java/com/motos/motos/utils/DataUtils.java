package com.motos.motos.utils;

import java.math.BigDecimal;
import java.util.Objects;

public class DataUtils {

    public static BigDecimal nullSafe(BigDecimal input){
        return Objects.isNull(input) ? BigDecimal.ZERO : input;
    }
}
