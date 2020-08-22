package com.laoh.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DuplicateRemovalMessage implements Serializable {

    private static final long serialVersionUID = -2076253696811811906L;

    private Long MsgId;
    
    private String FromUserName;
    
    private String CreateTime;

}