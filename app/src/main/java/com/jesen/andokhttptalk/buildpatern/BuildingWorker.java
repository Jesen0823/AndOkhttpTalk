package com.jesen.andokhttptalk.buildpatern;

// 建筑员工
public class BuildingWorker {

    private HouseParam houseParam;

    // 拿到图纸
    public void setHouseParam(HouseParam houseParam) {
        this.houseParam = houseParam;
    }


    public House buildHouse(){
        House house = new House();
        house.setColor(houseParam.getColor());
        house.setHeight(houseParam.getHeight());
        house.setWidth(houseParam.getWidth());
        return house;
    }
}
