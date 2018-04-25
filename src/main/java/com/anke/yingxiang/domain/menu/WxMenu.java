package com.anke.yingxiang.domain.menu;

import java.util.List;

/**
 * Created by qingxiangzhang on 2017/12/5.
 */
public class WxMenu {

    public WxMenu() {
    }

    public WxMenu(List<Button> button) {
        this.button = button;
    }

    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
