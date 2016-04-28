package com.xxplus.antlr4.old.chapter02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016-03-26.
 */
public class Context {


    private static Context instance = new Context();

    public static Context getInstance(){
        return instance;
    }

    private Context(){}

    private Map<String, Double> mapValue = new HashMap<>();
    private Deque<Double> stack = new ArrayDeque<>();

    public Double getValue(String key){
        Double d = mapValue.get(key);
        return d == null ? Double.NaN : d;
    }

    public void setContext(String key, Double value){
        mapValue.put(key, value);
    }

    public void setContext(String key, String value){
        setContext(key, Double.valueOf(value));
    }

    public void pushStach(Double d){
        stack.push(d);
    }

    public Double popStack(){
        return stack.pop();
    }

}
