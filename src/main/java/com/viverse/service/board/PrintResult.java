package com.vivers.service.board;

import java.io.PrintWriter;

public class PrintResult {
    public static String out(PrintWriter out, int result, String action) {
        if(result == 1) {
            System.out.println(action + " 성공");
            return "success";
        } else {
            out.println("<script>");
            out.println("alert('" + action + " 실패');");
            out.println("history.back();");
            out.println("</script>");

            out.close();
            return null;
        }
    }
}
