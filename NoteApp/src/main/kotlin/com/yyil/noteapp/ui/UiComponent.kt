package com.yyil.noteapp.ui

import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Font

class UiComponent {
    val leftMenu = HBox()
    val noteArea = VBox()
    val base = HBox()

    val toolBar = ToolBar()
    val button1 = Button("Button1")
    val button2 = Button("Button2")
    val button3 = Button("Button3")
    //var buttonList = mutableListOf<Button>()

    val textArea = TextArea()
    val textScroll = ScrollPane()

    val leftList = ListView<String>()
    val listScroll = ScrollPane()
    val showListButton = Button("<")

    val defaultFont = "Helvetica"
    val defaultFontSize = 12.0

    constructor()

    private fun initToolBar(){
        toolBar.items.add(button1)
        toolBar.items.add(button2)
        toolBar.items.add(button3)
    }

    private fun initTextArea(){
        textArea.font = Font(defaultFont, defaultFontSize)
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
        noteArea.widthProperty().addListener { observable, oldValue, newValue ->  }

        base.children.add(leftMenu)
        base.children.add(noteArea)
        HBox.setHgrow(noteArea, Priority.ALWAYS)
        base.fillHeightProperty().set(true)
    }
}