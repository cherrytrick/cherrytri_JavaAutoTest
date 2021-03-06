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
*测试用例 --PO（Persistent Object）：持久化对象
*

**/
@Setter
@Getter
@Entity
@Table(name = "testCase")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class TestCase {
        private static final long serialVersionUID = 1L;
        /**
         * url的id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @NotBlank(message = "id不能为空")
        @Id
        private Long id;
        /**
         * 绑定状态 0 正向测试 1 反向测试
         */
        private Integer caseStatus;
        /**
        * 接口信息对应的id
        */
        private Long interfacemsgId ;
        /**
         * 用例描述
         */
        private String descCase;
        /**
         * 请求头参数
         */
        private String headersParames;
        /**
         * 请求体参数
         */
        private String bodyParames;
        /**
         * 期望结果
         */
        private String expect;
        /**
         * 实际响应结果
         */
        private String actual;
        /**
         * 备注信息
         */
        private String remark;

}
