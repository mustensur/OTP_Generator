/**
 * OTP Generator created by Mustensur Khan
 * This is a tool that will allow users to generate the OTP by providing a secret.
 */
package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jboss.aerogear.security.otp.Totp;

public class OtpGenerator implements ActionListener
{

    private JTextField otpLabel;
    private JLabel secretLabel;
    private JFrame frame;
    private JPanel panel;
    private JButton generateOtpButton;
    private JButton copyOtp;
    private JTextField textField;

    public OtpGenerator()
    {
        frame = new JFrame();
        panel = new JPanel();
        textField = new JTextField(20);
        generateOtpButton = new JButton("GENERATE OTP");
        otpLabel = new JTextField("OTP CODE");
        secretLabel = new JLabel("Enter secret");
        copyOtp = new JButton(new AbstractAction("COPY CODE")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StringSelection copiedString = new StringSelection(otpLabel.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(copiedString, null);
            }
        });


        panel.setBorder(BorderFactory.createEmptyBorder(70,70,10,70));
        panel.setLayout(new GridLayout(0, 1));

        secretLabel.setBounds(10,50,80,25);
        panel.add(secretLabel);

        textField.setBounds(100, 20 ,165, 25);
        panel.add(textField);

        panel.add(generateOtpButton);

        panel.add(otpLabel);

        panel.add(copyOtp);

        generateOtpButton.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MK OTP Generator");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new OtpGenerator();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String otpKeyStr = textField.getText();

        Totp totp = new Totp(otpKeyStr);
        String twoFactorCode = totp.now();

        otpLabel.setText(twoFactorCode);
    }


    public void copOtp(ActionEvent e)
    {
        StringSelection copiedString = new StringSelection(otpLabel.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(copiedString, null);
    }

}
