package com.app.assignmenttest.observer.event;

import com.app.assignmenttest.entity.ListItem;

import java.util.List;

/**
 * Created by Devanshu on 27/7/18.
 */

public class GetList {
    public List<ListItem> getListItemList() {
        return listItemList;
    }

    public void setListItemList(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    List<ListItem> listItemList;
    public GetList( List<ListItem> listItemList) {
        this.listItemList=listItemList;

    }
}
