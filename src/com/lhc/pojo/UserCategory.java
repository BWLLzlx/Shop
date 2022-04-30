package com.lhc.pojo;

public class UserCategory {
    public static final Integer PAGE_SIZE = 16;

    private Integer id;
    private Integer food;
    private Integer clothes;
    private Integer daily;
    private Integer furniture;
    private Integer electric;
    private Integer fun;

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Integer getClothes() {
        return clothes;
    }

    public void setClothes(Integer clothes) {
        this.clothes = clothes;
    }

    public Integer getDaily() {
        return daily;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Integer getFurniture() {
        return furniture;
    }

    public void setFurniture(Integer furniture) {
        this.furniture = furniture;
    }

    public Integer getElectric() {
        return electric;
    }

    public void setElectric(Integer electric) {
        this.electric = electric;
    }

    public Integer getFun() {
        return fun;
    }

    public void setFun(Integer fun) {
        this.fun = fun;
    }

    @Override
    public String toString() {
        return "UserCategory{" +
                "id=" + id +
                ", food=" + food +
                ", clothes=" + clothes +
                ", daily=" + daily +
                ", furniture=" + furniture +
                ", electric=" + electric +
                ", fun=" + fun +
                '}';
    }

    public UserCategory(Integer id, Integer food, Integer clothes, Integer daily, Integer furniture, Integer electric, Integer fun) {
        this.id = id;
        this.food = food;
        this.clothes = clothes;
        this.daily = daily;
        this.furniture = furniture;
        this.electric = electric;
        this.fun = fun;
    }

    public UserCategory() {
    }
}
