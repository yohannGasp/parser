package gui;

import config.BeenConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.baikalinvestbank.parser.*;
import static util.GrabScreen.screenCom;
import util.ToMsWord;
import util.elem;
import util.itemModel;

/**
 *
 * @author evgeniy
 *
 * -DurlStroytehRu="https://www.stroyteh.ru/sale/xml/"
 * -DurlGruzovikRu="https://www.gruzovik.ru/offers/trucks/refrigerator/"
 * -Dtarget_dir=/tmp
 *
 */
public class mainJFrame extends javax.swing.JFrame {

    public final static Logger log = Logger.getLogger(mainJFrame.class.getName());

    private String urlStroytehRu;
    private String urlGruzovikRu;
    private String userHome;
    private String browser;
    private long pause;
    private Proxy proxy;
    private String listPath;
    private String category;
    private String log_path;
    private String log_level;
    private String fileProxyList;

    public String parameters = "?";

    Set<String> grModel1 = new TreeSet<>();
    Map<String, List<itemModel>> grModel2 = new HashMap<>();

    int[] arr;

    ApplicationContext springContext;

    /**
     * Creates new form mainJFrame
     */
    @SuppressWarnings("empty-statement")
    public mainJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grModel1.add("Автобусы");
        grModel1.add("Грузовики");
        grModel1.add("Стройтехника");

        grModel2.put("Автобусы", Arrays.asList(
                new itemModel("Автодома", "https://www.gruzovik.ru/offers/passengers/autohouse/"),
                new itemModel("Вахтовые автобусы", "https://www.gruzovik.ru/offers/passengers/rotational-bus/"),
                new itemModel("Городские автобусы", "https://www.gruzovik.ru/offers/passengers/city-bus/"),
                new itemModel("Междугородные автобусы", "https://www.gruzovik.ru/offers/passengers/bus/"),
                new itemModel("Микроавтобусы", "https://www.gruzovik.ru/offers/passengers/minibus/"),
                new itemModel("Пригородные автобусы", "https://www.gruzovik.ru/offers/passengers/shuttle-bus/"),
                new itemModel("Другой пассажирский транспорт", "https://www.gruzovik.ru/offers/passengers/misc/"),
                new itemModel("Автолавки", "https://www.gruzovik.ru/offers/spectech/shop-trailer/"),
                new itemModel("Грузовики фургоны", "https://www.gruzovik.ru/offers/trucks/van/"),
                new itemModel("Грузопассажирские фургоны", "https://www.gruzovik.ru/offers/trucks/cargo-and-passenger/"),
                new itemModel("Жилые прицепы", "https://www.gruzovik.ru/offers/hookon/camper/")
        ));

