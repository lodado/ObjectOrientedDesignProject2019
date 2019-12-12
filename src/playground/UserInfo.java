package playground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 로그인, 회원가입 기능 및 사용자의 승패정보를 관리하기 위한 클래스 */
public class UserInfo {
   /** 사용자 아이디 */
   String id;
   /** 사용자 닉네임 */
   String nickname;
   /** 사용자의 승리 횟수 */
   int win;
   /** 사용자의 패배 횟수 */
   int lose;

   /** JDBC driver */
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   /** DB 주소:포트 번호/DB 이름 */
   static final String DB_URL = "jdbc:mysql://34.236.219.181:3306/OOP_USER";
   /** 접속할 계정 이름 */
   static final String USERNAME = "tester";
   /** 비밀번호 */
   static final String PASSWORD = "tester1234";
   /** DB 연결 */
   Connection conn = null;
   /** DB 상태 */
   Statement stmt = null;
   /** 쿼리 실행 결과 */
   ResultSet rs;

   /** DB와 연결한다. */
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
    * 회원가입을 위한 메소드
    * 
    * @param id       - 사용자 아이디
    * @param nickname - 사용자 닉네임
    * @param pw       - 사용자 비밀번호
    */
   public String register(String id, String nickname, String pw) {
      try {
         String sql = "SELECT COUNT(*) FROM OOP_player WHERE ID = \"" + id + "\"";
         rs = stmt.executeQuery(sql);
         rs.next();
         if (rs.getInt(1) > 0) {
            return "아이디가 이미 존재합니다.";
         } else {
            sql = "INSERT INTO OOP_player VALUES(\"" + id + "\", \"" + nickname + "\", \"" + pw + "\")";
            int rs1 = stmt.executeUpdate(sql);
            sql = "INSERT INTO OOP_score VALUES(\"" + id + "\", 0, 0)";
            rs1 = stmt.executeUpdate(sql);
            return "완료";
         }

      } catch (SQLException se1) {
         se1.printStackTrace();
         return "오류!";
      }
   }

   /**
    * 로그인을 위한 메소드
    * 
    * @param id - 사용자 아이디
    * @param pw - 사용자 비밀번호
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
               return "로그인 성공!";
            } else
               return "비밀번호가 틀립니다.";
         }
      } catch (SQLException se1) {
         se1.printStackTrace();
         return "계정이 없습니다.";
      }
      return "오류?";
   }

   /** DB에서 점수 목록을 얻기 위한 메소드 */
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

   /** 사용자의 점수를 DB에 업데이트하는 메소드 */
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
   
   /**사용자의 순위를 가져오는 메소드*/
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

   /** DB와의 연결을 끊는 메소드 */
   public void closeGame() {
      try {
         rs.close();
         stmt.close();
         conn.close();
         System.out.println("종료 완료");
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