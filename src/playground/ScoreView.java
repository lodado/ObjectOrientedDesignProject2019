package playground;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.ScrollPane;
import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** ���� ����� �����ִ� �� */
public class ScoreView implements ActionListener {
   /** DB���� ���� ����� �������� ���� ����� ���� */
   private JFrame frame;
   private UserInfo user;
   private JButton backButton;
   private JButton frontButton;
   private JButton btnClose;
   private JLabel lblPage;
   private int pageNum;
   private int maxPageNum;
   private ScorePanel sp1;
   private ScorePanel sp2;
   private ScorePanel sp3;
   private ScorePanel sp4;
   private ScorePanel sp5;
   ArrayList<Score> scoreList;

   /**
    * ���� ��� ����
    * 
    * @param user - ����� ����
    */
   public ScoreView(UserInfo user) {
      frame = new JFrame();
      pageNum = 0;
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenSize = tk.getScreenSize();
      int screenHeight = screenSize.height;
      int screenWidth = screenSize.width;
      frame.setLocation(screenWidth / 4, screenHeight / 10);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.user = user;

      frame.setSize(600, 600);
      frame.getContentPane();
      frame.getContentPane().setLayout(null);

      // �÷��̾� ����
      JLabel label = new JLabel("���� ����");
      label.setBounds(17, 15, 82, 21);
      frame.getContentPane().add(label);

      JLabel lblMyRank = new JLabel("");
      int myRank = user.getMyScore();
      if (myRank != -1)
         lblMyRank.setText(Integer.toString(myRank));
      else
         lblMyRank.setText("������ ��");
      lblMyRank.setBounds(17, 48, 82, 21);
      frame.getContentPane().add(lblMyRank);

      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setText(Integer.toString(user.getWin()));
      lblNewLabel.setBounds(316, 48, 82, 21);
      frame.getContentPane().add(lblNewLabel);

      JLabel lblNewLabel_7 = new JLabel("");
      lblNewLabel_7.setText(Integer.toString(user.getLose()));
      lblNewLabel_7.setBounds(463, 48, 82, 21);
      frame.getContentPane().add(lblNewLabel_7);
      // �÷��̾� ��

      // ���ھ� �г� ����
      JLabel lblNewLabel_1 = new JLabel("����");
      lblNewLabel_1.setBounds(27, 90, 82, 21);
      frame.getContentPane().add(lblNewLabel_1);

      JLabel lblNewLabel_2 = new JLabel("�г���");
      lblNewLabel_2.setBounds(160, 90, 82, 21);
      frame.getContentPane().add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel("�̱� Ƚ��");
      lblNewLabel_3.setBounds(316, 90, 82, 21);
      frame.getContentPane().add(lblNewLabel_3);

      JLabel lblNewLabel_4 = new JLabel("�� Ƚ��");
      lblNewLabel_4.setBounds(463, 90, 82, 21);
      frame.getContentPane().add(lblNewLabel_4);

      JLabel lblNewLabel_5 = new JLabel("�̱� Ƚ��");
      lblNewLabel_5.setBounds(316, 15, 82, 21);
      frame.getContentPane().add(lblNewLabel_5);

      JLabel lblNewLabel_6 = new JLabel("�� Ƚ��");
      lblNewLabel_6.setBounds(463, 15, 82, 21);
      frame.getContentPane().add(lblNewLabel_6);

      sp1 = new ScorePanel();
      sp1.setBounds(0, 126, 578, 75);
      frame.getContentPane().add(sp1);
      sp1.setLayout(null);

      sp2 = new ScorePanel();
      sp2.setBounds(0, 200, 578, 75);
      frame.getContentPane().add(sp2);
      sp2.setLayout(null);

      sp3 = new ScorePanel();
      sp3.setBounds(0, 275, 578, 75);
      frame.getContentPane().add(sp3);
      sp3.setLayout(null);

      sp4 = new ScorePanel();
      sp4.setBounds(0, 350, 578, 75);
      frame.getContentPane().add(sp4);
      sp4.setLayout(null);

      sp5 = new ScorePanel();
      sp5.setBounds(0, 425, 578, 75);
      frame.getContentPane().add(sp5);
      sp5.setLayout(null);

      frontButton = new JButton("front");
      frontButton.setBounds(214, 500, 129, 29);
      frame.getContentPane().add(frontButton);
      frontButton.addActionListener(this);
      backButton = new JButton("back");
      backButton.setBounds(17, 500, 129, 29);
      frame.getContentPane().add(backButton);
      backButton.addActionListener(this);

      lblPage = new JLabel("1");
      lblPage.setBounds(176, 504, 21, 21);
      frame.getContentPane().add(lblPage);

      btnClose = new JButton("close");
      btnClose.setBounds(432, 500, 129, 29);
      frame.getContentPane().add(btnClose);
      btnClose.addActionListener(this);
      scoreList = user.getScore();

      maxPageNum = scoreList.size() / 5;
      try {
         changePanelState(scoreList, sp1, 0);
         changePanelState(scoreList, sp2, 1);
         changePanelState(scoreList, sp3, 2);
         changePanelState(scoreList, sp4, 3);
         changePanelState(scoreList, sp5, 4);
      } catch (Exception e) {

      }
      frame.setVisible(true);
   }

