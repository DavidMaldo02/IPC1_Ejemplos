package ipc1.lab.models;

public class Product {
    private String code;
    private String name;
    private String material;
    private String color;
    private int processTime;

    public Product(String code, String name, String material, String color, int processTime) {
        this.code = code;
        this.name = name;
        this.material = material.toUpperCase();
        this.color = color.toUpperCase();
        this.processTime = processTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
