package com.profdeveloper.fllawi.model.ThingToDoDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot implements Serializable {

    @SerializedName("thing_to_do_id")
    @Expose
    private Integer thingToDoId;
    @SerializedName("inclusion_id")
    @Expose
    private Integer inclusionId;
    @SerializedName("is_applicable")
    @Expose
    private Integer isApplicable;

    public Integer getThingToDoId() {
        return thingToDoId;
    }

    public void setThingToDoId(Integer thingToDoId) {
        this.thingToDoId = thingToDoId;
    }

    public Integer getInclusionId() {
        return inclusionId;
    }

    public void setInclusionId(Integer inclusionId) {
        this.inclusionId = inclusionId;
    }

    public Integer getIsApplicable() {
        return isApplicable;
    }

    public void setIsApplicable(Integer isApplicable) {
        this.isApplicable = isApplicable;
    }

}