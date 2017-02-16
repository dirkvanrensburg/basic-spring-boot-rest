package com.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.cache.annotation.CacheResult;
import java.io.Serializable;
import java.util.Date;

@RestController
public class CalculatorService {

    @CacheResult(cacheName="sumCache")
    @RequestMapping(value = "/calc/{a}/plus/{b}", method = RequestMethod.GET)
    public CalcResult sum(@PathVariable("a") Double a, @PathVariable("b") Double b) {

        //Print a message so we can see that method is not executed for cached results
        System.out.println(String.format("******> Calculating %s + %s",a,b));

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

        //Keep the created date so we can see if it came from cache or not
        public Date resultCreated;


        public CalcResult(Double result) {
            this.resultCreated = new Date();
            this.result = result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CalcResult that = (CalcResult) o;

            if (resultCreated != null ? !resultCreated.equals(that.resultCreated) : that.resultCreated != null)
                return false;
            return result != null ? result.equals(that.result) : that.result == null;
        }

        @Override
        public int hashCode() {
            int result1 = resultCreated != null ? resultCreated.hashCode() : 0;
            result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
            return result1;
        }

        @Override
        public String toString() {
            return "CalcResult{" +
                    "resultCreated=" + resultCreated +
                    ", result=" + result +
                    '}';
        }
    }
}