   /** ���ھ� �г��� ���¸� �ٲٴ� �޼ҵ� */
   public void changePanelState(ArrayList<Score> scoreList, ScorePanel sp, int num) {
      sp.lblNickname.setText(scoreList.get(num).nickname);
      sp.lblWin.setText(Integer.toString(scoreList.get(num).win));
      sp.lblLose.setText(Integer.toString(scoreList.get(num).lose));
      sp.lblRank.setText(Integer.toString(num + 1));
   }
   
   /** ���ھ� �г� �ʱ�ȭ*/
   public void initialPanelState(ScorePanel sp) {
      sp.lblRank.setText("");
      sp.lblNickname.setText("");
      sp.lblWin.setText("");
      sp.lblLose.setText("");
   }
   
   /** ��ư�� ���� �� ���ھ� �������� �̵��Ѵ�. ���� ��ư�� ������ �䰡 ����ȴ�. */
   public void actionPerformed(ActionEvent e) {
      initialPanelState(sp1);
      initialPanelState(sp2);
      initialPanelState(sp3);
      initialPanelState(sp4);
      initialPanelState(sp5);
      if (e.getSource().equals(frontButton)) {
         if (pageNum < maxPageNum) {
            pageNum += 1;
            try {
               changePanelState(scoreList, sp1, 5 * pageNum);
               changePanelState(scoreList, sp2, (5 * pageNum) + 1);
               changePanelState(scoreList, sp3, (5 * pageNum) + 2);
               changePanelState(scoreList, sp4, (5 * pageNum) + 3);
               changePanelState(scoreList, sp5, (5 * pageNum)+ 4);
            } catch (Exception e1) {
            }
            lblPage.setText(Integer.toString(pageNum + 1));
         }
      } else if (e.getSource().equals(backButton)) {
         if (pageNum > 0) {
            pageNum -= 1;
            try {
               changePanelState(scoreList, sp1, 5 * pageNum);
               changePanelState(scoreList, sp2, (5 * pageNum) + 1);
               changePanelState(scoreList, sp3, (5 * pageNum) + 2);
               changePanelState(scoreList, sp4, (5 * pageNum) + 3);
               changePanelState(scoreList, sp5, (5 * pageNum) + 4);
            } catch (Exception e2) {

            }
            lblPage.setText(Integer.toString(pageNum + 1));
         }
      } else if (e.getSource() == (btnClose)) {
         frame.dispose();
      }
   }
}

/** ���� ����� ������ŭ �г��� �����ϱ� ���� �г� Ŭ���� */
class ScorePanel extends JPanel {
   JLabel lblRank;
   JLabel lblNickname;
   JLabel lblWin;
   JLabel lblLose;

   public ScorePanel() {
      lblRank = new JLabel("");
      lblRank.setBounds(17, 26, 82, 21);
      add(lblRank);

      lblNickname = new JLabel("");
      lblNickname.setBounds(144, 26, 82, 21);
      add(lblNickname);

      lblWin = new JLabel("");
      lblWin.setBounds(313, 26, 82, 21);
      add(lblWin);

      lblLose = new JLabel("");
      lblLose.setBounds(455, 26, 82, 21);
      add(lblLose);
   }

}