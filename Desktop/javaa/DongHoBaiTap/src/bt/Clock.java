package bt;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Clock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField jMuiGio;
    private JLabel jClock;
    Timer timer = new Timer();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Clock frame = new Clock();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Clock() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        contentPane.setLayout(null);

        jMuiGio = new JTextField("7");
        jMuiGio.setHorizontalAlignment(SwingConstants.CENTER);
        jMuiGio.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jMuiGio.setBounds(117, 204, 96, 26);
        contentPane.add(jMuiGio);
        jMuiGio.setColumns(10);

        JLabel lblNewLabel = new JLabel("Múi giờ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(41, 204, 66, 26);
        contentPane.add(lblNewLabel);

        jClock = new JLabel();
        JButton jOpen = new JButton("Open");
        jOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int muiGio = Integer.parseInt(jMuiGio.getText());
                if (muiGio>24||muiGio<0)
                	JOptionPane.showInternalMessageDialog(null, "Không có múi giờ trên");
                else
                	new Thread(new ClockFrame(muiGio)).start(); // 
            }
        });

        jOpen.setBounds(260, 193, 85, 47);
        contentPane.add(jOpen);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LocalTime currentTime = LocalTime.now(ZoneId.systemDefault());
                jClock.setText(String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond()));
            }
        }, 0, 1000);
        jClock.setHorizontalAlignment(SwingConstants.CENTER);
        jClock.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jClock.setBounds(64, 72, 247, 47);
        contentPane.add(jClock);
        this.setVisible(true);
        setContentPane(contentPane);
    }
}

class ClockFrame implements Runnable {
    private int muiGio;

    public ClockFrame(int muiGio) {
        this.muiGio = muiGio;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Clock");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        
        JLabel a = new JLabel("Múi "+muiGio);
        a.setBounds(130,100,50,50);
        a.setFont(new Font(null, muiGio, 15));
        frame.add(a);

        JLabel jClock = new JLabel();
        jClock.setHorizontalAlignment(SwingConstants.CENTER);
        jClock.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.add(jClock);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LocalTime currentTime = LocalTime.now(ZoneId.systemDefault());
                int gio = Integer.parseInt(currentTime.getHour() + "");
                gio = gio + muiGio - 7;
                if (gio >= 24)
                    gio -= 24;
                else if (gio < 0)
                    gio += 24;
                jClock.setText(String.format("%02d:%02d:%02d", gio, currentTime.getMinute(), currentTime.getSecond()));
            }
        }, 0, 1000);

        frame.setVisible(true);
    }
}



