package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.LabelList
import javafx.event.EventHandler

class LabelListController(
    val model: Model,
    labelList: LabelList
) {
    init {
        labelList.labelList.items = model.listLabel
        labelList.clearButton.onMouseClicked = EventHandler {
            labelList.searchBar.text = ""
        }
    }
}