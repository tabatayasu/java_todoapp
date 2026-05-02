package com.example.todo.entity;

public enum Importance {
    HIGH("high", "高"),
    MIDDLE("middle", "中"),
    LOW("low", "低");

    private final String code;
    private final String label;

    Importance(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
