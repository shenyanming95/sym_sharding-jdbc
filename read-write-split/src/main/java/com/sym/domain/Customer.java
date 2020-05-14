package com.sym.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author shenyanming
 * Created on 2020/5/14 11:28
 */
@Data
@ToString
@Accessors(chain = true)
public class Customer {

    private long id;

    private String username;

    private String realName;

    private boolean isDeleted;

    private LocalDateTime createTime;
}
