package com.example.coincalculator.config.entity;

import java.io.Serializable;

public interface IAuditable extends Serializable {

    boolean hasCreateInfo();

    void addUpdateUser(Long userId);

}
