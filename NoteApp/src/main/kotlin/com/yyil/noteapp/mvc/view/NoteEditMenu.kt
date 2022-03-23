package com.yyil.noteapp.mvc.view

import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem

class NoteEditMenu(var noteCell: NoteCell) : ContextMenu() {
    var renameOption = MenuItem("Rename")
    var deleteOption = MenuItem("Delete")
    init {
        items.addAll(renameOption, deleteOption)
    }
}