package com.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class CalculatorService {

    @RequestMapping(value = "/calc/{a}/plus/{b}", method = RequestMethod.GET)
    public CalcResult sum(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return new CalcResult(a + b);
    }

    @RequestMapping(value = "/calc/{a}/minus/{b}", method = RequestMethod.GET)
    public CalcResult minus(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return new CalcResult(a - b);
    }

    @RequestMapping(value = "/calc/{a}/times/{b}", method = RequestMethod.GET)
    public CalcResult times(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return new CalcResult(a * b);
    }

    @RequestMapping(value = "/calc/{a}/divide/{b}", method = RequestMethod.GET)
    public CalcResult divide(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        return new CalcResult(a / b);
    }


    public static class CalcResult implements Serializable {

        public Double result;

        public CalcResult(Double result) {
            this.result = result;
        }

    }
}

