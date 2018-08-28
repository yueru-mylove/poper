package com.sport.peking.poper.bean;

import org.apache.ibatis.annotations.Mapper;

public class Jade {
    private Integer id;

    private String name;

    private String source;

    private String born;

    private String inch;

    private String value;

    private String material;

    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born == null ? null : born.trim();
    }

    public String getInch() {
        return inch;
    }

    public void setInch(String inch) {
        this.inch = inch == null ? null : inch.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}