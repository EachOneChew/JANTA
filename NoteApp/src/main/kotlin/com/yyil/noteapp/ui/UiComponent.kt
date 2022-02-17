package com.yyil.noteapp.ui

import  javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane

import javafx.scene.web.WebView
import javafx.scene.web.WebEngine

class UiComponent {
    val base = HBox()
    val noteArea = StackPane()
    val leftMenu = HBox()

//    val toolBar = ToolBar()
//    val toolBarButton = ToolBarButton()
//    val toolBarLeft = HBox()
//    val toolBarRight = HBox()

    val textArea = WebView()
    val webEngine: WebEngine = textArea.engine
    val url: String = javaClass.classLoader.getResource("editor.html").toExternalForm()

    val leftList = ListView<String>()
    val listScroll = ScrollPane()
    val showListButton = Button("<")


//    private fun initToolBar(){
//        HBox.setHgrow(toolBarLeft, Priority.ALWAYS)
//        HBox.setHgrow(toolBarRight, Priority.ALWAYS)
//
//        toolBar.items.add(toolBarLeft)
//        toolBarButton.addToToolBar(toolBar)
//        toolBar.items.add(toolBarRight)
//    }

    private fun initTextArea(){
        webEngine.load(url)
    }

    private fun  initLeftList(){
        val leftListItems = FXCollections.observableArrayList (
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
//        initToolBar()
        initTextArea()
        initLeftList()

        leftMenu.children.add(leftList)
        leftMenu.children.add(showListButton)
        leftMenu.fillHeightProperty().set(true)

        noteArea.children.add(textArea)

        base.children.add(leftMenu)
        base.children.add(noteArea)
        HBox.setHgrow(noteArea, Priority.ALWAYS)
        base.fillHeightProperty().set(true)
    }
}
