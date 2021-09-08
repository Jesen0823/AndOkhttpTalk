package com.jesen.andokhttptalk.buildpatern;

// 设计师
public class Designer {

    private HouseParam houseParam;
    private BuildingWorker buildingWorker;


    public Designer(){
        houseParam = new HouseParam();
        buildingWorker = new BuildingWorker();
    }




    // 设计师收到需求
    public Designer addHeight(double height){
        // 设计师画图纸
        houseParam.setHeight(height);
        return this;
    }

    public Designer addWidth(double width){
        houseParam.setWidth(width);
        return this;
    }

    public Designer addColor(String color){
        houseParam.setColor(color);
        return this;
    }

    public House build() {
        // 给图纸
        buildingWorker.setHouseParam(houseParam);
        // 让工人盖房
        return buildingWorker.buildHouse();
    }
}
