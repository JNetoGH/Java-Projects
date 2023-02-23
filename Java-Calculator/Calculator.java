import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application {

    private static double firstTerm = 0, secondTerm = 0, result = 0;
    private static char op = '?';
    private static String historyContent = "History:";

    @Override
    public void start(Stage primaryStage) throws Exception {

        // root layout
        VBox mainLayout = new VBox();


        // top displayable areas
        HBox displayableAreas = new HBox();
        displayableAreas.setAlignment(Pos.CENTER);
        // both displayable areas
        TextArea display = new TextArea();
        display.setFont(new Font(28));
        display.setEditable(true);
        display.setPrefHeight(GeneralResources.HEIGHT * 0.25); // 25% da altura da tela
        display.setPrefWidth(GeneralResources.WIDTH/5 * 4); // 4/5 80% da tela

        TextArea history = new TextArea();
        history.setFont(new Font(display.getFont().getSize()/5*2)); // 2/5 da fonte da anterior
        history.setEditable(false);
        history.setPrefHeight(display.getPrefHeight());
        history.setPrefWidth(GeneralResources.WIDTH/5*1); //1/5 da tela

        displayableAreas.getChildren().addAll(display, history);


        // carries optionLayout and numbersLayout
        VBox buttonsLayout = new VBox();
        // both will be added to buttonsLayout
        HBox optionLayout = new HBox();
        TilePane numbersLayout = new TilePane();
        buttonsLayout.getChildren().addAll(optionLayout, numbersLayout);

        // optionLayout implementation
        optionLayout.setPadding(new Insets(GeneralResources.BUTTONS_GAP, GeneralResources.BUTTONS_GAP+5,0, 0));
        optionLayout.setSpacing(GeneralResources.BUTTONS_GAP);
        optionLayout.setAlignment(Pos.CENTER_RIGHT);
        Button[] opButtons = {new Button("C"), new Button("<")};
        for (Button b: opButtons) optionLayout.getChildren().add(b);
        opButtons[0].setOnAction(event -> {
            display.clear();
            firstTerm = 0; //limpa as variáveis
            secondTerm = 0;
            result = 0;
            op = '?';
        });
        opButtons[1].setOnAction(event -> display.setText(display.getText().substring(0, display.getLength()-1)));

        // numbersLayout implementation
        numbersLayout.setVgap(GeneralResources.BUTTONS_GAP);
        numbersLayout.setHgap(GeneralResources.BUTTONS_GAP);
        numbersLayout.setAlignment(Pos.CENTER);
        numbersLayout.setPadding(new Insets(10,0,0,0));
        //                        0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19
        String[] buttonsTexts = {"1","2","3","+","-","4","5","6","x","÷","7","8","9","^","√",".","0","=","%","F"};
        Button[] buttons = new Button[buttonsTexts.length];

        // adiciona ação para todos os botões dos números e operações e os pões no Numbers Layout
        for (int i = 0; i < buttonsTexts.length ; i++) {
            int finalI = i; // precisa dessa merda pra usar as lambdas expressions
            buttons[i] = new Button(buttonsTexts[i]);
            buttons[i].setFont(GeneralResources.BUTTONS_FONT);
            buttons[i].setPrefWidth(GeneralResources.BUTTONS_WIDTH); // 4 botoes por linha - margem de erro pq o botão é um pouquinho maior
            buttons[i].setPrefHeight(GeneralResources.BUTTONS_HEIGHT);
            numbersLayout.getChildren().add(buttons[i]);
            // escreve os números e o ponto na tela
            if (i!=3 && i!=4 && i!=8 && i!=9 && i!=13 && i!=14 && i!=17 && i!=18 && i!=19) {
                buttons[i].setOnAction(event -> display.setText(display.getText() + buttons[finalI].getText()));
            }
            else if (i!=17) { // se n for "=" executa as operacoes de 1 termo e prepara terreno para as de 2
                buttons[i].setOnAction(event -> {

                    // add a minus to negative number if the display is empty in case of button 4 has been selected
                    if (display.getText().isEmpty() && finalI == 4) {
                        display.setText(buttons[finalI].getText());
                    }
                    else {  // it will just allow a attribution of the value if the txt area is not empty
                        if (! display.getText().isEmpty()) {
                            firstTerm = Double.parseDouble(display.getText());
                            history.setText(history.getText() + "\n" + GeneralResources.checkIfIsIntAndCutZerosOff(firstTerm));
                        }
                        display.clear();
                        switch (finalI) {
                            case 3: op = '+'; break;
                            case 4: op = '-'; break;
                            case 8: op = 'x'; break;
                            case 9: op = '÷'; break;
                            case 13: op = '^'; break;
                            case 18: op = '%'; break;
                            case 19:
                                op = 'F';
                                if (firstTerm >= 0) {
                                    result = GeneralResources.fibonacci((int)firstTerm);
                                    history.setText(history.getText() + "\n" + op + "\n" + (int) result + "\n");
                                    display.setText((int)result + "\n" + "F" + (int)firstTerm + " = " + (int)result);
                                } else {
                                    display.setText("ERROR: number < 0");
                                }
                                firstTerm = 0; //limpa as variáveis
                                secondTerm = 0;
                                result = 0;
                                break;
                            case 14:
                                op = '√';
                                result = Math.sqrt(firstTerm);
                                history.setText(history.getText() + "\n" + op + "\n" + GeneralResources.checkIfIsIntAndCutZerosOff(result) + "\n");
                                display.setText(GeneralResources.checkIfIsIntAndCutZerosOff(result) + "\n" + "√" +
                                        GeneralResources.checkIfIsIntAndCutZerosOff(firstTerm) + " = " +
                                        GeneralResources.checkIfIsIntAndCutZerosOff(result));
                                firstTerm = 0; //limpa as variáveis
                                secondTerm = 0;
                                result = 0;
                                break;
                        }
                        if (op != 'F' && op != '√') history.setText(history.getText() + "\n" + op); // o excluidos eu fiz especial
                    }
                });
            }
            else { // se for "=" 17 executa as operacoes de 2 termos
                buttons[i].setOnAction(event -> {

                    secondTerm = Double.parseDouble(display.getText());

                    switch (op) {
                        case '+': result = firstTerm + secondTerm; break; // +
                        case '-': result = firstTerm - secondTerm; break; // -
                        case 'x': result = firstTerm * secondTerm; break; // x
                        case '÷': result = firstTerm / secondTerm; break; // /
                        case '^': result = Math.pow(firstTerm, secondTerm); break; // ^
                        case '%': result = secondTerm * (firstTerm/100); break; // ^
                    }

                    history.setText(history.getText() + "\n" +
                            GeneralResources.checkIfIsIntAndCutZerosOff(secondTerm) + "\n=\n" +
                            GeneralResources.checkIfIsIntAndCutZerosOff(result) + "\n");

                    display.clear();
                    display.setText(GeneralResources.checkIfIsIntAndCutZerosOff(result) + "\n" +
                            GeneralResources.checkIfIsIntAndCutZerosOff(firstTerm) + " " + op + " " +
                            GeneralResources.checkIfIsIntAndCutZerosOff(secondTerm) + " = " +
                            GeneralResources.checkIfIsIntAndCutZerosOff(result));

                    firstTerm = 0; // limpa as variáveis
                    secondTerm = 0;
                    result = 0;
                    op = '?';

                });
            }
        }

        // add the layout to the root layout and init the stage
        mainLayout.getChildren().addAll(displayableAreas, buttonsLayout);
        Scene mainScene = new Scene(mainLayout);
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(GeneralResources.WIDTH);
        primaryStage.setHeight(GeneralResources.HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Mendes Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
