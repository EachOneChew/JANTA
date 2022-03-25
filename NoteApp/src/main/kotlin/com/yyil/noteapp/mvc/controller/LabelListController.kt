package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.LabelList
import javafx.event.EventHandler

class LabelListController(
    val model: Model,
    labelList: LabelList
) {
    init {
        labelList.labelList.onMouseClicked = EventHandler {
            val i = labelList.labelList.selectionModel.selectedIndex
            println("clicked with index $i")
            model.handleLabelNav(i)
        }

        labelList.labelList.items = model.listLabel
        labelList.clearButton.onMouseClicked = EventHandler {
            labelList.searchBar.text = ""
        }
    }
}