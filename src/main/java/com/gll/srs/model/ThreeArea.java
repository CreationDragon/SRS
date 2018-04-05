package com.gll.srs.model;

import com.gll.srs.entity.AreaCity;
import com.gll.srs.entity.AreaDistrict;
import com.gll.srs.entity.AreaProvince;

public class ThreeArea {
    private AreaProvince areaProvince;
    private AreaCity areaCity;
    private AreaDistrict areaDistrict;


    public AreaProvince getAreaProvince() {
        return areaProvince;
    }

    public void setAreaProvince(AreaProvince areaProvince) {
        this.areaProvince = areaProvince;
    }

    public AreaCity getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(AreaCity areaCity) {
        this.areaCity = areaCity;
    }

    public AreaDistrict getAreaDistrict() {
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
