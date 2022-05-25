package com.project.jobsearch.pojo;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionResponseTest {

    @Test
    public void getStatusTest() {
     ExceptionResponse exe=new ExceptionResponse();
     exe.setStatus(808);
     assertEquals(808,exe.getStatus());
    }

    @Test
    public void getMessageTest() {
        ExceptionResponse exe=new ExceptionResponse();
        exe.setMessage("no error");
        exe.setTimeStamp(System.currentTimeMillis());
        assertEquals("no error",exe.getMessage());
    }

    @Test
    public void getTimeStampTest() {
        ExceptionResponse exe=new ExceptionResponse(404,"no error",System.currentTimeMillis());
        assertEquals(System.currentTimeMillis(),exe.getTimeStamp());

    }
}