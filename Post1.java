import java.lang.System;
import java.io.*;
//不含有尾递归
class Parser {
  int lookahead;
  int counter;
  public Parser() throws IOException {
      lookahead = System.in.read();
      counter = 0;
  }

  void expr() throws IOException {
      term();
      rest();
  }

  void rest() throws IOException {
      while(true) {
          if (lookahead == '+') {
              match('+');
              term();
              System.out.write('+');
          } else if (lookahead == '-') {
              match('-');
              term();
              System.out.write('-');
          } else {
              //System.out.println("lookahead in the end is: " + lookahead);
        	     System.out.println("第" + counter + "发生词法错误");
               break;
          }
      }
  }

  void term() throws IOException {
      if (Character.isDigit((char)lookahead)) {
          System.out.write((char)lookahead);
          match(lookahead);
      } else  throw new Error("syntax error");
  }

  void match(int t) throws IOException {
      if (lookahead == t)  {
    	  lookahead = System.in.read();
    	  counter++;
      }
      else  throw new Error("syntax error");
  }
}

public class Post {
  public static void main(String[] args) throws IOException {
      long startTime=System.currentTimeMillis();
      System.out.println("这是去除尾递归的Postfix");
      System.out.println("Input an infix expression and output its postfix notation:");
      new Parser().expr();
      System.out.println("\nEnd of program.");
      long endTime=System.currentTimeMillis(); //获取结束时间
      System.out.println("程序运行时间:" + (endTime-startTime)+ "ms");
  }
}
