package ru.netology.mode;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String  getVerificationCode(AuthInfo authInfo) {

        val getCodeSQL = "SELECT  code FROM auth_codes order by created desc limit 1";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
                val countStmt = conn.createStatement()
        ) {
            try (val rs = countStmt.executeQuery(getCodeSQL)) {
                if (rs.next()) {
                    // выборка значения по индексу столбца (нумерация с 1) — лучше выбирать по имени
                    val getCode = rs.getString(1);
                    return getCode;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public static void cleanDataBase() {

        val cleanCards = "DELETE FROM cards";
        val cleanAuthCodes = "DELETE FROM auth_codes";
        val cleanUser = "DELETE FROM users";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass")
        ) {
            runner.update(conn, cleanAuthCodes);
            runner.update(conn, cleanCards);
            runner.update(conn, cleanUser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}