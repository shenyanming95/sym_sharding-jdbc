package com.sym.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author shenyanming
 * @date 2020/5/10 19:59.
 */
@Data
@ToString
@Accessors(chain = true)
public class SimpleEntity {
    private long id;
    private String name;
    private String description;
    private boolean isDeleted;
}