        grModel2.put("Грузовики", Arrays.asList(
                new itemModel("Автовозы", "https://www.gruzovik.ru/offers/trucks/rack-car/"),
                new itemModel("Пищевые автоцистерны", "https://www.gruzovik.ru/offers/trucks/food-tank/"),
                new itemModel("Бензовозы", "https://www.gruzovik.ru/offers/trucks/gasoline-tanker/"),
                new itemModel("Бортовые грузовики", "https://www.gruzovik.ru/offers/trucks/onboard/"),
                new itemModel("Грузопассажирские фургоны", "https://www.gruzovik.ru/offers/trucks/cargo-and-passenger/"),
                new itemModel("Изотермические грузовики", "https://www.gruzovik.ru/offers/trucks/isothermal/"),
                new itemModel("Контейнеровозы", "https://www.gruzovik.ru/offers/trucks/container-truck/"),
                new itemModel("Лесовозы", "https://www.gruzovik.ru/offers/trucks/timber-truck/"),
                new itemModel("Грузовики-рефрижераторы", "https://www.gruzovik.ru/offers/trucks/refrigerator/"),
                new itemModel("Самопогрузчики", "https://www.gruzovik.ru/offers/trucks/selfloader/"),
                new itemModel("Самосвалы", "https://www.gruzovik.ru/offers/trucks/tipper/"),
                new itemModel("Скотовозы", "https://www.gruzovik.ru/offers/trucks/cattle-truck/"),
                new itemModel("Тентованные грузовики", "https://www.gruzovik.ru/offers/trucks/tilt/"),
                new itemModel("Топливозаправщики", "https://www.gruzovik.ru/offers/trucks/bowser/"),
                new itemModel("Трубовозы", "https://www.gruzovik.ru/offers/trucks/pipe-carrier/"),
                new itemModel("Грузовики-фургоны", "https://www.gruzovik.ru/offers/trucks/van/"),
                new itemModel("Шасси", "https://www.gruzovik.ru/offers/trucks/chassis/"),
                new itemModel("Другие грузовики", "https://www.gruzovik.ru/offers/trucks/misc/"),
                new itemModel("Бескапотные седельные тягачи", "https://www.gruzovik.ru/offers/truck-tractor/forward-control/"),
                new itemModel("Бетономиксеры", "https://www.gruzovik.ru/offers/constructions/concretemixer/"),
                new itemModel("Бетононасосы", "https://www.gruzovik.ru/offers/constructions/concretepump/"),
                new itemModel("Вездеходы", "https://www.gruzovik.ru/offers/spectech/all-terrain/"),
                new itemModel("Грузовики самосвалы-зерновозы", "https://www.gruzovik.ru/offers/agricult/grain-dump-truck/"),
                new itemModel("Капотные седельные тягачи", "https://www.gruzovik.ru/offers/truck-tractor/conventional/"),
                new itemModel("Карьерные самосвалы", "https://www.gruzovik.ru/offers/mining-machinery/pit-run-dumper/"),
                new itemModel("Легкие грузовики", "https://www.gruzovik.ru/offers/trucks-small/"),
                new itemModel("Лесовозы", "https://www.gruzovik.ru/offers/timber-machinery/timber-truck/"),
                new itemModel("Мини самосвалы", "https://www.gruzovik.ru/offers/mini/minitipper/"),
                new itemModel("Мусоровозы", "https://www.gruzovik.ru/offers/utility-machinery/garbage-truck/"),
                new itemModel("Средние грузовики", "https://www.gruzovik.ru/offers/trucks-average/"),
                new itemModel("Тяжёлые грузовики", "https://www.gruzovik.ru/offers/trucks-heavy/")
        ));

        grModel2.put("Стройтехника", Arrays.asList(
                new itemModel("Автокраны", "https://www.gruzovik.ru/offers/constructions/autocrane/"),
                new itemModel("Бетономиксеры", "https://www.gruzovik.ru/offers/constructions/concretemixer/"),
                new itemModel("Бетононасосы", "https://www.gruzovik.ru/offers/constructions/concretepump/"),
                new itemModel("Бульдозеры", "https://www.gruzovik.ru/offers/constructions/buldoser/"),
                new itemModel("Бурильно-сваебойные машины", "https://www.gruzovik.ru/offers/constructions/borepile/"),
                new itemModel("Буровые установки", "https://www.gruzovik.ru/offers/constructions/bore/"),
                new itemModel("Краны", "https://www.gruzovik.ru/offers/constructions/crane/"),
                new itemModel("Телескопические погрузчики", "https://www.gruzovik.ru/offers/constructions/teleloader/"),
                new itemModel("Трубоукладчики", "https://www.gruzovik.ru/offers/constructions/pipelayers/"),
                new itemModel("Фронтальные погрузчики", "https://www.gruzovik.ru/offers/constructions/frontloader/"),
                new itemModel("Экскаваторы", "https://www.gruzovik.ru/offers/constructions/excavator/"),
                new itemModel("Экскаваторы-погрузчики", "https://www.gruzovik.ru/offers/constructions/excavator-loader/"),
                new itemModel("Другая стройтехника", "https://www.gruzovik.ru/offers/constructions/misc/"),
                new itemModel("Автопогрузчики", "https://www.gruzovik.ru/offers/mining-machinery/autoloader/"),
                new itemModel("Асфальтоукладчики", "https://www.gruzovik.ru/offers/road-building-machinery/asphalt-machine/"),
                new itemModel("Горные экскаваторы", "https://www.gruzovik.ru/offers/mining-machinery/mine-excavator/"),
                new itemModel("Грейдеры", "https://www.gruzovik.ru/offers/road-building-machinery/grader/"),
                new itemModel("Грузовики самосвалы", "https://www.gruzovik.ru/offers/trucks/tipper/"),
                new itemModel("Дорожные катки", "https://www.gruzovik.ru/offers/road-building-machinery/road-roller/"),
                new itemModel("Карьерные самосвалы", "https://www.gruzovik.ru/offers/mining-machinery/pit-run-dumper/"),
                new itemModel("Мини погрузчики", "https://www.gruzovik.ru/offers/mini/mini-loader/"),
                new itemModel("Мини самосвалы", "https://www.gruzovik.ru/offers/mini/minitipper/"),
                new itemModel("Мини тракторы", "https://www.gruzovik.ru/offers/mini/minitractor/"),
                new itemModel("Мини экскаваторы", "https://www.gruzovik.ru/offers/mini/mini-excavator/"),
                new itemModel("Тракторы", "https://www.gruzovik.ru/offers/agricult/tractor/"),
                new itemModel("Тяжеловозы", "https://www.gruzovik.ru/offers/semitrailers/heavy-truck/")
        ));

