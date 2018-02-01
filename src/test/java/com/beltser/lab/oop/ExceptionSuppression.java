package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ExceptionSuppression {
    public static void main(String[] args) {
        try {
            Exception exception = new Exception("upper", new Exception("lower"));
//            exception.initCause()
            throw exception;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exCauseEx() {
        try {
            throw new Exception("upper", new Exception("lower"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void exToRuntimeEx() {
        exSuppressor();
    }

    private void exSuppressor() {
        try {
            evil();
        } catch (Exception e) {
            RuntimeException causedEx = new RuntimeException("Exception is suppressed", e);
//            causedEx.addSuppressed(e);
//            Throwable throwable = causedEx.initCause(e);
            throw causedEx;
        }
    }

    private void evil() throws Exception {
        throw new Exception("EVIL HERE");
    }

}
