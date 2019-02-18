package com.dipanshu.mycityapp.Weather_dir;

class Detail {
    private String main,description,icon;

    Detail(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon=icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
