package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    private double firstNumber = 0;
    private String operator = "";

    @Override
    public void start(Stage stage) {

        Image icon = new Image("Calculator.png");
        stage.getIcons().add(icon);

        TextField display = new TextField();
        display.setEditable(false);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Button[] numbers = new Button[10];
        for (int i = 0; i <= 9; i++) {
            numbers[i] = new Button(String.valueOf(i));
        }

        Button add = new Button("+");
        Button sub = new Button("-");
        Button mul = new Button("*");
        Button div = new Button("/");
        Button eq = new Button("=");
        Button clear = new Button("C");
        Button dot = new Button(".");

        int num = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid.add(numbers[num], col, row);
                num++;
            }
        }

        grid.add(numbers[0], 1, 3);
        grid.add(dot, 0, 3);
        grid.add(eq, 2, 3);
        grid.add(add, 3, 0);
        grid.add(sub, 3, 1);
        grid.add(mul, 3, 2);
        grid.add(div, 3, 3);
        grid.add(clear, 0, 4);

        for (int i = 0; i <= 9; i++) {
            int digit = i;
            numbers[i].setOnAction(e -> {
                display.setText(display.getText() + digit);
            });
        }

        dot.setOnAction(e -> {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        });

        add.setOnAction(e -> setOperator(display, "+"));
        sub.setOnAction(e -> setOperator(display, "-"));
        mul.setOnAction(e -> setOperator(display, "*"));
        div.setOnAction(e -> setOperator(display, "/"));

        eq.setOnAction(e -> {
            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;

            if (operator.equals("+")) {
                result = firstNumber + secondNumber;
            } else if (operator.equals("-")) {
                result = firstNumber - secondNumber;
            } else if (operator.equals("*")) {
                result = firstNumber * secondNumber;
            } else if (operator.equals("/")) {

                if (secondNumber == 0) {
                    display.setText("Error");
                    return;
                }
                result = firstNumber / secondNumber;
            }
            display.setText(String.valueOf(result));
        });

        clear.setOnAction(e -> {
            display.clear();
            firstNumber = 0;
            operator = "";
        });

        VBox root = new VBox(10, display, grid);

        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void setOperator(TextField display, String op) {
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        display.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}