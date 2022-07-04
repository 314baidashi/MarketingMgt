package com.universe.marketing.client.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据传输对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String clientId;
    private String clientName;
    private String clientPhone;
    private String clientAddress;
}

