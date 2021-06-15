package com.testol.auto_cher.Enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
*
*URL访问路径 --po 持久化对象
*
**/
@Setter
@Getter
@Entity
@Table(name = "interfaceMsg")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class InterfaceMsg {
        private static final long serialVersionUID = 1L;
        /**
         * url的id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @NotBlank(message = "id不能为空")
        @Id
        private Long id;
        /**
        * 请求类型：get/post/put/delete/update
        */
        @NotBlank(message = "请求类型不能为空")
        private String requestType ;
        /**
        * 请求地址address
        */
        @NotBlank(message = "url地址不能为空")
        private String urlAddress;
        /**
         * 请求参数
         */
        private String params;
        /**
         * 备注信息
         */
        private String remark;

}