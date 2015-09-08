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
        tabPane.getTabs().add(new DemoTab("Text ", "text2.fxml"));

        Scene scene = new Scene(tabPane);
        scene.getStylesheets().addAll(
            "bootstrapfx.css",
            "org/kordamp/bootstrapfx/sampler.css",
            "org/kordamp/bootstrapfx/xml-highlighting.css");

        primaryStage.setTitle("BootstrapFX Sampler");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
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
