import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Hello  {
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to CRUD OF Zoo and Animals");
		boolean flag=true;

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		while (flag){
			System.out.println("press 1 for insert\npress 2 for update\npress 3 for show data\npress 4 for delete\npress 5 for Q1\npress 6 for q2\npress 7 for Animal start with D\npress 8 for order by age\npress 0 for exit");
			int n = Integer.parseInt(bufferedReader.readLine());
			switch (n){
				case 1:
					Dao.insert();
					break;
				case 2:
					Dao.upadte();
					break;
				case 3:
					Dao.show();
					break;
				case 4:
					Dao.delete();
					break;
				case 5:
					Dao.Query1();
					break;
				case 6:
					Dao.Query2();
					break;
				case 7:
					Dao.animalNameStartWithD();
					break;
				case 8:
					Dao.orderByAge();
					break;
				case 0:
					flag=false;
					break;
				default:
					break;
			}
		}
	}
}
