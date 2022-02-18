package com.yyil.noteapp.component

import com.yyil.noteapp.constant.ComponentConstant
import javafx.collections.FXCollections
import javafx.concurrent.Worker
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.scene.web.WebEngine
import javafx.scene.web.WebEvent
import javafx.scene.web.WebView
import netscape.javascript.JSObject


class TempComponent {
    val base = HBox()
    val noteArea = VBox()
    val leftMenu = HBox()

//    val toolBar = ToolBar()
//    val toolBarButton = ToolBarButton()
//    val toolBarLeft = HBox()
//    val toolBarRight = HBox()

    val textArea = WebView()
    val webEngine: WebEngine = textArea.engine
    val url: String = javaClass.classLoader.getResource(ComponentConstant.EDITOR_FILE)?.toExternalForm() ?: "N/A"
    lateinit var tinyMCE: JSObject

    val leftList = ListView<String>()
    val listScroll = ScrollPane()
    val showListButton = Button("<")

    var testTextSync = Text("init")
    val testTextScroll = ScrollPane(testTextSync)

//    private fun initToolBar(){
//        HBox.setHgrow(toolBarLeft, Priority.ALWAYS)
//        HBox.setHgrow(toolBarRight, Priority.ALWAYS)
//
//        toolBar.items.add(toolBarLeft)
//        toolBarButton.addToToolBar(toolBar)
//        toolBar.items.add(toolBarRight)
//    }

    private fun initTextArea() {
        webEngine.load(url)
        webEngine.onAlert = EventHandler<WebEvent<String>> { e ->
            testTextSync.text = e.data
        }

        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                tinyMCE = (webEngine.executeScript(ComponentConstant.TINYMCE_SCRIPT) as JSObject)
                    .getMember("activeEditor") as JSObject
            }
        }
    }

    private fun initLeftList() {
        val leftListItems = FXCollections.observableArrayList(
            "Note1", "Note2", "Note3", "Note4"
        )
        leftList.items = leftListItems

        leftList.onMouseClicked = EventHandler {
            val i = leftList.selectionModel.selectedIndex
            val tempContent = FXCollections.observableArrayList(
                "You have opened Note1!", "Note2 Lorem Ipsum", "Note3 Huak Huak Huak", "Note4 READING WEAEK SOON"
            )
            tinyMCE.call("setContent", tempContent[i])
        }

        listScroll.content = leftList

        showListButton.prefHeight = Double.MAX_VALUE
        showListButton.minWidth = ComponentConstant.MIN_BUTTON_WIDTH
        showListButton.style = "-fx-background-radius: 0"

        val showListHandler = EventHandler{
                event : MouseEvent ->
            println("Button Clicked (" + event.x + "," + event.y + ")")
            leftList.isVisible = !leftList.isVisible
            leftList.isManaged = !leftList.isManaged
            showListButton.text = if(showListButton.text == "<") ">" else "<"
        }
        showListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, showListHandler)
    }

    fun init() {
//        initToolBar()
        initTextArea()
        initLeftList()

        leftMenu.children.add(leftList)
        leftMenu.children.add(showListButton)
        leftMenu.fillHeightProperty().set(true)

        noteArea.children.add(textArea)
        noteArea.children.add(testTextScroll)

        base.children.add(leftMenu)
        base.children.add(noteArea)
        HBox.setHgrow(noteArea, Priority.ALWAYS)
        base.fillHeightProperty().set(true)
    }
}
