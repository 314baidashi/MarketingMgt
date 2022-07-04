package com.universe.marketing.users.vo;

import lombok.Data;

/**
 * @author yuanjs
 * @description:
 * @date 2020-04-05 11:16
 */
@Data
public class UsersParams {
    private String queryKey;
    private Integer enabled;
    private String projectId;
}
