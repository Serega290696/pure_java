package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

public class ExceptionSuppression {
    @Test
    void exToRuntimeEx() {
        exSuppressor();
    }

    private void exSuppressor() {
        try {
            evil();
        } catch (Exception e) {
            RuntimeException e2 = new RuntimeException("Exception is suppressed");
            e2.addSuppressed(e);
//            Throwable throwable = e2.initCause(e);
            throw e2;
        }
    }

    private void evil() throws Exception {
        throw new Exception("EVIL HERE");
    }

}
