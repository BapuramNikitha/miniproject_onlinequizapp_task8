package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {
    private final String url = "jdbc:mysql://localhost:3306/quizdb";
    private final String user = "root";
    private final String password = "root";  // <-- change if needed

    public List<Question> getAllQuestions() throws Exception {
        List<Question> list = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM questions");

        while (rs.next()) {
            int id = rs.getInt("id");
            String q = rs.getString("question");
            String[] ops = {
                    rs.getString("opt1"),
                    rs.getString("opt2"),
                    rs.getString("opt3"),
                    rs.getString("opt4")
            };
            int ans = rs.getInt("answerIndex");
            list.add(new Question(id, q, ops, ans));
        }
        con.close();
        return list;
    }

    public void saveScore(String username, int score) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO result(username, score) VALUES (?,?)");
        ps.setString(1, username);
        ps.setInt(2, score);
        ps.executeUpdate();
        con.close();
    }
}

