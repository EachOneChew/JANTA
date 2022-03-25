package com.yyil.noteapp.mvc.controller

import com.yyil.noteapp.mvc.model.Model
import com.yyil.noteapp.mvc.view.LabelList
import javafx.collections.transformation.FilteredList
import javafx.collections.transformation.SortedList
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import java.util.function.Predicate

class LabelListController(
    val model: Model,
    labelList: LabelList
) {
    init {
        labelList.labelList.items = FilteredList(SortedList(model.listLabel, Comparator { o1, o2 ->  o1.compareTo(o2) }))

        labelList.labelList.onMouseClicked = EventHandler {
            val i = labelList.labelList.selectionModel.selectedIndex
            println("clicked with index $i")
            model.handleLabelNav(i)
        }

        labelList.searchBar.onKeyPressed = EventHandler {
            if (it.code == KeyCode.ENTER) {
                val s = labelList.searchBar.text
                if (s.isBlank()) {
                    (labelList.labelList.items as FilteredList<String>).predicate = null
                }
                else {
                    (labelList.labelList.items as FilteredList<String>).predicate = Predicate { t -> t.contains(s) }
                }
            }
        }
    }
}