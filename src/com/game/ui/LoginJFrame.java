package com.game.ui;

import com.game.domain.User;
import com.game.util.CodeUtil;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    //创建两个临时用户
    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("tianjiajie","9437498"));
        allUsers.add(new User("T","1"));
    }

    JButton login = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();
//    JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    //正确的验证码
    JLabel rightCode = new JLabel();



    //构造方法，构造游戏界面
    public LoginJFrame() {
        //初始化界面
        initJFrame();

        //在界面中添加内容
        initView();

        //让当前界面显示出来
        this.setVisible(true);
    }

    //初始化界面
    private void initJFrame() {
        //设置界面的宽高
        this.setSize(488, 430);
        //设置界面的标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //用户不能调整界面
        setResizable(false);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //设置窗口可见
        this.setVisible(true);
    }

    //添加界面内容
    private void initView() {
        //添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("..\\puzzlegame\\image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("..\\puzzlegame\\image\\login\\密码.png"));
        passwordText.setBounds(130, 200, 32, 16);
        this.getContentPane().add(passwordText);

        //添加验证码文字
        JLabel codeText = new JLabel(new ImageIcon("..\\puzzlegame\\image\\login\\验证码.png"));
        codeText.setBounds(116, 256, 50, 30);
        this.getContentPane().add(codeText);

        //添加用户名输入框
        JTextField username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //添加验证码输入框
        JTextField code = new JTextField();
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        //添加密码输入框
        JTextField password = new JTextField();
        password.setBounds(195, 200, 200, 30);
        this.getContentPane().add(password);

        String codeStr = CodeUtil.getCode();
        JLabel rightCode = new JLabel();
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(310,245,100,50);
        //添加到界面
        this.getContentPane().add(rightCode);

        //添加登录按钮
        JButton login = new JButton();
        login.setBounds(123,310,128,47);
        login.setIcon(new ImageIcon("..\\puzzlegame\\image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        //添加注册按钮
        JButton register = new JButton();
        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon("..\\puzzlegame\\image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("..\\puzzlegame\\image\\login\\background.png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

