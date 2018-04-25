package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

public class FixTypeModel implements Serializable {

    private static final long serialVersionUID = -7059698987300464481L;

    private String Description;
    private String DisplayName;
    private String Id;
    private String Name;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
