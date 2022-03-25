package com.yyil.noteapp.svg

import com.yyil.noteapp.NoteApplication
import com.yyil.noteapp.constant.ComponentConstant
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.ImageTranscoder
import java.awt.image.BufferedImage


object BufferedImageTranscoder : ImageTranscoder() {
    var bufferedImage: BufferedImage? = null

    override fun createImage(width: Int, height: Int): BufferedImage {
        return BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    }

    override fun writeImage(img: BufferedImage?, to: TranscoderOutput?) {
        bufferedImage = img
    }

    fun transcodeToImage(path : String): WritableImage? {
        val transIn = TranscoderInput(path)
        transcode(transIn, null);
        var img = SwingFXUtils.toFXImage(bufferedImage, null)
        return img
    }

    fun transcodeToImageView(path: String) : ImageView {
        var icon = ImageView(transcodeToImage(path))
        icon.fitWidth = ComponentConstant.BUTTON_ICON_WIDTH
        icon.fitHeight = ComponentConstant.BUTTON_ICON_HEIGHT
        icon.isPreserveRatio = true
        return icon
    }
}