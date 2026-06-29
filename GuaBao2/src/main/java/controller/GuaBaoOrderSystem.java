package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import exception.GuaBaoException;
import model.Member;
import model.Porder;
import model.PorderDetail;
import service.MemberService;
import service.impl.MemberServiceImpl;

public class GuaBaoOrderSystem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea txtReceipt;
    private JLabel lblTotalPrice;
    private JLabel lblClock; 
    private int currentTotalAmount = 0; 

    private JTextField txtMemberId;
    private JTextField txtMemberPoints;
    private JLabel lblMemberInfo;
    private String currentLoginMember = null; 

    private final Map<String, Integer> itemPrices = new HashMap<>();
    private final Map<String, Integer> itemQuantities = new HashMap<>();
    private final Map<String, JLabel> itemLabels = new HashMap<>();

    private MemberService service = new MemberServiceImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuaBaoOrderSystem frame = new GuaBaoOrderSystem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GuaBaoOrderSystem() {
        initData();

        setTitle("好家割包總店");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 880, 750); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("好家割包總店 點餐系統");
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        lblTitle.setBounds(22, 10, 400, 40);
        contentPane.add(lblTitle);

        lblClock = new JLabel();
        lblClock.setHorizontalAlignment(SwingConstants.RIGHT);
        lblClock.setFont(new Font("微軟正黑體", Font.BOLD, 16)); 
        lblClock.setForeground(Color.DARK_GRAY);
        lblClock.setBounds(450, 20, 360, 30);
        contentPane.add(lblClock);
        
        startClock();

        JPanel panelMember = new JPanel();
        panelMember.setBorder(new TitledBorder(null, "會員中心", TitledBorder.LEADING, TitledBorder.TOP, new Font("微軟正黑體", Font.BOLD, 14), Color.BLUE));
        panelMember.setBounds(22, 60, 820, 100);
        contentPane.add(panelMember);
        panelMember.setLayout(null);

        JLabel lblMemberTag = new JLabel("會員卡號:");
        lblMemberTag.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        lblMemberTag.setBounds(15, 25, 70, 25);
        panelMember.add(lblMemberTag);

        txtMemberId = new JTextField();
        txtMemberId.setBounds(85, 25, 110, 25);
        panelMember.add(txtMemberId);

        JLabel lblPointsTag = new JLabel("累積點數:");
        lblPointsTag.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        lblPointsTag.setBounds(210, 25, 70, 25);
        panelMember.add(lblPointsTag);

        txtMemberPoints = new JTextField();
        txtMemberPoints.setBounds(280, 25, 60, 25);
        panelMember.add(txtMemberPoints);

        lblMemberInfo = new JLabel("狀態：未登入會員 (一般消費)");
        lblMemberInfo.setForeground(Color.DARK_GRAY);
        lblMemberInfo.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        lblMemberInfo.setBounds(15, 65, 450, 25);
        panelMember.add(lblMemberInfo);

        JButton btnLogin = new JButton("會員登入");
        btnLogin.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
        btnLogin.setBounds(360, 24, 100, 26);
        panelMember.add(btnLogin);

        JButton btnAdd = new JButton("新增會員");
        btnAdd.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
        btnAdd.setBounds(490, 24, 150, 26);
        panelMember.add(btnAdd);

        JButton btnSearch = new JButton("查詢點數");
        btnSearch.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
        btnSearch.setBounds(655, 24, 150, 26);
        panelMember.add(btnSearch);

        JButton btnUpdate = new JButton("修改點數");
        btnUpdate.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
        btnUpdate.setBounds(490, 60, 150, 26);
        panelMember.add(btnUpdate);

        JButton btnDelete = new JButton("刪除會員");
        btnDelete.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
        btnDelete.setBounds(655, 60, 150, 26);
        panelMember.add(btnDelete);

        JLabel lblCategory1 = new JLabel("【 割包系列 】");
        lblCategory1.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        lblCategory1.setBounds(22, 180, 150, 25);
        contentPane.add(lblCategory1);

        JPanel panelGuaBao = new JPanel();
        panelGuaBao.setBounds(22, 215, 420, 160);
        contentPane.add(panelGuaBao);
        panelGuaBao.setLayout(new GridLayout(3, 1, 0, 10));
        createItemRow(panelGuaBao, "綜合割包");
        createItemRow(panelGuaBao, "赤肉割包");
        createItemRow(panelGuaBao, "焢肉割包");

        JLabel lblCategory2 = new JLabel("【 湯品系列 】");
        lblCategory2.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        lblCategory2.setBounds(22, 395, 150, 25);
        contentPane.add(lblCategory2);

        JPanel panelSoup = new JPanel();
        panelSoup.setBounds(22, 430, 420, 160);
        contentPane.add(panelSoup);
        panelSoup.setLayout(new GridLayout(3, 1, 0, 10));
        createItemRow(panelSoup, "魚丸湯");
        createItemRow(panelSoup, "貢丸湯");
        createItemRow(panelSoup, "八寶湯");

        JLabel lblOrderTitle = new JLabel("點餐明細");
        lblOrderTitle.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        lblOrderTitle.setBounds(450, 180, 100, 25);
        contentPane.add(lblOrderTitle);

        txtReceipt = new JTextArea();
        txtReceipt.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtReceipt.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtReceipt);
        scrollPane.setBounds(450, 215, 400, 310);
        contentPane.add(scrollPane);

        lblTotalPrice = new JLabel("總金額：$ 0 元");
        lblTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalPrice.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        lblTotalPrice.setBounds(480, 540, 360, 30);
        contentPane.add(lblTotalPrice);

        JButton btnReset = new JButton("清除重設");
        btnReset.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        btnReset.setBounds(480, 600, 110, 40);
        contentPane.add(btnReset);
        btnReset.addActionListener(e -> resetOrder());

        JButton btnPrint = new JButton("列印明細");
        btnPrint.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        btnPrint.setBounds(605, 600, 110, 40);
        contentPane.add(btnPrint);
        btnPrint.addActionListener(e -> {
            try {
                txtReceipt.print(new java.text.MessageFormat("好家割包總店"), new java.text.MessageFormat("第 {0} 頁"));
            } catch (PrinterException pe) {
                pe.printStackTrace();
            }
        });

        JButton btnCheckout = new JButton("確認結帳");
        btnCheckout.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        btnCheckout.setBounds(730, 600, 110, 40);
        contentPane.add(btnCheckout);
        btnCheckout.addActionListener(e -> {
            if (currentTotalAmount == 0) {
                JOptionPane.showMessageDialog(this, "請先選擇商品！");
                return;
            }

            Porder order = new Porder();
            order.setMemberId(currentLoginMember);
            order.setTotalAmount(currentTotalAmount);

            String[] displayOrder = {"綜合割包", "赤肉割包", "焢肉割包", "魚丸湯", "貢丸湯", "八寶湯"};
            for (String item : displayOrder) {
                int qty = itemQuantities.get(item);
                if (qty > 0) {
                	PorderDetail detail = new PorderDetail();
                    detail.setItemName(item);
                    detail.setUnitPrice(itemPrices.get(item));
                    detail.setQuantity(qty);
                    detail.setSubtotal(itemPrices.get(item) * qty);
                    order.getDetails().add(detail);
                }
            }

            try {
                String resultMessage = service.checkout(order);
                JOptionPane.showMessageDialog(this, "結帳成功！\n" + resultMessage, "提示", JOptionPane.INFORMATION_MESSAGE);
                resetOrder();
                logoutMember();
            } catch (GuaBaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLogin.addActionListener(e -> {
            String id = txtMemberId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "請輸入卡號！");
                return;
            }
            try {
                Member m = service.login(id);
                currentLoginMember = m.getMemberId();
                lblMemberInfo.setText("歡迎會員 " + currentLoginMember + "，目前積點：" + m.getTotalPoints() + " 點");
                lblMemberInfo.setForeground(new Color(0, 100, 0));
                JOptionPane.showMessageDialog(this, "登入成功！");
            } catch (GuaBaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
            updateReceipt();
        });

        btnSearch.addActionListener(e -> {
            String id = txtMemberId.getText().trim();
            if (id.isEmpty()) return;
            try {
                Member m = service.queryMember(id);
                txtMemberPoints.setText(String.valueOf(m.getTotalPoints()));
            } catch (GuaBaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAdd.addActionListener(e -> {
            String id = txtMemberId.getText().trim();
            String ptsStr = txtMemberPoints.getText().trim();
            int pts = ptsStr.isEmpty() ? 0 : Integer.parseInt(ptsStr);
            try {
                service.registerMember(id, pts);
                JOptionPane.showMessageDialog(this, "會員新增成功！");
                txtMemberPoints.setText("");
            } catch (GuaBaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdate.addActionListener(e -> {
            String id = txtMemberId.getText().trim();
            String ptsStr = txtMemberPoints.getText().trim();
            if (id.isEmpty() || ptsStr.isEmpty()) return;
            try {
                int pts = Integer.parseInt(ptsStr);
                service.updateMemberPoints(id, pts);
                JOptionPane.showMessageDialog(this, "點數修改成功！");
                if (id.equals(currentLoginMember)) {
                    lblMemberInfo.setText("歡迎會員 " + id + "，目前積點：" + pts + " 點");
                }
            } catch (GuaBaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            String id = txtMemberId.getText().trim();
            if (id.isEmpty()) return;
            try {
                service.deleteMember(id);
                JOptionPane.showMessageDialog(this, "會員刪除成功！");
                if (id.equals(currentLoginMember)) logoutMember();
                txtMemberId.setText("");
                txtMemberPoints.setText("");
            } catch (GuaBaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateReceipt();
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lblClock.setText("系統時間: " + sdf.format(new Date()));
        });
        timer.start();
    }

    private void initData() {
        itemPrices.put("綜合割包", 70);
        itemPrices.put("赤肉割包", 70);
        itemPrices.put("焢肉割包", 70);
        itemPrices.put("魚丸湯", 55);
        itemPrices.put("貢丸湯", 55);
        itemPrices.put("八寶湯", 80);
        for (String itemName : itemPrices.keySet()) itemQuantities.put(itemName, 0);
    }

    private void createItemRow(JPanel parentPanel, final String itemName) {
        JPanel row = new JPanel();
        row.setLayout(null);
        int price = itemPrices.get(itemName);
        
        JLabel lblName = new JLabel(itemName + " ($" + price + ")");
        lblName.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblName.setBounds(0, 5, 180, 30);
        row.add(lblName);

        JButton btnMinus = new JButton("-");
        btnMinus.setBounds(200, 5, 50, 30);
        row.add(btnMinus);

        final JLabel lblQty = new JLabel("0", SwingConstants.CENTER);
        lblQty.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblQty.setBounds(260, 5, 40, 30);
        row.add(lblQty);
        itemLabels.put(itemName, lblQty);

        JButton btnPlus = new JButton("+");
        btnPlus.setBounds(310, 5, 50, 30);
        row.add(btnPlus);

        btnMinus.addActionListener(e -> {
            int qty = itemQuantities.get(itemName);
            if (qty > 0) {
                qty--;
                itemQuantities.put(itemName, qty);
                lblQty.setText(String.valueOf(qty));
                updateReceipt();
            }
        });

        btnPlus.addActionListener(e -> {
            int qty = itemQuantities.get(itemName);
            qty++;
            itemQuantities.put(itemName, qty);
            lblQty.setText(String.valueOf(qty));
            updateReceipt();
        });

        parentPanel.add(row);
    }

    private void updateReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append(" 店名：好家割包總店\n");
        sb.append(currentLoginMember != null ? " 會員卡號：" + currentLoginMember + "\n" : " 客別：一般顧客 (未登入)\n");
        sb.append("===========================================\n");
        sb.append(" 品項\t\t單價\t數量\t小計\n");
        sb.append("-------------------------------------------\n");

        currentTotalAmount = 0;
        String[] displayOrder = {"綜合割包", "赤肉割包", "焢肉割包", "魚丸湯", "貢丸湯", "八寶湯"};

        for (String item : displayOrder) {
            int qty = itemQuantities.get(item);
            if (qty > 0) {
                int price = itemPrices.get(item);
                int subtotal = price * qty;
                currentTotalAmount += subtotal;
//                String tab = (item.length() < 4) ? "\t\t" : "\t";
                String tab = (item.length() < 4) ? "   \t" : " \t";
                sb.append(String.format(" %s%s%d\t%d\t$%d\n", item, tab, price, qty, subtotal));
            }
        }
        txtReceipt.setText(sb.toString());
        lblTotalPrice.setText("總金額：$ " + currentTotalAmount + " 元");
    }

    private void resetOrder() {
        for (String item : itemQuantities.keySet()) {
            itemQuantities.put(item, 0);
            itemLabels.get(item).setText("0");
        }
        updateReceipt();
    }

    private void logoutMember() {
        currentLoginMember = null;
        txtMemberId.setText("");
        txtMemberPoints.setText("");
        lblMemberInfo.setText("狀態：未登入會員 (一般消費)");
        lblMemberInfo.setForeground(Color.DARK_GRAY);
        updateReceipt();
    }
}