package com.payu.hrassistant.common;

import com.payu.hrassistant.usermanagement.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static  ResponseEntity<Object> buildErrorResponse(String errorCode, String message, HttpStatus status) {
        return new ResponseEntity<>(CommonUtil.buildFailureResponse(errorCode, message, null), status);
    }
    public static ResponseDto buildFailureResponse(String errorCode, String message, String guid) {
        return ResponseDto.builder().status(0).message(message).guid(guid).errorCode(errorCode).build();
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(date == null) return "";
        String formattedDate = formatter.format(date);

        return formattedDate;
    }

    public static Date createDateObject(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(dateString.isEmpty()) return null;

        Date date = formatter.parse(dateString);

        return date;
    }


}
