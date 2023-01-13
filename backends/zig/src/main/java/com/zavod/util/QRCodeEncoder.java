package com.zavod.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.qrcode.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeEncoder {

    public static ResponseEntity<byte[]> generateQRCodeImage(String url) throws WriterException, IOException, BadElementException {
        BarcodeQRCode qrCode = new BarcodeQRCode(url, 200, 200, null);

        java.awt.Image awtImage = qrCode.createAwtImage(Color.BLACK, Color.WHITE);
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(awtImage, 0, 0, null);
        bufferedImage.getGraphics().dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

}
