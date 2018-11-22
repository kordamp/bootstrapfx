package org.kordamp.bootstrapfx;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import org.apache.commons.io.IOUtils;

import java.net.URL;

public class DemoTabPane extends StackPane {

    public DemoTabPane() throws Exception {
        URL location = getClass().getResource("sampler.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        TabPane tabPane = fxmlLoader.load();

        tabPane.getTabs().add(new DemoTab("Buttons", "buttons.fxml"));
        tabPane.getTabs().add(new DemoTab("Labels", "labels.fxml"));
        tabPane.getTabs().add(new DemoTab("Alerts", "alerts.fxml"));
        tabPane.getTabs().add(new DemoTab("Panels", "panels.fxml"));
        tabPane.getTabs().add(new DemoTab("Headings", "text.fxml"));
        tabPane.getTabs().add(new DemoTab("Progress Bars", "progressbars.fxml"));
        tabPane.getTabs().add(new DemoTab("Tooltips ", "tooltips.fxml"));
        tabPane.getTabs().add(new DemoTab("Text ", "text2.fxml"));
        tabPane.getTabs().add(new DemoTab("Paragraph ", "paragraph.fxml"));
        tabPane.getTabs().add(new DemoTab("Button Groups ", "button_groups.fxml"));
        tabPane.getTabs().add(new DemoTab("SplitMenuButtons", "split_menu_buttons.fxml"));

        getChildren().add(tabPane);
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

            String text = IOUtils.toString(getClass().getResourceAsStream(sourceFile));
            editor.setText(text);
            source.setContent(editor);

            content.getTabs().addAll(widgets, source);
        }
    }
}
