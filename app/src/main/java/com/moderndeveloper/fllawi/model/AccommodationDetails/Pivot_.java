package com.moderndeveloper.fllawi.model.AccommodationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pivot_ implements Serializable{

    @SerializedName("accomodation_id")
    @Expose
    private int accomodationId;
    @SerializedName("inclusion_id")
    @Expose
    private int inclusionId;
    @SerializedName("is_applicable")
    @Expose
    private int isApplicable;

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
    }

    public int getInclusionId() {
        return inclusionId;
    }

    public void setInclusionId(int inclusionId) {
        this.inclusionId = inclusionId;
    }

    public int getIsApplicable() {
        return isApplicable;
    }

    public void setIsApplicable(int isApplicable) {
        this.isApplicable = isApplicable;
    }

}