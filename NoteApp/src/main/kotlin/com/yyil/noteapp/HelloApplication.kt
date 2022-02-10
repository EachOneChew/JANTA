package com.yyil.noteapp

import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.stage.StageStyle


class HelloApplication : Application() {

    var windowW = 800.0
    var windowH = 600.0

    override fun start(stage: Stage) {

        val toolbar = ToolBar()
        var buttonList = mutableListOf<Button>()
        var button1 = Button("Button1")
        buttonList.add(button1)
        var button2 = Button("Button2")
        buttonList.add(button2)
        var button3 = Button("Button3")
        buttonList.add(button3)
        toolbar.items.addAll(buttonList)
        toolbar.maxWidth = Double.MAX_VALUE

        var textArea = TextArea()
        textArea.font = Font("Helvetica", 12.0)
        val textScroll = ScrollPane()
        textScroll.content = textArea
        textScroll.hmax = 0.0

        val leftList = ListView<String>()
        var leftListItems = FXCollections.observableArrayList (
                "Note1", "Note2", "Note3", "Note4")
        leftList.items = leftListItems
        val listScroll = ScrollPane()
        listScroll.content = leftList

        val showListButton = Button("<")
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

        var leftMenu = HBox(leftList, showListButton)
        var noteArea = VBox(toolbar, textArea)
        VBox.setVgrow(textArea, Priority.ALWAYS)
        var base = HBox(leftMenu, noteArea)
        HBox.setHgrow(noteArea, Priority.ALWAYS)

        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, EventHandler {
            println("leftMenu: "+leftMenu.width +","+leftMenu.height)
            println("noteArea: "+noteArea.width +","+noteArea.height)
            println("base: "+base.width +","+base.height)
        })

        leftMenu.fillHeightProperty().set(true)
        noteArea.fillWidthProperty().set(true)
        base.fillHeightProperty().set(true)

        stage.scene = Scene(base, windowW, windowH)
        stage.heightProperty().addListener { observable, oldval, newval -> {
        } }
        stage.widthProperty().addListener { observable, oldval, newval -> {

        } }

        stage.title = "Note Taking App"
        //stage.initStyle(StageStyle.UNDECORATED)
        stage.show()
    }
}