        Iterator<String> iterator = grModel1.iterator();
        while (iterator.hasNext()) {
            jComboBox1.addItem(iterator.next());
        }

        jComboBox2.removeAll();

        for (itemModel itemM : grModel2.get("Автобусы")) {
            jComboBox2.addItem(itemM.getName());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("parser");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);

        jButton1.setText("Один запрос");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("www.stroyteh.ru");

        jCheckBox3.setText("www.gruzovik.ru");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jTextField1.setText("toyota");

        jLabel1.setText("Марка");

        jLabel2.setText("Модель");

        jTextField2.setText("auris");

        jLabel3.setText("Год выпуска");

        jLabel4.setText("Объем двигателя");

        jLabel5.setText("Пробег");

        jLabel6.setText("Цена от");

        jLabel7.setText("Количество позиций:");

        jComboBox1.setMaximumRowCount(20);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setMaximumRowCount(20);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("скриншот");

        jLabel8.setText("version 1.4");

        jLabel9.setText("до");

        jCheckBox4.setText("auto.ru");

        jToggleButton1.setText("Запросы по листу");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("распечатывать");

        jMenu1.setText("Файл");
        jMenuBar1.add(jMenu1);

        jMenu4.setText("Справка");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))
                        .addGap(361, 361, 361))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField1))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox5))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297)
                .addComponent(jLabel8)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBox1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox5))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel8)
                    .addComponent(jToggleButton1))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!jCheckBox1.isSelected() && !jCheckBox3.isSelected() && !jCheckBox4.isSelected()) {
            new NewOkCancelDialog(this, true, "not checked").setVisible(true);
        }

        click();

        parameters = "?";

        String marka = jTextField1.getText();
        String model = jTextField2.getText();
        String year = jTextField3.getText();
        String capacity = jTextField4.getText();
        String mileage = jTextField5.getText();
        String priceMax = jTextField6.getText();
        String priceMin = jTextField7.getText();

        int ageSum;
        int spSum;

        jLabel7.setText("Количество позиций:");

        /**
         * mark model category year hourMin hourMax mileageMin mileageMax pmin -
         * min price pmax- max price country region city per
         */
        // www.stroyteh.ru
        if (jCheckBox1.isSelected()) {

            if (marka.length() > 0) {
                parameters = parameters.concat("mark=").concat(marka).concat("&");
            }
            if (model.length() > 0) {
                parameters = parameters.concat("model=").concat(model).concat("&");
            }
            if (year.length() > 0) {
                parameters = parameters.concat("year=").concat(year).concat("&");
            }
            if (capacity.length() > 0) {
                parameters = parameters.concat("capacity=").concat(capacity).concat("&");
            }
            if (mileage.length() > 0) {
                parameters = parameters.concat("mileageMax=").concat(mileage).concat("&");
            }
            if (priceMin.length() > 0) {
                parameters = parameters.concat("pmin=").concat(priceMin).concat("&");
            }
            if (priceMax.length() > 0) {
                parameters = parameters.concat("pmax=").concat(priceMax);
            }
            List<Item> result;
            result = new StroytehRu().parse(urlStroytehRu.concat(parameters), this.proxy);

            jLabel7.setText("Количество позиций:".concat(Integer.toString(result.size())));

            if (result.size() >= 3) {

                /**
                 * MS WORD
                 *
                 */
                ToMsWord toMsWord = new ToMsWord();

                Format formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String dateTime = formatter.format(Calendar.getInstance().getTime());

                toMsWord.setNamefile(this.userHome + System.getProperty("file.separator") + "Report_" + dateTime + ".docx");

                List<elem> param = new ArrayList<>();

                param.add(new elem(result.get(0).getCost(), result.get(0).getLink()));
                param.add(new elem(result.get(1).getCost(), result.get(1).getLink()));
                param.add(new elem(result.get(2).getCost(), result.get(2).getLink()));

                ageSum = (result.get(0).getCostInt() + result.get(1).getCostInt() + result.get(2).getCostInt()) / 3;
                spSum = (int) (ageSum * 0.9);

                InputStream is1 = null;
                InputStream is2 = null;
                InputStream is3 = null;

                if (jCheckBox2.isSelected()) {
                    is1 = screenCom(this.browser, result.get(0).getLink(), this.pause);
                    is2 = screenCom(this.browser, result.get(1).getLink(), this.pause);
                    is3 = screenCom(this.browser, result.get(2).getLink(), this.pause);
                }

                Map<String, String> parameters = new HashMap<>();

                toMsWord.process(param, parameters);

                new NewOkCancelDialog(this, true, "ok").setVisible(true);

            } else {
                new NewOkCancelDialog(this, true, "Результат меньше 3 позиций").setVisible(true);
            }

        }

        // www.gruzovik.ru
        if (jCheckBox3.isSelected()) {

            // marka in eng str
            if (marka.length() > 0) {
                urlGruzovikRu = urlGruzovikRu.concat(marka).concat("/");
            }

            if (model.length() > 0) {
                urlGruzovikRu = urlGruzovikRu.concat(model).concat("/");
            }

            if (priceMin.length() > 0) {
                parameters = parameters.concat("pf=").concat(priceMin).concat("&");
            }

            if (priceMax.length() > 0) {
                parameters = parameters.concat("pt=").concat(priceMax);
            }

            System.out.println(urlGruzovikRu);

            List<Item> result;
            result = new gruzovikRu().parse(urlGruzovikRu.concat(parameters), this.proxy);

            jLabel7.setText("Количество позиций:".concat(Integer.toString(result.size())));

            if (result.size() >= 3) {

                /**
                 * MS WORD
                 *
                 */
                ToMsWord toMsWord = new ToMsWord();

                Format formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String dateTime = formatter.format(Calendar.getInstance().getTime());

                toMsWord.setNamefile(this.userHome + System.getProperty("file.separator") + "Report_" + dateTime + ".docx");

                List<elem> param = new ArrayList<>();

                param.add(new elem(result.get(0).getCost(), result.get(0).getLink()));
                param.add(new elem(result.get(1).getCost(), result.get(1).getLink()));
                param.add(new elem(result.get(2).getCost(), result.get(2).getLink()));

                ageSum = (result.get(0).getCostInt() + result.get(1).getCostInt() + result.get(2).getCostInt()) / 3;
                spSum = (int) (ageSum * 0.9);

                InputStream is1 = null;
                InputStream is2 = null;
                InputStream is3 = null;

                if (jCheckBox2.isSelected()) {
                    is1 = screenCom(this.browser, result.get(0).getLink(), this.pause);
                    is2 = screenCom(this.browser, result.get(1).getLink(), this.pause);
                    is3 = screenCom(this.browser, result.get(2).getLink(), this.pause);
                }

                Map<String, String> parameters = new HashMap<>();

                toMsWord.process(param, parameters);

                new NewOkCancelDialog(this, true, "ok").setVisible(true);

            } else {
                new NewOkCancelDialog(this, true, "Результат меньше 3 позиций").setVisible(true);
            }

        }

        if (jCheckBox4.isSelected()) {

            //https://auto.ru/cars/toyota/auris/all/?beaten=1&customs_state=1&geo_radius=200&image=true&sort_offers=fresh_relevance_1-DESC&top_days=off&currency=RUR&output_type=list&page_num_offers=1
            // marka in eng str
//            if (marka.length() > 0) {
//                urlAutoRu = urlAutoRu.concat(marka).concat("/");
//            }
//
//            if (model.length() > 0) {
//                urlAutoRu = urlAutoRu.concat(model).concat("/");
//            }
//
//            urlAutoRu = urlAutoRu.concat("all/");
            parameters = "?beaten=1&customs_state=1&geo_radius=200&image=true&sort_offers=fresh_relevance_1-DESC&top_days=off&currency=RUR&output_type=list&page_num_offers=1";

//            log.log(Level.INFO, urlAutoRu);
            List<Item> result = null;

//            result = new AutoRu().parse(urlAutoRu.concat(parameters), this.proxy);
            jLabel7.setText("Количество позиций:".concat(Integer.toString(result.size())));

            if (result.size() >= 3) {

                /**
                 * MS WORD
                 *
                 */
                ToMsWord toMsWord = new ToMsWord();

                Format formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String dateTime = formatter.format(Calendar.getInstance().getTime());

                toMsWord.setNamefile(this.userHome + System.getProperty("file.separator") + "Report_" + dateTime + ".docx");

                List<elem> param = new ArrayList<>();

                param.add(new elem(result.get(0).getCost(), result.get(0).getLink()));
                param.add(new elem(result.get(1).getCost(), result.get(1).getLink()));
                param.add(new elem(result.get(2).getCost(), result.get(2).getLink()));

                ageSum = (result.get(0).getCostInt() + result.get(1).getCostInt() + result.get(2).getCostInt()) / 3;
                spSum = (int) (ageSum * 0.9);

                InputStream is1 = null;
                InputStream is2 = null;
                InputStream is3 = null;

                if (jCheckBox2.isSelected()) {
                    is1 = screenCom(this.browser, result.get(0).getLink(), this.pause);
                    is2 = screenCom(this.browser, result.get(1).getLink(), this.pause);
                    is3 = screenCom(this.browser, result.get(2).getLink(), this.pause);
                }

                Map<String, String> parameters = new HashMap<>();

                toMsWord.process(param, parameters);

                new NewOkCancelDialog(this, true, "ok").setVisible(true);

            } else {
                new NewOkCancelDialog(this, true, "Результат меньше 3 позиций").setVisible(true);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        jComboBox2.removeAllItems();

        String key = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());

        if (grModel2.containsKey(key)) {
            for (itemModel itemM : grModel2.get(key)) {
                jComboBox2.addItem(itemM.getName());
            }
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed

    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        click();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        boolean f1 = false;
        boolean f2 = false;
        boolean f3 = false;

        String marka = null;
        String model = null;

        int countStr = 0;

        parser parser = (parser) this.springContext.getBean("parser_bean");

        parser.loadProxy(parser.fromFile(this.fileProxyList));
        proxyServer proxy1 = parser.getProxy();
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy1.getServer(), Integer.valueOf(proxy1.getPort())));

        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Выберите файл с таблицей");
        if (ret == JFileChooser.APPROVE_OPTION) {

            Workbook wb = null;
            try {

                File file = fileopen.getSelectedFile();
                this.listPath = file.getName();

                wb = new XSSFWorkbook(new FileInputStream(file));
                Sheet sheet = wb.getSheetAt(0);
                log.log(Level.INFO, "Лист " + sheet.getSheetName());

                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                rowIterator.next();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    Cell cell = row.getCell(7);
                    if (cell != null) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            category = cell.getStringCellValue();
                        }
                    }

                    if (category != null && category.length() > 0) {

                        parser.rootUrl = parser.identifyCategory(category);
                        f1 = true;

                        // marka
                        cell = row.getCell(8);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                marka = cell.getStringCellValue();
                                if (marka.length() > 0) {
                                    // marka in eng str
                                    marka = parser.translitMarka(marka.toUpperCase());
                                    parser.rootUrl = parser.rootUrl.concat(marka).concat("/");
                                    //urlAutoRu = urlAutoRu.concat(marka).concat("/");
                                    f2 = true;
                                }
                            }
                        }

                        // model
                        cell = row.getCell(9);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                model = cell.getStringCellValue();
                                if (model.length() > 0) {
                                    parser.rootUrl = parser.rootUrl.concat(model).concat("/");
                                    f3 = true;
                                }
                            }
                        }

                        String numkredDog = "1";
                        cell = row.getCell(2);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                numkredDog = cell.getStringCellValue();
                            }
                        }

                        String dataKredDog = "12.12.2010";
                        cell = row.getCell(3);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                dataKredDog = cell.getStringCellValue();
                            }
                        }

                        String numDogZal = "---";
                        String dataDogZal = "---";

                        String fio = "";
                        cell = row.getCell(6);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                fio = cell.getStringCellValue();
                            }
                        }

                        String fioZaem = "";
                        cell = row.getCell(1);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                fioZaem = cell.getStringCellValue();
                            }
                        }

                        String mesto = "";
                        cell = row.getCell(19);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                mesto = cell.getStringCellValue();
                            }
                        }

                        Format formatter = new SimpleDateFormat("dd.MM.yyyy");
                        String data = formatter.format(Calendar.getInstance().getTime());

                        // Only if there is a brand and model
                        if (f1 && f2 && f3) {

                            parser.rootUrl = parser.rootUrl.concat("all/");
                            //urlAutoRu = urlAutoRu.concat("all/");

                            parameters = "?beaten=1&customs_state=1&geo_radius=200&image=true&sort_offers=fresh_relevance_1-DESC&top_days=off&currency=RUR&output_type=list&page_num_offers=1";

                            log.log(Level.INFO, parser.rootUrl);countStr++;
                            log.log(Level.INFO, "count request " + Integer.toString(countStr));

                            // every 10 requests change proxy
                            if (countStr % 10 == 0) {
                                proxyServer proxy2 = parser.getProxy();
                                this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy2.getServer(), Integer.valueOf(proxy2.getPort())));
                                log.log(Level.INFO, "change proxy ".concat(proxy2.getServer()).concat(":").concat(proxy2.getPort()));
                            }

                            List<Item> result;
                            result = parser.parse(new AutoRu(), parser.rootUrl.concat(parameters), this.proxy);

                            jLabel7.setText("Количество позиций:".concat(Integer.toString(result.size())));

                            if (result.size() >= 3) {

                                /**
                                 * MS WORD
                                 *
                                 */
                                ToMsWord toMsWord = new ToMsWord();

                                formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                String dateTime = formatter.format(Calendar.getInstance().getTime());

                                toMsWord.setNamefile(this.userHome + System.getProperty("file.separator") + "Report_" + dateTime + ".docx");

                                // image 
                                InputStream is1 = null;
                                InputStream is2 = null;
                                InputStream is3 = null;

                                if (jCheckBox2.isSelected()) {
                                    is1 = screenCom(this.browser, result.get(0).getLink(), this.pause);
                                    is2 = screenCom(this.browser, result.get(1).getLink(), this.pause);
                                    is3 = screenCom(this.browser, result.get(2).getLink(), this.pause);
                                }

                                // param
                                List<elem> param = new ArrayList<>();

                                param.add(new elem(result.get(0).getCost(), result.get(0).getLink(), is1));
                                param.add(new elem(result.get(1).getCost(), result.get(1).getLink(), is2));
                                param.add(new elem(result.get(2).getCost(), result.get(2).getLink(), is3));

                                // ras
                                int ageSum = (result.get(0).getCostInt() + result.get(1).getCostInt() + result.get(2).getCostInt()) / 3;
                                int spSum = (int) (ageSum * 0.9);
                                int zalSum = (int) (ageSum * 0.5);

                                // par
                                Map<String, String> par = new HashMap<>();

                                par.put("numKredDog", numkredDog);
                                par.put("dataKredDog", dataKredDog);
                                par.put("numDogZal", numDogZal);
                                par.put("dataDogZal", dataDogZal);
                                par.put("fio", fio);
                                par.put("fioZaem", fioZaem);
                                par.put("mesto", mesto);
                                par.put("marka", marka);
                                par.put("model", model);
                                par.put("ageSum", String.valueOf(ageSum));
                                par.put("spSum", String.valueOf(spSum));
                                par.put("zalSum", String.valueOf(zalSum));
                                par.put("data", data);

                                toMsWord.process(param, par);

                                log.log(Level.INFO, "toMsWord ok");

                            } else {
                                log.log(Level.INFO, "Результат меньше 3 позиций");
                            }

                            f1 = false;
                            f2 = false;
                            f3 = false;
                            marka = null;
                            model = null;

                            sleep(5531);

                        }

                    }

                }

                new NewOkCancelDialog(this, true, "ok").setVisible(true);

            } catch (IOException | InterruptedException ex) {
                log.log(Level.INFO, ex.toString());
            } finally {
                try {
                    wb.close();
                } catch (IOException ex) {
                    log.log(Level.INFO, ex.toString());
                }
            }

        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void click() {
        String key1 = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
        String key2 = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());

        if (grModel2.containsKey(key1)) {
            for (itemModel itemM : grModel2.get(key1)) {
                if (itemM.getName().equals(key2)) {
                    //System.out.println(itemM.getName() + " " + itemM.getHref());
                    urlGruzovikRu = itemM.getHref();
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainJFrame mf = new mainJFrame();
                mf.urlStroytehRu = System.getProperty("urlStroytehRu");
                mf.urlGruzovikRu = System.getProperty("urlGruzovikRu");
                mf.userHome = System.getProperty("target_dir") == null ? System.getProperty("user.home") : System.getProperty("target_dir");
                mf.browser = System.getProperty("browser");
                mf.pause = Integer.valueOf(System.getProperty("pause") == null ? "5000" : System.getProperty("pause"));
                mf.listPath = System.getProperty("listPath");
                mf.log_path = System.getProperty("log_path");
                mf.log_level = System.getProperty("log_level");
                mf.fileProxyList = System.getProperty("fileProxyList");

                /**
                 * Loger
                 */
                FileHandler fh;
                try {

                    fh = new FileHandler(mf.log_path, true);
                    fh.setFormatter(new SimpleFormatter());
                    fh.setEncoding("UTF-8");
                    log.addHandler(fh);
                    log.setLevel(Level.ALL);

                } catch (IOException e) {
                    System.out.println(e.toString());
                }

                log.log(Level.INFO, "urlStroytehRu:" + mf.urlStroytehRu);
                log.log(Level.INFO, "urlGruzovikRu:" + mf.urlGruzovikRu);
                log.log(Level.INFO, "userHome:" + mf.userHome);
                log.log(Level.INFO, "browser:" + mf.browser);
                log.log(Level.INFO, "pause:" + mf.pause);
                log.log(Level.INFO, "listPath:" + mf.listPath);
                log.log(Level.INFO, "log_path:" + mf.log_path);
                log.log(Level.INFO, "log_level:" + mf.log_level);
                log.log(Level.INFO, "fileProxyList:" + mf.fileProxyList);

                mf.springContext = new AnnotationConfigApplicationContext(BeenConfig.class);

                mf.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JToggleButton jToggleButton1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables
}
