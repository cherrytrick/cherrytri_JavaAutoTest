package com.testol.auto_cher.Enity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*

**/
@StaticMetamodel(InterfaceMsg.class)
public class InterfaceMsg_ {
    public static volatile SingularAttribute<InterfaceMsg,Long> id;
    public static volatile SingularAttribute<InterfaceMsg,String> requestType;
    public static volatile SingularAttribute<InterfaceMsg,String> urlAddress;
}
