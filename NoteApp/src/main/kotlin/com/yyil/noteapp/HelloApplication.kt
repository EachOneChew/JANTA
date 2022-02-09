package com.yyil.noteapp

import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.control.ToolBar
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage


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

        var textArea = Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
        textArea.font = Font("Helvetica", 12.0)
        textArea.wrappingWidth = 550.0
        val textScroll = ScrollPane()
        textScroll.content = textArea

        val leftList = ListView<String>()
        var leftListItems = FXCollections.observableArrayList (
                "Note1", "Note2", "Note3", "Note4")
        leftList.items = leftListItems
        val listScroll = ScrollPane()
        listScroll.content = leftList

        val showListButton = Button(">")
        showListButton.prefHeight = Double.MAX_VALUE
        showListButton.minWidth = 25.0
        val showListHandler = EventHandler{
                event : MouseEvent ->
            println("Button Clicked (" + event.x + "," + event.y + ")")
            println("Button Size (" + showListButton.width + "," + showListButton.height + ")")
            leftList.isVisible = !leftList.isVisible
            leftList.isManaged = !leftList.isManaged
        }
        showListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)

        var leftMenu = HBox(leftList, showListButton)
        var noteArea = VBox(toolbar, textScroll)
        var base = HBox(leftMenu, noteArea)

        leftMenu.fillHeightProperty().set(true)
        noteArea.fillWidthProperty().set(true)
        base.fillHeightProperty().set(true)

        stage.scene = Scene(base, windowW, windowH)
        stage.heightProperty().addListener { observable, oldval, newval -> {
            toolbar.prefWidth = noteArea.width
        } }
        stage.widthProperty().addListener { observable, oldval, newval -> {

        } }

        stage.title = "Note Taking App"
        stage.show()

    }
}
