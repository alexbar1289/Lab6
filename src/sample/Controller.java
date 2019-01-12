package sample;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.AccessibleAttribute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;

public class Controller {
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;

    @FXML
    private TableView tableView1;
    @FXML
    private TableView tableView2;
    @FXML
    private TableView tableView3;

    private int c = 0;
    private int r = 0;
    @FXML
    public void initialize(){
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TableColumn col_1 = new TableColumn();
                tableView1.getColumns().add(col_1);
                col_1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.15));
                col_1.setResizable(false);
                col_1.setCellFactory(TextFieldTableCell.forTableColumn());
                TableColumn col_2 = new TableColumn();
                tableView2.getColumns().add(col_2);
                col_2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.15));
                col_2.setResizable(false);
                col_2.setCellFactory(TextFieldTableCell.forTableColumn());

                c = c + 1;
            }
        });

        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add("RowTV1");
                tableView1.getItems().add(row);

                ObservableList<String> row2 = FXCollections.observableArrayList();
                row2.add("RowTV2");
                tableView2.getItems().add(row2);

                r = r + 1;
            }
        });

        button3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //+++++++++++++++++++++++++++++++++++++++++++++++++++Матрицу первую в Map из тэйблвью 1
                Map<Integer, Double> matrix1 = new HashMap<Integer, Double>();//матрица 1
                int count1 = 0;
                for(int j = 0; j < c; j++) {
                    for (int i = 0; i < r; i++) {
                        TextFieldTableCell yach = (TextFieldTableCell)tableView1.queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN, i, j);
                        matrix1.put(count1, Double.parseDouble(yach.getText()));
                        count1 = count1 + 1;
                    }
                }
                //+++++++++++++++++++++++++++++++++++++++++++++++++++Матрицу вторую в Map из  тэйблвью 2
                Map<Integer, Double> matrix2 = new HashMap<Integer, Double>();//матрица 2
                int count2 = 0;
                for(int w = 0; w < c; w++) {
                    for (int t = 0; t < r; t++) {
                        TextFieldTableCell yach = (TextFieldTableCell)tableView2.queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN, t, w);
                        matrix2.put(count2, Double.parseDouble(yach.getText()));
                        count2 = count2 + 1;
                    }
                }
                //+++++++++++++++++++++++++++++++++++++++++++++++++++Получаем матрицу 3, сложив 1 и 2 матрицы
                Matrix mx = new Matrix();
                Map<Integer, String> matrix3 = mx.education(matrix1, matrix2);
                //++++++++++++++++++++++++++++++++++++++++++++++++Перекинул элементы матрицы из Map в двумерный строковый массив
                r = r + 1;
                String[][] dataArray  = new String[r][c];
                for (int g = 0; g < c; g++){
                    dataArray[0][g] = " ";
                }
                int d = 0;
                for (int j = 0; j < c; j++) {
                    for (int i = 1; i < r; i++) {
                        dataArray[i][j] = matrix3.get(d);
                        d = d + 1;
                    }
                }
                //+++++++++++++++++++++++++++++++++++++++++++++++++++Вывод в тэйблвью
                ObservableList<String[]> observableList = FXCollections.observableArrayList();
                observableList.addAll(Arrays.asList(dataArray));
                observableList.remove(0);
                TableView<String[]> tableView = new TableView<>();
                for (int i = 0; i < dataArray[0].length; i++) {
                    TableColumn tableColumn = new TableColumn(" ");
                    tableColumn.prefWidthProperty().bind(tableView3.widthProperty().multiply(0.15));
                    tableColumn.setResizable(false);
                    int columnNumber = i;
                    tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                            return new SimpleStringProperty((p.getValue()[columnNumber]));
                        }
                    });
                    tableView3.getColumns().add(tableColumn);
                }
                tableView3.setItems(observableList);
                //+++++++++++++++++++++++++++++++++++++++++++++++++++
            }
        });
    }

}
