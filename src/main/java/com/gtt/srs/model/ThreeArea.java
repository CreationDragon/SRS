package com.gtt.srs.model;

import com.gtt.srs.entity.AreaCity;
import com.gtt.srs.entity.AreaDistrict;
import com.gtt.srs.entity.AreaProvince;

public class ThreeArea {
    private com.gtt.srs.entity.AreaProvince areaProvince;
    private com.gtt.srs.entity.AreaCity areaCity;
    private com.gtt.srs.entity.AreaDistrict areaDistrict;


    public com.gtt.srs.entity.AreaProvince getAreaProvince() {
        return areaProvince;
    }

    public void setAreaProvince(AreaProvince areaProvince) {
        this.areaProvince = areaProvince;
    }

    public com.gtt.srs.entity.AreaCity getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(AreaCity areaCity) {
        this.areaCity = areaCity;
    }

    public com.gtt.srs.entity.AreaDistrict getAreaDistrict() {
        return areaDistrict;
    }

    public void setAreaDistrict(AreaDistrict areaDistrict) {
        this.areaDistrict = areaDistrict;
    }

    @Override
    public String toString() {
        return "ThreeArea{" +
                "areaProvince=" + areaProvince +
                ", areaCity=" + areaCity +
                ", areaDistrict=" + areaDistrict +
                '}';
    }
}
