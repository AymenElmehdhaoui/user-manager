package com.example.usermanager.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
public class ValidationDetails {

    private List<String> errors;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private String uri;

    public ValidationDetails() {
        this.errors = new ArrayList<>();
        this.timestamp = new Date();
    }

    public void addError(String err) {
        this.errors.add(err);
    }
}
