package com.yyil.noteapp.ui

import  javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Font

class UiComponent {
    val base = HBox()
    val noteArea = VBox()
    val leftMenu = HBox()

    val toolBar = ToolBar()
    val toolBarButton = ToolBarButton()
    val toolBarLeft = HBox()
    val toolBarRight = HBox()

    val textArea = TextArea()
    val textScroll = ScrollPane()

    val leftList = ListView<String>()
    val listScroll = ScrollPane()
    val showListButton = Button("<")

    val defaultFont = "Helvetica"
    val defaultFontSize = 12.0

    constructor()

    private fun initToolBar(){
        HBox.setHgrow(toolBarLeft, Priority.ALWAYS)
        HBox.setHgrow(toolBarRight, Priority.ALWAYS)

        toolBar.items.add(toolBarLeft)
        toolBarButton.addToToolBar(toolBar)
        toolBar.items.add(toolBarRight)
    }

    private fun initTextArea(){
        textArea.font = Font(defaultFont, defaultFontSize)
        textArea.wrapTextProperty().set(true)

        textScroll.content = textArea
    }

    private fun  initLeftList(){
        var leftListItems = FXCollections.observableArrayList (
            "Note1", "Note2", "Note3", "Note4")
        leftList.items = leftListItems

        listScroll.content = leftList

        showListButton.prefHeight = Double.MAX_VALUE
        showListButton.minWidth = 25.0

        val showListHandler = EventHandler{
                event : MouseEvent ->
            println("Button Clicked (" + event.x + "," + event.y + ")")
            leftList.isVisible = !leftList.isVisible
            leftList.isManaged = !leftList.isManaged
            showListButton.text = if(showListButton.text == "<") ">" else "<"
        }
        showListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)
    }

    fun init(){
        initToolBar()
        initTextArea()
        initLeftList()

        leftMenu.children.add(leftList)
        leftMenu.children.add(showListButton)
        leftMenu.fillHeightProperty().set(true)

        noteArea.children.add(toolBar)
        noteArea.children.add(textArea)
        VBox.setVgrow(textArea, Priority.ALWAYS)
        noteArea.fillWidthProperty().set(true)

        base.children.add(leftMenu)
        base.children.add(noteArea)
        HBox.setHgrow(noteArea, Priority.ALWAYS)
        base.fillHeightProperty().set(true)
    }
}
