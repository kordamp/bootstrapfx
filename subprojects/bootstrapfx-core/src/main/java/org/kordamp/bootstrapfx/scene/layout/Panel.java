/*
 * Copyright (c) 2015-2016 Andres Almiray
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
package org.kordamp.bootstrapfx.scene.layout;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Panel extends BorderPane {
    private ObjectProperty<Node> heading;
    private ObjectProperty<Node> body;
    private ObjectProperty<Node> footer;

    public Panel() {
        getStyleClass().setAll("panel");
    }

    public Panel(String title) {
        this();
        Label label = new Label(title);
        label.getStyleClass().add("panel-title");
        setHeading(label);
    }

    public final ObjectProperty<Node> headingProperty() {
        if (this.heading == null) {
            this.heading = new SimpleObjectProperty<>(this, "heading");
            this.heading.addListener((v, o, n) -> {
                if (null != n) {
                    GridPane box = new GridPane();
                    box.getStyleClass().setAll("panel-heading");
                    GridPane.setColumnIndex(n, 0);
                    GridPane.setRowIndex(n, 0);
                    GridPane.setHgrow(n, Priority.ALWAYS);
                    GridPane.setVgrow(n, Priority.ALWAYS);
                    box.getChildren().add(n);
                    setTop(box);
                }
            });
        }
        return this.heading;
    }

    public final void setHeading(Node content) {
        this.headingProperty().set(content);
    }

    public final Node getHeading() {
        return this.heading == null ? null : this.heading.get();
    }

    public final ObjectProperty<Node> bodyProperty() {
        if (this.body == null) {
            this.body = new SimpleObjectProperty<>(this, "body");
            this.body.addListener((v, o, n) -> {
                if (null != n) {
                    GridPane box = new GridPane();
                    box.getStyleClass().setAll("panel-body");
                    GridPane.setColumnIndex(n, 0);
                    GridPane.setRowIndex(n, 0);
                    GridPane.setHgrow(n, Priority.ALWAYS);
                    GridPane.setVgrow(n, Priority.ALWAYS);
                    box.getChildren().add(n);
                    setCenter(box);
                }
            });
        }
        return this.body;
    }

    public final void setBody(Node body) {
        this.bodyProperty().set(body);
    }

    public final Node getBody() {
        return this.body == null ? null : this.body.get();
    }

    public final ObjectProperty<Node> footerProperty() {
        if (this.footer == null) {
            this.footer = new SimpleObjectProperty<>(this, "footer");
            this.footer.addListener((v, o, n) -> {
                if (null != n) {
                    GridPane box = new GridPane();
                    box.getStyleClass().setAll("panel-footer");
                    GridPane.setColumnIndex(n, 0);
                    GridPane.setRowIndex(n, 0);
                    GridPane.setHgrow(n, Priority.ALWAYS);
                    GridPane.setVgrow(n, Priority.ALWAYS);
                    box.getChildren().add(n);
                    setBottom(box);
                }
            });
        }
        return this.footer;
    }

    public final void setFooter(Node content) {
        this.footerProperty().set(content);
    }

    public final Node getFooter() {
        return this.footer == null ? null : this.footer.get();
    }

    public void setText(String text) {
        headingProperty().set(new Label(text));
    }

    public String getText() {
        Node node = headingProperty().get();
        if (node instanceof Labeled) {
            return ((Labeled) node).getText();
        }
        return null;
    }
}
