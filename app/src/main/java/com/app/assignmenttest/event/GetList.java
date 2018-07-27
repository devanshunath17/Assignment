package com.app.assignmenttest.event;

import com.app.assignmenttest.entity.ListItem;

import java.util.ArrayList;
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
