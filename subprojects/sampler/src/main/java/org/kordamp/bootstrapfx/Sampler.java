/*
 * Copyright (c) 2015-2018 Andres Almiray
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.joining;

public class Sampler extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("sampler.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        TabPane tabPane = fxmlLoader.load();

        tabPane.getTabs().add(new DemoTab("Buttons", "buttons.fxml"));
        tabPane.getTabs().add(new DemoTab("Labels", "labels.fxml"));
        tabPane.getTabs().add(new DemoTab("Alerts", "alerts.fxml"));
        tabPane.getTabs().add(new DemoTab("Panels", "panels.fxml"));
        tabPane.getTabs().add(new DemoTab("Headings", "text.fxml"));
        tabPane.getTabs().add(new DemoTab("Progress Bars", "progressbars.fxml"));
        tabPane.getTabs().add(new DemoTab("Text ", "text2.fxml"));
        tabPane.getTabs().add(new DemoTab("Paragraph ", "paragraph.fxml"));
        tabPane.getTabs().add(new DemoTab("Button Groups ", "button_groups.fxml"));
        tabPane.getTabs().add(new DemoTab("SplitMenuButtons", "split_menu_buttons.fxml"));

        Scene scene = new Scene(tabPane);
        scene.getStylesheets().addAll(
            "bootstrapfx.css",
            "org/kordamp/bootstrapfx/sampler.css",
            "org/kordamp/bootstrapfx/xml-highlighting.css");

        primaryStage.setTitle("BootstrapFX Sampler");
        primaryStage.setScene(scene);
        //primaryStage.sizeToScene();
        primaryStage.setWidth(1024);
        primaryStage.show();
    }

    private static class DemoTab extends Tab {
        private DemoTab(String title, String sourceFile) throws Exception {
            super(title);
            setClosable(false);

            TabPane content = new TabPane();
            setContent(content);
            content.setSide(Side.BOTTOM);

            Tab widgets = new Tab("Widgets");
            widgets.setClosable(false);
            URL location = getClass().getResource(sourceFile);
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Node node = fxmlLoader.load();
            widgets.setContent(node);

            Tab source = new Tab("Source");
            source.setClosable(false);
            XMLEditor editor = new XMLEditor();
            editor.setEditable(false);

            String text = lines(Paths.get(getClass().getResource(sourceFile).toURI())).collect(joining("\n"));
            editor.setText(text);
            source.setContent(editor);

            content.getTabs().addAll(widgets, source);
        }
    }
}
