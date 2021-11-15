package com.hans.rboard.dto;

import lombok.Data;

@Data
public class ResRemoveUser {
    boolean deleteSuccess;

    public ResRemoveUser (int result) {
        this.deleteSuccess = result != 0;
    }
}
