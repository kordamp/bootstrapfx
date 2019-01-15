/*
 * SPDX-License-Identifier: MIT
 *
 * Copyright (c) 2015-2019 Andres Almiray
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.kordamp.bootstrapfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Florian Kirmaier
 */
public class SamplerJPro extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane innerPane = new StackPane(new DemoTabPane());
        innerPane.setMaxWidth(1000);
        innerPane.setMaxHeight(600);
        innerPane.setStyle("-fx-background-color: #ddd; -fx-background-radius: 10;");
        StackPane outerPane = new StackPane(innerPane);
        outerPane.setStyle("-fx-background-image: url('/org/kordamp/bootstrapfx/ambient-background.jpg');" +
                "-fx-background-size: cover;");

        Scene scene = new Scene(outerPane);
        scene.getStylesheets().addAll(
            "org/kordamp/bootstrapfx/bootstrapfx.css",
            "org/kordamp/bootstrapfx/sampler.css",
            "org/kordamp/bootstrapfx/xml-highlighting.css");

        primaryStage.setTitle("BootstrapFX Sampler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
