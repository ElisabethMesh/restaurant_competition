package com.meshalkina.restaurant_competition.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {
    @Pointcut("execution(* com.meshalkina.restaurant_competition.service.*Service.create*(..))")
    public void allCreateMethodsFromService() {
    }

    @Pointcut("execution(* com.meshalkina.restaurant_competition.service.*Service.getAll*())")
    public void allGetAllMethodsFromService() {
    }

    @Pointcut("execution(* com.meshalkina.restaurant_competition.service.*Service.getById*(*))")
    public void allGetByIdMethodsFromService() {
    }

    @Pointcut("execution(* com.meshalkina.restaurant_competition.rest.UserRestController.getUser())")
    public void getUserMethodFromUserRestController() {
    }

    @Pointcut("execution(* com.meshalkina.restaurant_competition.service.*Service.update*(*))")
    public void allUpdateMethodsFromService() {
    }

    @Pointcut("execution(* com.meshalkina.restaurant_competition.service.*Service.delete*(*))")
    public void allDeleteMethodsFromService() {
    }

}
