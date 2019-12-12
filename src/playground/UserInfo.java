package playground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** �α���, ȸ������ ��� �� ������� ���������� �����ϱ� ���� Ŭ���� */
public class UserInfo {
   /** ����� ���̵� */
   String id;
   /** ����� �г��� */
   String nickname;
   /** ������� �¸� Ƚ�� */
   int win;
   /** ������� �й� Ƚ�� */
   int lose;

   /** JDBC driver */
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   /** DB �ּ�:��Ʈ ��ȣ/DB �̸� */
   static final String DB_URL = "jdbc:mysql://34.236.219.181:3306/OOP_USER";
   /** ������ ���� �̸� */
   static final String USERNAME = "tester";
   /** ��й�ȣ */
   static final String PASSWORD = "tester1234";
   /** DB ���� */
   Connection conn = null;
   /** DB ���� */
   Statement stmt = null;
   /** ���� ���� ��� */
   ResultSet rs;

   /** DB�� �����Ѵ�. */
   UserInfo() {
      try {
         Class.forName(JDBC_DRIVER);
         conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
         System.out.println("\n- MySQL Connection");
         stmt = conn.createStatement();
      } catch (SQLException se1) {
         se1.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * ȸ�������� ���� �޼ҵ�
    * 
    * @param id       - ����� ���̵�
    * @param nickname - ����� �г���
    * @param pw       - ����� ��й�ȣ
    */
   public String register(String id, String nickname, String pw) {
      try {
         String sql = "SELECT COUNT(*) FROM OOP_player WHERE ID = \"" + id + "\"";
         rs = stmt.executeQuery(sql);
         rs.next();
         if (rs.getInt(1) > 0) {
            return "���̵� �̹� �����մϴ�.";
         } else {
            sql = "INSERT INTO OOP_player VALUES(\"" + id + "\", \"" + nickname + "\", \"" + pw + "\")";
            int rs1 = stmt.executeUpdate(sql);
            sql = "INSERT INTO OOP_score VALUES(\"" + id + "\", 0, 0)";
            rs1 = stmt.executeUpdate(sql);
            return "�Ϸ�";
         }

      } catch (SQLException se1) {
         se1.printStackTrace();
         return "����!";
      }
   }

   /**
    * �α����� ���� �޼ҵ�
    * 
    * @param id - ����� ���̵�
    * @param pw - ����� ��й�ȣ
    */
   public String login(String id, String pw) {
      try {
         String sql;
         sql = "SELECT * FROM OOP_player WHERE ID = \"" + id + "\"";
         rs = stmt.executeQuery(sql);

         while (rs.next()) {
            String p = rs.getString("PW");
            if (p.equals(pw)) {
               this.setId(id);
               this.setNn(pw);
               sql = "SELECT * FROM OOP_score WHERE nickname = \"" + nickname + "\"";
               ResultSet rs2 = stmt.executeQuery(sql);
               while (rs2.next()) {
                  win = rs2.getInt("win");
                  lose = rs2.getInt("lose");
               }
               rs2.close();
               return "�α��� ����!";
            } else
               return "��й�ȣ�� Ʋ���ϴ�.";
         }
      } catch (SQLException se1) {
         se1.printStackTrace();
         return "������ �����ϴ�.";
      }
      return "����?";
   }

   /** DB���� ���� ����� ��� ���� �޼ҵ� */
   public ArrayList<Score> getScore() {
      int i = 1;
      ArrayList<Score> scoreList = new ArrayList<Score>();
      try {
         String sql = "SELECT COUNT(*) FROM OOP_score";
         rs = stmt.executeQuery(sql);
         rs.next();
         if (rs.getInt(1) < 50)
            sql = "SELECT * FROM OOP_score ORDER BY win DESC, lose LIMIT " + Integer.toString(rs.getInt(1));
         else
            sql = "SELECT * FROM OOP_score ORDER BY win DESC, lose LIMIT 50";

         ResultSet rs2 = stmt.executeQuery(sql);
         while (rs2.next()) {
            scoreList.add(new Score(rs2.getString("nickname"), rs2.getInt("win"), rs2.getInt("lose")));
            i++;
         }

      } catch (SQLException se1) {
         se1.printStackTrace();
      }
      return scoreList;
   }

   /** ������� ������ DB�� ������Ʈ�ϴ� �޼ҵ� */
   public void updateMyScore(int win, int lose) {
      this.win = win;
      this.lose = lose;
      try {
         String sql;
         sql = "update OOP_score set win=" + this.win + ", lose=" + this.lose + " where nickname=\"" + nickname
               + "\"";
         int rs = stmt.executeUpdate(sql);
      } catch (SQLException se1) {
         se1.printStackTrace();
      }
   }
   
   /**������� ������ �������� �޼ҵ�*/
   public int getMyScore() {
      ArrayList<Score> scoreList = getScore();
      int index = 1;

      for (Score s : scoreList) {
         if (s.nickname.equals(nickname))
            return index;
         else
            index++;
      }
      return -1;
   }

   /** DB���� ������ ���� �޼ҵ� */
   public void closeGame() {
      try {
         rs.close();
         stmt.close();
         conn.close();
         System.out.println("���� �Ϸ�");
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNn() {
      return nickname;
   }

   public void setNn(String nn) {
      this.nickname = nn;
   }

   public int getWin() {
      return win;
   }

   public void setWin(int win) {
      this.win = win;
   }

   public int getLose() {
      return lose;
   }

   public void setLose(int lose) {
      this.lose = lose;
   }
}